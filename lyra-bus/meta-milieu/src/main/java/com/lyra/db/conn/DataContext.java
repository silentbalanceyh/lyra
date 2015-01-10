package com.lyra.db.conn;

import com.lyra.meta.Value;
import com.lyra.mod.def.FieldSchema;

import java.util.List;
import java.util.Map;

/**
 * Data operations of SQL database
 *
 * @author Lang
 */
public interface DataContext {
	/**
	 * Execute sql statement
	 */
	void execute(final String ddlSql);

	/**
	 * @param schemaMap
	 * @param valueMap
	 * @return
	 */
	Value<?> insert(final String sql,
			final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap, final FieldSchema idSchema,
			final boolean isRetKey);

	/**
	 * @param sql
	 * @param schema
	 * @param pId
	 * @return
	 */
	int update(final String sql, final FieldSchema schema, final Value<?> value);

	/**
	 * Execute sql statement and return affect rows
	 *
	 * @param sql
	 * @return
	 */
	int update(final String sql);

	/**
	 * Execute sql statement and pass schame/value information
	 *
	 * @param sql
	 * @param schemaMap
	 * @param valueMap
	 * @return
	 */
	int update(final String sql, final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap);

	/**
	 * @param countSql
	 * @return
	 */
	<T extends Number> T count(final String countSql, final Class<T> retType);

	/**
	 * @param idSchema
	 * @param pId
	 * @return
	 */
	Map<String, String> select(final String sql, final FieldSchema schema,
			final Value<?> value);

	/**
	 * @param sql
	 * @param schemaMap
	 * @param valueMap
	 * @return
	 */
	Map<String, String> select(final String sql,
			final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap);

	/**
	 * @param sql
	 * @return
	 */
	List<Map<String, String>> select(final String sql);

	/**
	 * @param sql
	 * @param schema
	 * @param pIds
	 * @return
	 */
	int[] batchExecute(final String sql, final FieldSchema schema,
			final List<Value<?>> pIds);

	/**
	 * @param sql
	 * @param schemaList
	 * @param valueList
	 * @return
	 */
	int[] batchExecute(final String sql,
			final List<Map<Integer, FieldSchema>> schemaList,
			final List<Map<Integer, Value<?>>> valueList);
}
