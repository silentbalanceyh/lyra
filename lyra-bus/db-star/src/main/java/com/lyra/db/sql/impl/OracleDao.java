package com.lyra.db.sql.impl;

import static com.lyra.util.converter.StringConverter.uuid;
import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.internal.Validator.zero;
import static com.lyra.util.logger.Logger.debug;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lyra.db.sql.util.Restrictions;
import com.lyra.db.sql.util.stmt.DeleteKit;
import com.lyra.db.sql.util.stmt.InsertKit;
import com.lyra.db.sql.util.stmt.MixKit;
import com.lyra.db.sql.util.stmt.UpdateKit;
import com.lyra.db.sql.util.stmt.WhereKit;
import com.lyra.meta.Record;
import com.lyra.meta.Value;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Constants;
import com.lyra.res.Symbol;

/**
 * Implementation class for Oracle
 *
 * @author Lang
 * @package com.test.lyra.db.sql.impl
 * @name OracleService
 * @class com.test.lyra.db.sql.impl.OracleDao
 * @date Oct 14, 2014 3:10:45 PM
 * @see
 */
class OracleDao extends AbstractDao {
	// ~ Static Fields =======================================
	/**
	 * obtain sequence id SQL statement. *
	 */
	private final static String SQL_SEQ = "SELECT {0}.NEXTVAL FROM DUAL";

	// ~ Constructors ========================================

	/**
	 * Non Public constructor *
	 */
	OracleDao(final String modelName) {
		super(modelName);
	}

	// ~ Override Methods ====================================

	/**
	 * Query ( by id )
	 */
	@Override
	public Record queryById(final Value<?> pId) {
		final StringBuilder sqlStr = builder(
				MixKit.prepSelectById(this.getContext()));
		debug(getClass(), "[METHOD] (P = AUTO,GUID,RANDOM) queryById: [SQL] : "
				+ sqlStr);
		final Map<String, String> dataMap = this.getJdbc().select(
				sqlStr.toString(), this.getContext().getPK(), pId);
		return this.extractRecord(dataMap);
	}

	/**
     *
     */
	@Override
	public Record queryById(final Map<String, Value<?>> pIds) {
		final StringBuilder sqlStr = builder(
				MixKit.prepSelectById(this.getContext()));
		debug(getClass(), "[METHOD] (P = MULTI) queryById: [SQL] : " + sqlStr);
		final Map<Integer, FieldSchema> schemaMap = hashMap(true);
		final Map<Integer, Value<?>> valueMap = hashMap(true);
		this.fillPkParams(pIds, schemaMap, valueMap);
		return this.extractRecord(this.getJdbc().select(sqlStr.toString(),
				schemaMap, valueMap));
	}

	/**
	 * Query ( by unique )
	 */
	@Override
	public Record queryByUnique(final String fieldName,
			final Value<?> fieldValue) {
		final StringBuilder sqlStr = builder(MixKit.prepSelect(this
				.getContext()));
		final FieldSchema schema = this.getField(fieldName);
		sqlStr.append(WhereKit.prepWhere(schema));
		debug(getClass(), "[METHOD] (P = ALL) queryByUnique: [SQL] : " + sqlStr);
		final Map<String, String> dataMap = this.getJdbc().select(
				sqlStr.toString(), schema, fieldValue);
		return this.extractRecord(dataMap);
	}

	/**
	 * Query All
	 */
	@Override
	public List<Record> queryAll() {
		final StringBuilder sqlStr = builder(MixKit.prepSelect(this
				.getContext()));
		sqlStr.append(MixKit.prepOrderBy(this.getContext()));
		debug(getClass(), "[METHOD] (P = ALL) queryAll: [SQL] : " + sqlStr);
		return this.extractRecordList(this.getJdbc().select(sqlStr.toString()));
	}

