package com.lyra.meta.builder.impl;

import static com.lyra.util.calculator.SetCalculator.diff;
import static com.lyra.util.converter.ConditionalConverter.ifToBoolean;
import static com.lyra.util.converter.StringConverter.upper;
import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.logger.Console.console;
import static com.lyra.util.logger.Logger.trace;
import static com.lyra.util.reflector.Factory.instance;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;

import com.lyra.meta.Context;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Constants;
import com.lyra.res.Symbol;

/**
 * MS SQL Server builder
 *
 * @author Lang
 */
class MsSqlBuilder extends AbstractMetaBuilder {

	// ~ Static Fields =======================================
	/**
	 * Exiting table checking sql statement. *
	 */
	private final static String SQL_TB_EXIST = "SELECT COUNT(name) FROM dbo.SYSOBJECTS WHERE ID = OBJECT_ID(N''{0}'') AND OBJECTPROPERTY(ID, ''IsTable'') = 1";
	/**
	 * Check if the table contains data record. *
	 */
	private final static String SQL_TB_COUNT = "SELECT COUNT(*) FROM {0}";
	/**
	 * Get all table columns meta-data information from database. *
	 */
	private final static String SQL_TB_COLUMNS = "SELECT COLUMN_NAME,IS_NULLABLE,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME=''{0}'' AND TABLE_CATALOG=''{1}''";
	/**
	 * Get all table columns meta-data of constraints information from database.
	 */
	private final static String SQL_TB_CONSTS = "SELECT T.COLUMN_NAME,C.CONSTRAINT_TYPE FROM (INFORMATION_SCHEMA.TABLE_CONSTRAINTS AS C JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE AS T ON T.CONSTRAINT_NAME=C.CONSTRAINT_NAME) WHERE T.TABLE_NAME=''{0}'' AND T.TABLE_CATALOG=''{1}''";
	/**
	 * MS Sql Server data type which contains length attribute. *
	 */
	private final static List<String> SQL_LENGTH_TYPE = Arrays.asList("CHAR",
			"NCHAR", "VARCHAR", "NVARCHAR");
	/**
	 * MS Sql Server AUTO Types *
	 */
	private final static String[] PK_AUTO_TYPES = new String[] { "INT",
			"BIGINT" };
	/**
	 * MS Sql Server GUID Types *
	 */
	private final static String[] PK_GUID_TYPES = new String[] { "UNIQUEIDENTIFIER" };
	/**
	 * Column name literal to avoid management issue. *
	 */
	private final static String STR_COLUMN_NAME = "COLUMN_NAME";

	// ~ Constructors ========================================

	/**
	 * Public constrcutor
	 *
	 * @param context
	 */
	MsSqlBuilder(final Context context) {
		super(context);
	}

	// ~ Override Methods ====================================

	/**
     *
     */
	@Override
	protected Set<String> getAutoTypes() {
		final Set<String> retTypes = hashSet();
		for (final String type : PK_AUTO_TYPES) {
			retTypes.add(type);
		}
		return retTypes;
	}

	/**
     *
     */
	@Override
	protected Set<String> getGuidTypes() {
		final Set<String> retTypes = hashSet();
		for (final String type : PK_GUID_TYPES) {
			retTypes.add(type);
		}
		return retTypes;
	}

	/**
	 * Check if table exit in database
	 */
	@Override
	public boolean existTable() {
		final String countSql = MessageFormat.format(SQL_TB_EXIST, this
				.getContext().getTable());
		final int countNum = this.getJdbc().count(countSql, Integer.class);
		return (countNum == 0) ? false : true;
	}

	/**
	 * Create table if the table does not exist
	 */
	@Override
	public boolean createTable() {
		final String ddlSql = this.genCreatingSql();
		boolean ret = false;
		try {
			// Debug Mode Only
			info(getClass(), "[META] Final creation sql statements: ");
			info(getClass(), ddlSql);
			this.getJdbc().execute(ddlSql);
			ret = true;
		} catch (DataAccessException ex) {
			trace(getClass(), ex);
		}
		return ret;
	}

	/**
	 * Drop table if the table exist in database
	 */
	@Override
	public boolean removeTable() {
		final StringBuilder builder = builder();
		builder.append(DdlHelper.buildDrop(this.getContext().getTable()));
		boolean ret = false;
		try {
			this.getJdbc().execute(builder.toString());
			ret = true;
		} catch (DataAccessException ex) {
			trace(getClass(), ex);
		}
		return ret;
	}

	/**
	 * Update table, the most complex method of metadata management.kk
	 */
	@Override
	public boolean updateTable() {
		boolean ret = false;
		// TODO: For debugging, the condition should be: this.existRecords
		// Check whether exist data records
		// if (!this.existRecords()) { // Debug line
		if (this.existRecords()) {
			final List<String> sqlList = this.genUpdatingSqls(
					this.fromSchema(), this.fromDatabase());
			try {
				for (final String sqlStmt : sqlList) {
					// no changes should not an exception
					if (!sqlStmt.equals(Symbol.STR_EMPTY)) {
						this.getJdbc().execute(sqlStmt);
					}
				}
				ret = true;
			} catch (DataAccessException ex) {
				trace(getClass(), ex);
			}
		} else {
			this.removeTable();
			this.createTable();
			ret = true;
		}
		return ret;
	}

