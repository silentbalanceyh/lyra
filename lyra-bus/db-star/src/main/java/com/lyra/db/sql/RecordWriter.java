package com.lyra.db.sql;

import com.lyra.meta.Record;
import com.lyra.meta.Value;

import java.util.List;
import java.util.Map;

/**
 * @author Lang
 * @package com.test.lyra.db.sql
 * @name RecordWriter
 * @class com.test.lyra.db.sql.RecordWriter
 * @date Oct 14, 2014 2:22:14 AM
 * @see
 */
public interface RecordWriter {
	/**
	 * Add new record into database
	 *
	 * @param record
	 * @return
	 */
	Record add(final Record record);

	/**
	 * Add new records into database
	 *
	 * @param records
	 * @return
	 */
	boolean add(final List<Record> records);

	/**
	 * Add/Update current record into database
	 *
	 * @param record
	 * @return
	 */
	Record save(final Record record);

	/**
	 * Update current record into database
	 *
	 * @param record
	 * @return
	 */
	Record update(final Record record);

	/**
	 * Update current records into database
	 *
	 * @param records
	 * @return
	 */
	boolean update(final List<Record> records);

	/**
	 * Delete current record into database by provided id;
	 *
	 * @param pId
	 * @return
	 */
	boolean deleteById(final Value<?> pId);

	/**
	 * Delete current records into database by provided ids;
	 *
	 * @param modelName
	 * @param pIds
	 * @return
	 */
	boolean deleteById(final List<Value<?>> pIds);

	/**
	 * Delete current record into database by provided ids ( MULTI Policy Only )
	 *
	 * @param pIds
	 * @return
	 */
	boolean deleteById(final Map<String, Value<?>> pIds);

	/**
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	boolean deleteByUnique(final String fieldName, final Value<?> fieldValue);

	/**
	 * @return
	 */
	boolean deleteAll();
}
