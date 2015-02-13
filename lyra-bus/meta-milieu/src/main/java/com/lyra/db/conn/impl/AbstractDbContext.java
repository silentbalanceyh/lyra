package com.lyra.db.conn.impl;

import static com.lyra.util.converter.StringConverter.upper;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Logger.trace;
import static com.lyra.util.reflector.Factory.singleton;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.lyra.db.conn.DataContext;
import com.lyra.db.conn.JdbcContext;
import com.lyra.db.conn.MetadataContext;
import com.lyra.db.conn.util.Input;
import com.lyra.db.conn.util.Output;
import com.lyra.meta.Value;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Resources;
import com.lyra.res.Symbol;
import com.lyra.util.prop.PropLoader;

/**
 * Jdbc Context
 *
 * @author Lang
 */
public abstract class AbstractDbContext implements MetadataContext,
		DataContext, JdbcContext {
	// ~ Static Fields =======================================
	/**
	 * Sql Datasource instance. *
	 */
	protected static DataSource dataSource;

	// ~ Instance Fields =====================================
	/**
	 * Spring JDBC template instance *
	 */
	protected transient JdbcTemplate template;
	/**
	 * Current system database category, read from properties. *
	 */
	protected transient String databaseCategory;
	/**
	 * Current system database name. *
	 */
	protected transient String databaseName;
	/**
	 * Metadata of current database *
	 */
	protected transient DatabaseMetaData metadata;
	/**
	 * Resource loader to read properties information. *
	 */
	protected transient PropLoader loader = singleton(PropLoader.class,
			getClass(), Resources.DB_CFG_FILE);

	// ~ Static Block ========================================

	/** Static initialization for BoneCPDataSource and set pool to singleton **/
	static {
		dataSource = singleton(Resources.DB_DATA_SOURCE);
	}

	// ~ Constructors ========================================

	/**
	 * Default constrcutor *
	 */
	public AbstractDbContext() {
		this(upper(Resources.DB_CATEGORY));
	}

	/**
	 * Another constructor by database name *
	 */
	public AbstractDbContext(final String databaseCategory) {
		synchronized (getClass()) {
			// Check dataSource
			if (!nullable(dataSource)) {
				// Pre initialization
				this.databaseCategory = databaseCategory;
				this.databaseName = this.loader.getString(this.databaseCategory
						+ ".jdbc.database.name");
				// Initialize data source configuration
				this.initJdbc();

				this.initAddtional();
				// Post initialization
				this.template = new JdbcTemplate(dataSource);
				// Metadata querying
				this.initMetadata();
			}
		}
	}

	// ~ Static Methods ======================================

	// ~ Abstract Methods ====================================

	/**
	 * Must be implemented by sub class, JDBC configuration *
	 */
	protected abstract void initJdbc();

	/**
	 * Must be implemented by sub class, Addtional configuration *
	 */
	protected abstract void initAddtional();

	/**
	 * Get Data source reference
	 *
	 * @return
	 */
	protected abstract DataSource getDataSource();

	// ~ Override Methods ====================================

	/**
	 * Execute sql statement and no return value needed
	 */
	@Override
	public void execute(final String ddlSql) {
		this.getJdbc().execute(ddlSql);
	}

	/**
	 * Execute count sql statement and return to T
	 */
	@Override
	public <T extends Number> T count(final String countSql,
			final Class<T> retType) {
		return this.getJdbc().queryForObject(countSql, retType);
	}

	/**
	 * Get database name
	 *
	 * @return
	 */
	@Override
	public String getProductName() {
		String ret = Symbol.STR_NULL;
		try {
			ret = this.metadata.getDatabaseProductName();
		} catch (SQLException ex) {
			trace(getClass(), ex);
		}
		return ret;
	}

	/**
	 * Get database version
	 *
	 * @return
	 */
	@Override
	public String getProductVersion() {
		String ret = Symbol.STR_NULL;
		try {
			ret = this.metadata.getDatabaseProductVersion();
		} catch (SQLException ex) {
			trace(getClass(), ex);
		}
		return ret;
	}

	/**
	 * Get jdbc driver name
	 *
	 * @return
	 */
	@Override
	public String getDriverName() {
		String ret = Symbol.STR_NULL;
		try {
			ret = this.metadata.getDriverName();
		} catch (SQLException ex) {
			trace(getClass(), ex);
		}
		return ret;
	}

	/**
	 * Get jdbc driver version
	 *
	 * @return
	 */
	@Override
	public String getDriverVersion() {
		String ret = Symbol.STR_NULL;
		try {
			ret = this.metadata.getDriverVersion();
		} catch (SQLException ex) {
			trace(getClass(), ex);
		}
		return ret;
	}

	/**
	 * Get username of current connection
	 *
	 * @return
	 */
	@Override
	public String getUserName() {
		String ret = Symbol.STR_NULL;
		try {
			ret = this.metadata.getUserName();
		} catch (SQLException ex) {
			trace(getClass(), ex);
		}
		return ret;
	}

	/**
	 * Get database category
	 *
	 * @return
	 */
	@Override
	public String getDatabaseCategory() {
		return this.databaseCategory;
	}

	/**
	 * Get database name.
	 *
	 * @return
	 */
	@Override
	public String getDatabaseName() {
		return this.databaseName;
	}

	/**
	 * Select one column value and put into list
	 */
	@Override
	public Set<String> selectColumn(final String querySql,
			final String columnName) {
		return this.getJdbc().query(querySql, Output.extractData(columnName));
	}

	/**
	 * Select one record with multi colulmns
	 *
	 * @param querySql
	 * @return
	 */
	@Override
	public Map<String, String> selectRow(final String querySql,
			final String... columns) {
		return this.getJdbc().query(querySql, Output.extractData(columns));
	}

	/**
	 * Select multi records
	 */
	@Override
	public List<Map<String, String>> selectRows(final String querySql,
			final String... columns) {
		return this.getJdbc().query(querySql, Output.extractDataList(columns));
	}

	// ~ Override Methods ====================================

	/**
	 * @param sql
	 * @param rowMapper
	 * @param params
	 * @return
	 */
	@Override
	public <T> T select(final String sql, final RowMapper<T> rowMapper,
			final Object... params) {
		return this.getJdbc().queryForObject(sql, rowMapper, params);
	}

	/**
     *
     */
	@Override
	public int update(final String sql, final Object... params) {
		return this.getJdbc().update(sql, params);
	}

	/**
     *
     */
	@Override
	public int update(final String sql, final Object[] values, final int[] types) {
		return this.getJdbc().update(sql, values, types);
	}

	// ~ Override Methods ====================================

	/**
     *
     */
	@Override
	public Map<String, String> select(final String sql,
			final FieldSchema idSchema, final Value<?> pId) {
		return this.getJdbc().query(Input.prepStmt(sql, idSchema, pId),
				Output.extractData());
	}

	/**
     *
     */
	@Override
	public Map<String, String> select(final String sql,
			final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap) {
		return this.getJdbc().query(Input.prepStmt(sql, schemaMap, valueMap),
				Output.extractData());
	}

	/**
     *
     */
	@Override
	public int update(final String sql, final FieldSchema fieldSchema,
			final Value<?> fieldValue) {
		return this.getJdbc().update(
				Input.prepStmt(sql, fieldSchema, fieldValue));
	}

	/**
     *
     */
	@Override
	public int[] batchExecute(final String sql, final FieldSchema schema,
			final List<Value<?>> pIds) {
		return this.getJdbc().batchUpdate(sql, Input.prepStmt(schema, pIds));
	}

	/**
     *
     */
	@Override
	public int[] batchExecute(final String sql,
			final List<Map<Integer, FieldSchema>> schemaList,
			final List<Map<Integer, Value<?>>> valueList) {
		return this.getJdbc().batchUpdate(sql,
				Input.prepStmt(schemaList, valueList));
	}

	/**
     *
     */
	@Override
	public int update(final String sql) {
		return this.getJdbc().update(sql);
	}

	/**
     *
     */
	@Override
	public int update(final String sql,
			final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap) {
		return this.getJdbc().update(Input.prepStmt(sql, schemaMap, valueMap));
	}

	/**
     *
     */
	@Override
	public Value<?> insert(final String sql,
			final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap, final FieldSchema idSchema,
			final boolean isRetKey) {
		return this.getJdbc().execute(
				Input.prepStmt(sql, schemaMap, valueMap, isRetKey),
				Output.extractPk(idSchema));
	}

	/**
     *
     */
	@Override
	public List<Map<String, String>> select(final String sql) {
		return this.getJdbc().query(sql, Output.extractDataList());
	}

	// ~ Methods =============================================

	// ~ Private Methods =====================================

	/**
	 * Initialize metadata information
	 */
	private void initMetadata() {
		if (nullable(this.metadata)) {
			Connection conn = nullObj();
			try {
				conn = this.getJdbc().getDataSource().getConnection();
				this.metadata = conn.getMetaData();
			} catch (SQLException ex) {
				trace(getClass(), ex);
			} finally {
				try {
					conn.close();
				} catch (SQLException ex) {
					trace(getClass(), ex);
				}
			}
		}
	}

	private JdbcTemplate getJdbc() {
		return this.template;
	}
}
