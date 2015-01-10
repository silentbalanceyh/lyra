package com.lyra.meta;

import com.lyra.exp.AbstractSchemaException;
import com.lyra.mod.def.FieldSchema;

import java.util.List;
import java.util.Map;

/**
 * Strategy to read meta data
 *
 * @author Lang
 */
public interface Strategy {
	// region Create new record

	/**
	 * Read record list from data file
	 *
	 * @return
	 */
	List<Record> readRecords();

	/**
	 * Create new record based on default value
	 *
	 * @return
	 */
	Record createRecord();

	/**
	 * Create new record based on map value;
	 *
	 * @param inputMap
	 * @return
	 */
	Record createRecord(final Map<String, Value<?>> inputMap);

	/**
	 * Create new record based on Json Content
	 *
	 * @param jsonContent
	 * @return
	 */
	Record createRecord(final String jsonContent);

	// endregion

	// region Extract database information

	/**
	 * Get keys information by model name;
	 *
	 * @param name
	 * @return
	 */
	Map<String, FieldSchema> getKeys();

	/**
	 * Get non keys information by model name;
	 *
	 * @param name
	 * @return
	 */
	Map<String, FieldSchema> getFields();

	/**
	 * Get column to field map
	 *
	 * @return
	 */
	Map<String, String> getCtoF();

	/**
	 * Get field to column map
	 *
	 * @return
	 */
	Map<String, String> getFtoC();

	/**
	 * Get table name
	 *
	 * @param name
	 * @return
	 */
	String getTable();

	/**
	 * Get order column collection
	 *
	 * @return
	 */
	List<String> getOrderColumns();

	/**
	 * Get Error of schema validation exception
	 *
	 * @return
	 */
	AbstractSchemaException getError();

	/**
	 * @param error
	 */
	void setError(final AbstractSchemaException error);
	// endregion

}