	/**
	 * Default package access, this method could be used only in current package
	 */
	@Override
	public void buildField(final StringBuilder builder, final FieldSchema schema) {
		builder.append(schema.getColumnName()).append(Symbol.C_WHITESPACE);
		// Data Type
		builder.append(schema.getColumnType());
		if (SQL_LENGTH_TYPE.contains(schema.getColumnType())) {
			builder.append(Symbol.C_L_BRACKET).append(schema.getLength())
					.append(Symbol.C_R_BRACKET);
		}
		builder.append(Symbol.C_WHITESPACE);
		// Non-Primary Key
		if (nullable(schema.getPolicy())) {
			if (!schema.isNullable()) {
				builder.append("NOT NULL ");
			}
			if (schema.isUnique()) {
				builder.append("UNIQUE ");
			}
		} else {
			// AUTO
			if (schema.getPolicy().equals(Constants.KP_AUTO)) {
				builder.append("IDENTITY(");
				builder.append(schema.getSeqInit()).append(',');
				builder.append(schema.getSeqStep()).append(')');
			}
		}
		builder.append(',');
	}

	// ~ Private Methods =====================================

	/**
	 * @return
	 */
	private List<String> genUpdatingSqls(
			final Map<String, FieldSchema> schemaMap,
			final Map<String, FieldSchema> dbMap) {
		// Debug Mode Only
		info(getClass(), "[META] Metadata from schema files: ");
		console(getClass(), schemaMap);
		info(getClass(), "[META] Metadata from database: ");
		console(getClass(), dbMap);
		final List<String> sqlList = arrayList();
		// Add Field
		final Set<String> addedCols = diff(schemaMap.keySet(), dbMap.keySet());
		// Drop Field
		final Set<String> deletedCols = diff(dbMap.keySet(), schemaMap.keySet());
		// Debug Mode Only
		info(getClass(),
				"[META] Columns which will be added from database: ");
		console(getClass(), addedCols);
		info(getClass(),
				"[META] Columns which will be deleted from database: ");
		console(getClass(), deletedCols);
		final String addedSql = this.genUAddedSql(schemaMap, addedCols);
		sqlList.add(addedSql);
		final String deletedSql = this.genUDeletedSql(deletedCols);
		sqlList.add(deletedSql);
		// Debug Mode Only
		info(getClass(), "[META] Final modification sql statements: ");
		info(getClass(), "Adding columns: " + addedSql);
		info(getClass(), "Deleting columns: " + deletedSql);
		return sqlList;
	}

	/**
	 * Generate added columns sql statement8
	 *
	 * @param builder
	 * @param schemaMap
	 * @param addedCols
	 */
	private String genUAddedSql(final Map<String, FieldSchema> schemaMap,
			final Set<String> addedCols) {
		final StringBuilder builder = builder();
		if (!addedCols.isEmpty()) {
			builder.append(DdlHelper.buildUpdate(this.getContext().getTable()));
			builder.append("ADD ");
			for (final String columnName : addedCols) {
				this.buildField(builder, schemaMap.get(columnName));
			}
			builder.delete(builder.length() - 1, builder.length());
		}
		return builder.toString();
	}

	@SuppressWarnings("unused")
	private void genUModifiedSql(final StringBuilder builder,
			final Map<String, FieldSchema> schemaMap,
			final Map<String, FieldSchema> dbMap) {
		// TODO: Modification Sql Statement

	}

	/**
	 * Generate deleted columns sql statements
	 *
	 * @param builder
	 * @param deletedCols
	 */
	private String genUDeletedSql(final Set<String> deletedCols) {
		final StringBuilder builder = builder();
		if (!deletedCols.isEmpty()) {
			builder.append(DdlHelper.buildUpdate(this.getContext().getTable()));
			builder.append("DROP COLUMN ");
			for (final String columnName : deletedCols) {
				builder.append(columnName).append(Symbol.C_COMMA);
			}
			builder.delete(builder.length() - 1, builder.length());
		}
		return builder.toString();
	}

	/**
	 * Private method to build sql template.
	 *
	 * @return
	 */
	private String genCreatingSql() {
		final StringBuilder builder = builder();
		builder.append(DdlHelper.buildCreate(this.getContext().getTable()));
		// Build Keys
		for (final Map.Entry<String, FieldSchema> entry : this.getContext()
				.getKeys().entrySet()) {
			this.buildField(builder, entry.getValue());
		}
		// Build Fields
		for (final Map.Entry<String, FieldSchema> entry : this
				.getNonKeyFields().entrySet()) {
			this.buildField(builder, entry.getValue());
		}
		// Remove the last ','
		if (getPKeys().isEmpty()) {
			builder.delete(builder.length() - 1, builder.length());
		} else {
			// Build Primary Key
			this.buildPrimaryKeys(builder, getPKeys());
		}
		builder.append(DdlHelper.buildFooter());
		return builder.toString();
	}

