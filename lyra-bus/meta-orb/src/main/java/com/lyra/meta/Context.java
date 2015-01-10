package com.lyra.meta;

import com.lyra.exp.AbstractSchemaException;
import com.lyra.mod.def.FieldSchema;

import java.util.List;
import java.util.Set;
import java.util.Map;

/**
 * Strategy major context class.
 *
 * @author Lang
 */
public interface Context {
	/**
	 * Create new Data Record by default values
	 *
	 * @return
	 */
	Record createRecord();

	/**
	 * Create new Data Record by Map.
	 *
	 * @param inputMap
	 * @return
	 */
	Record createRecord(final Map<String, Value<?>> inputMap);

	/**
	 * Create new Data Record by json string
	 *
	 * @param jsonContext
	 * @return
	 */
	Record createRecord(final String jsonContext);

	/**
	 * Read records from data file
	 *
	 * @return
	 */
	List<Record> readRecords();

	/**
	 * Get keys definition
	 *
	 * @return
	 */
	Map<String, FieldSchema> getKeys();

	/**
	 * Get fields definition
	 *
	 * @return
	 */
	Map<String, FieldSchema> getFields();

	/**
	 * Get column -> field
	 *
	 * @return
	 */
	Map<String, String> getCtoF();

	/**
	 * Get field -> column
	 *
	 * @return
	 */
	Map<String, String> getFtoC();

	/**
	 * Get MULTI primary key field schemata
	 *
	 * @return
	 */
	Set<FieldSchema> getPKs();

	/**
	 * Get AUTO, GUID, RANDOM primary key field schema
	 *
	 * @return
	 */
	FieldSchema getPK();

	/**
	 * Get policy of field
	 *
	 * @return
	 */
	String getPolicy();

	/**
	 * Get ORDER BY columns
	 *
	 * @return
	 */
	List<String> getOrderColumns();

	/**
	 * Get database table name
	 *
	 * @return
	 */
	String getTable();

	/**
	 * Get current model name
	 *
	 * @return
	 */
	String getModel();

	/**
	 * Get abstract schema exception
	 *
	 * @return
	 */
	AbstractSchemaException getError();

	/**
	 * Set abstract schema exception
	 *
	 * @param error
	 */
	void setError(final AbstractSchemaException error);
}