	/**
	 * Query ( with pager ) http://www.jb51.net/article/35213.htm ( 方案5 ) Depend
	 * on ORDER BY Clause
	 */
	@Override
	public List<Record> queryByPage(final int pageIndex, final int pageSize) {
		final String pageSql = this.genPageSql(pageIndex, pageSize);
		debug(getClass(), "[METHOD] (P = ALL) queryByPage: [SQL] : " + pageSql);
		return this.extractRecordList(this.getJdbc().select(pageSql));
	}

	/**
	 * Add
	 *
	 * @param record
	 * @return
	 */
	@Override
	public Record add(final Record record) {
		final Map<String, Integer> indexMap = hashMap(true);
		final String sqlStr = InsertKit.prepInsert(this.getContext(), indexMap,
				this.genFilters(false));
		// GUID
		this.setPreGuidId(record);
		// JDBC Execute
		debug(getClass(), "[METHOD] (P = ALL) add: [SQL] : " + sqlStr);
		final Map<Integer, FieldSchema> schemaMap = hashMap(true);
		final Map<Integer, Value<?>> valueMap = hashMap(true);
		this.fillInsertParams(record, indexMap, schemaMap, valueMap,
				this.genFilters(true));
		final boolean isRefKey = !zero(this.genFilters(false));
		final Value<?> genId = this.getJdbc().insert(sqlStr, schemaMap,
				valueMap, this.getContext().getPK(), isRefKey);
		if (isRefKey && !nullable(genId)) {
			record.set(this.getContext().getPK().getName(), genId);
		}
		return record;
	}

	/**
	 * Mass Add
	 *
	 * @param records
	 * @return
	 */
	@Override
	public boolean add(final List<Record> records) {
		final Map<String, Integer> indexMap = hashMap(true);
		final String sqlStr = InsertKit.prepInsert(this.getContext(), indexMap,
				this.genFilters(false));
		this.setPreGuidId(records.toArray(new Record[] {}));
		this.setPreAutoId(records.toArray(new Record[] {}));
		debug(getClass(), "[METHOD] (P = ALL) add ( Mass ): [SQL] : " + sqlStr);
		final List<Map<Integer, FieldSchema>> schemaList = arrayList();
		final List<Map<Integer, Value<?>>> valueList = arrayList();
		this.fillInsertParams(records, indexMap, schemaList, valueList,
				this.genFilters(true));
		return this.getExecuteResult(this.getJdbc().batchExecute(sqlStr,
				schemaList, valueList));
	}

	/**
	 * Save
	 */
	@Override
	public Record save(final Record record) {
		Record retR = nullObj();
		if (this.exist(record)) {
			retR = this.update(record);
		} else {
			retR = this.add(record);
		}
		return retR;
	}

	/**
	 * Update
	 */
	@Override
	public Record update(final Record record) {
		final Map<String, Integer> indexMap = hashMap(true);
		final StringBuilder sqlStr = builder(
				UpdateKit.prepUpdateById(this.getContext(), indexMap));
		debug(getClass(), "[METHOD] (P = ALL) update: [SQL] : " + sqlStr);
		final Map<Integer, FieldSchema> schemaMap = hashMap(true);
		final Map<Integer, Value<?>> valueMap = hashMap(true);
		this.fillUpdateParams(record, indexMap, schemaMap, valueMap);
		Record retR = nullObj();
		final boolean ret = this.getExecuteResult(this.getJdbc().update(
				sqlStr.toString(), schemaMap, valueMap));
		if (ret) {
			retR = record;
		}
		return retR;
	}

	/**
	 * Mass Update
	 */
	@Override
	public boolean update(final List<Record> records) {
		final Map<String, Integer> indexMap = hashMap(true);
		final StringBuilder sqlStr = builder(
				UpdateKit.prepUpdateById(this.getContext(), indexMap));
		debug(getClass(), "[METHOD] (P = ALL) update ( Mass ): [SQL] : "
				+ sqlStr);
		final List<Map<Integer, FieldSchema>> schemaList = arrayList();
		final List<Map<Integer, Value<?>>> valueList = arrayList();
		this.fillUpdateParams(records, indexMap, schemaList, valueList);
		return this.getExecuteResult(this.getJdbc().batchExecute(
				sqlStr.toString(), schemaList, valueList));
	}