	/**
	 * @return
	 */
	private Set<String> getPKeys() {
		final Set<String> retSet = hashSet();
		for (final FieldSchema schema : this.getContext().getKeys().values()) {
			if (!nullable(schema.getPolicy())) {
				retSet.add(schema.getColumnName());
			}
		}
		return retSet;
	}

	/**
	 * Get field schema definition from schema
	 *
	 * @return
	 */
	private Map<String, FieldSchema> fromSchema() {
		final Map<String, FieldSchema> fdMap = hashMap(true);
		for (final FieldSchema schema : this.getContext().getKeys().values()) {
			fdMap.put(schema.getColumnName(), schema);
		}
		for (final FieldSchema schema : this.getNonKeyFields().values()) {
			fdMap.put(schema.getColumnName(), schema);
		}
		return fdMap;
	}

	/**
	 * @return
	 */
	private boolean existRecords() {
		final String countSql = MessageFormat.format(SQL_TB_COUNT, this
				.getContext().getTable());
		final int countNum = this.getJdbc().count(countSql, Integer.class);
		return (countNum == 0) ? false : true;
	}

	/**
	 * Get field schema definition from database.
	 *
	 * @return
	 */
	private Map<String, FieldSchema> fromDatabase() {
		// Read basic information of field
		final Map<String, FieldSchema> fdMap = this.readFields();
		// Read constraints of field
		final Map<String, FieldSchema> csMap = this.readConstraints();
		// Extract constraints
		// MULTI checking
		final List<String> policyFields = arrayList();
		for (final String name : csMap.keySet()) {
			// Transfer Unique Constraints
			fdMap.get(name).setUnique(csMap.get(name).isUnique());
			// Primary Key
			if (!nullable(csMap.get(name).getPolicy())
					&& csMap.get(name).getPolicy().trim().equals("PLACEHOLDER")) {
				if (this.getAutoTypes().contains(
						fdMap.get(name).getColumnType())) {
					fdMap.get(name).setPolicy(Constants.KP_AUTO);
				} else if (this.getGuidTypes().contains(
						fdMap.get(name).getColumnType())) {
					fdMap.get(name).setPolicy(Constants.KP_GUID);
				} else {
					fdMap.get(name).setPolicy(Constants.KP_RANDOM);
				}
				policyFields.add(name);
			}
		}
		// MULTI building
		if (policyFields.size() > Symbol.I_ONE) {
			for (final String fieldName : policyFields) {
				fdMap.get(fieldName).setPolicy(Constants.KP_MULTI);
			}
		}
		return fdMap;
	}

	/**
	 * Get constraint schema definition from database.
	 *
	 * @return
	 */
	private Map<String, FieldSchema> readConstraints() {
		final String constraintSql = MessageFormat.format(SQL_TB_CONSTS, this
				.getContext().getTable(), this.getJdbc().getDatabaseName());
		final List<Map<String, String>> retList = this.getJdbc().selectRows(
				constraintSql, STR_COLUMN_NAME, "CONSTRAINT_TYPE");
		final Map<String, FieldSchema> retMap = hashMap(true);
		for (final Map<String, String> recordMap : retList) {
			final FieldSchema schema = instance(FieldSchema.class);
			final String name = recordMap.get(STR_COLUMN_NAME);
			if (!nullable(name)) {
				schema.setColumnName(name);
				final String constraint = recordMap.get("CONSTRAINT_TYPE");
				if (ifToBoolean(constraint, "UNIQUE", "PRIMARY KEY")) {
					schema.setUnique(true);
					if (ifToBoolean(constraint, "PRIMARY KEY")) {
						schema.setPolicy("PLACEHOLDER");
					}
				} else {
					schema.setUnique(false);
					schema.setPolicy(Symbol.STR_NULL);
				}
				retMap.put(name, schema);
			}
		}
		return retMap;
	}

	/**
	 * Read meta-data from MSSQL table: INFORMATION_SCHEMA.COLUMNS
	 *
	 * @return
	 */
	private Map<String, FieldSchema> readFields() {
		final String metaSql = MessageFormat.format(SQL_TB_COLUMNS, this
				.getContext().getTable(), this.getJdbc().getDatabaseName());
		final List<Map<String, String>> retList = this.getJdbc().selectRows(
				metaSql, STR_COLUMN_NAME, "DATA_TYPE", "IS_NULLABLE",
				"CHARACTER_MAXIMUM_LENGTH");
		final Map<String, FieldSchema> retMap = hashMap(true);
		for (final Map<String, String> recordMap : retList) {
			final FieldSchema schema = instance(FieldSchema.class);
			final String name = recordMap.get(STR_COLUMN_NAME);
			if (!nullable(name)) {
				schema.setColumnName(name);
				schema.setColumnType(upper(recordMap.get("DATA_TYPE")));
				schema.setNullable(ifToBoolean(recordMap.get("IS_NULLABLE"),
						"YES"));
				schema.setLength(Integer.parseInt(recordMap
						.get("CHARACTER_MAXIMUM_LENGTH")));
				retMap.put(name, schema);
			}
		}
		return retMap;
	}
}