	/**
	 * Delete ( by id )
	 */
	@Override
	public boolean deleteById(final Value<?> pId) {
		final StringBuilder sqlStr = builder(
				DeleteKit.prepDeleteById(this.getContext()));
		debug(getClass(),
				"[METHOD] (P = AUTO,GUID,RANDOM) deleteById: [SQL] : " + sqlStr);
		return this.getExecuteResult(this.getJdbc().update(sqlStr.toString(),
				this.getContext().getPK(), pId));
	}

	/**
	 * Mass Delete ( by ids )
	 */
	@Override
	public boolean deleteById(final List<Value<?>> ids) {
		final StringBuilder sqlStr = builder(
				DeleteKit.prepDeleteById(this.getContext()));
		debug(getClass(),
				"[METHOD] (P = AUTO,GUID,RANDOM) deleteById ( Mass ): [SQL] : "
						+ sqlStr);
		return this.getExecuteResult(this.getJdbc().batchExecute(
				sqlStr.toString(), this.getContext().getPK(), ids));
	}

	/**
     *
     */
	@Override
	public boolean deleteById(final Map<String, Value<?>> pIds) {
		final StringBuilder sqlStr = builder(
				DeleteKit.prepDeleteById(this.getContext()));
		debug(getClass(), "[METHOD] (P = MULTI) deleteById: [SQL] : " + sqlStr);
		final Map<Integer, FieldSchema> schemaMap = hashMap(true);
		final Map<Integer, Value<?>> valueMap = hashMap(true);
		this.fillPkParams(pIds, schemaMap, valueMap);
		return this.getExecuteResult(this.getJdbc().update(sqlStr.toString(),
				schemaMap, valueMap));
	}

	/**
	 * Delete ( by unique )
	 */
	@Override
	public boolean deleteByUnique(final String fieldName,
			final Value<?> fieldValue) {
		final StringBuilder sqlStr = builder(
				DeleteKit.prepDelete(this.getContext()));
		final FieldSchema schema = this.getField(fieldName);
		sqlStr.append(WhereKit.prepWhere(schema));
		debug(getClass(), "[METHOD] (P = ALL) deleteByUnique: [SQL] : "
				+ sqlStr);
		return this.getExecuteResult(this.getJdbc().update(sqlStr.toString(),
				schema, fieldValue));
	}

	/**
	 * Mass Delete ( all )
	 */
	@Override
	public boolean deleteAll() {
		final String sqlStr = DeleteKit.prepDelete(this.getContext());
		debug(getClass(), "[METHOD] (P = ALL) deleteAll ( Mass ): [SQL] : "
				+ sqlStr);
		return this.getExecuteResult(this.getJdbc().update(sqlStr));
	}

	/**
	 * Count ( all )
	 */
	@Override
	public long countAll() {
		final String sqlStr = MixKit.prepCount(this.getContext());
		debug(getClass(), "[METHOD] (P = ALL) countAll: [SQL] : " + sqlStr);
		return this.getJdbc().count(sqlStr, Long.class);
	}

	/**
     *
     */
	@Override
	public boolean exist(final Record record) {
		final StringBuilder sqlStr = builder(MixKit.prepCount(this
				.getContext()));
		sqlStr.append(WhereKit.prepPkWhere(this.getContext(), record));
		debug(getClass(), "[METHOD] (P = ALL) exist: [SQL] : " + sqlStr);
		return this.getExecuteResult(this.getJdbc().count(sqlStr.toString(),
				Integer.class));
	}

	// ~ Private Methods =====================================

	/**
	 * This method is for SQL Server only
	 *
	 * @return
	 */
	private String genPageSql(final int pageIndex, final int pageSize) {
		final int startRow = (pageIndex - 1) * pageSize;
		final int endRow = pageIndex * pageSize;
		final String policy = this.getContext().getPolicy();
		// ID From Segmentation & Where Segmentation
		final StringBuilder idBuf = builder();
		final StringBuilder idWhereBuf = builder();
		if (policy.trim().equals(Constants.KP_MULTI)) {
			for (final FieldSchema schema : this.getContext().getPKs()) {
				final String colName = schema.getColumnName();
				idBuf.append(colName).append(Symbol.C_COMMA);
				idWhereBuf.append(" T1.").append(colName).append("=T2.")
						.append(colName);
				idWhereBuf.append(Symbol.C_WHITESPACE).append(
						Restrictions._AND);
			}
			idBuf.delete(idBuf.length() - 1, idBuf.length());
		} else {
			final String colName = this.getContext().getPK().getColumnName();
			idBuf.append(colName);
			idWhereBuf.append(" T1.").append(colName).append("=T2.")
					.append(colName);
			idWhereBuf.append(Symbol.C_WHITESPACE)
					.append(Restrictions._AND);
		}
		final StringBuilder retSql = builder();
		final String tableName = this.getContext().getTable();
		retSql.append("SELECT T1.* FROM").append(Symbol.C_WHITESPACE)
				.append(tableName).append(Symbol.C_WHITESPACE).append("T1")
				.append(Symbol.C_COMMA).append(Symbol.C_L_BRACKET)
				.append("SELECT TOP ").append(endRow)
				.append(Symbol.C_WHITESPACE).append("ROW_NUMBER() OVER (")
				.append(MixKit.prepOrderBy(this.getContext()))
				.append(Symbol.C_R_BRACKET).append(Symbol.C_WHITESPACE)
				.append("N,").append(idBuf).append(Symbol.C_WHITESPACE)
				.append("FROM").append(Symbol.C_WHITESPACE)
				.append(tableName).append(Symbol.C_R_BRACKET)
				.append(Symbol.C_WHITESPACE).append("T2 WHERE")
				.append(idWhereBuf).append(" T2.N > ").append(startRow)
				.append(Symbol.C_WHITESPACE).append("ORDER BY T2.N ASC");
		return retSql.toString();
	}

	/**
	 * @param records
	 */
	private void setPreGuidId(final Record... records) {
		final String policy = this.getContext().getPolicy();
		if (!nullable(policy) && policy.equals(Constants.KP_GUID)) {
			for (final Record record : records) {
				record.set(this.getContext().getPK().getName(), uuid(true));
			}
		}
	}

	/**
	 * This method is for Oracle only
	 */
	private String autoID() {
		final String seqSql = MessageFormat.format(SQL_SEQ, this.getContext()
				.getPK().getSeqName());
		final List<Map<String, String>> retList = this.getJdbc().select(seqSql);

		return retList.get(0).get("NEXTVAL");
	}

	/**
	 * @param records
	 */
	private void setPreAutoId(final Record... records) {
		final String policy = this.getContext().getPolicy();
		if (!nullable(policy) && policy.equals(Constants.KP_AUTO)) {
			for (final Record record : records) {
				record.set(this.getContext().getPK().getName(), this.autoID());
			}
		}
	}

	/**
	 * Insert Helper: Generate sql statement filter for inserting
	 *
	 * @return
	 */
	private String[] genFilters(final boolean isField) {
		final Set<String> filterSet = hashSet();
		final String policy = this.getContext().getPolicy();
		switch (policy) {
		case Constants.KP_AUTO: {
			/*
			 * if (isField) {
			 * filterSet.add(this.getContext().getPK().getName()); } else {
			 * filterSet.add(this.getContext().getPK().getColumnName()); }
			 */
		}
			break;
		case Constants.KP_GUID:
		case Constants.KP_RANDOM:
		case Constants.KP_MULTI:
		default:
			break;
		}
		return filterSet.toArray(Symbol.T_STR_ARR);
	}
}
