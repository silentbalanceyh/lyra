package com.lyra.db.sql;

import com.lyra.meta.Record;
import com.lyra.meta.Value;

import java.util.List;
import java.util.Map;

/**
 * @author Lang
 * @package com.test.lyra.db.sql
 * @name RecordReader
 * @class com.test.lyra.db.sql.RecordReader
 * @date Oct 14, 2014 2:52:16 AM
 * @see
 */
public interface RecordReader {
	/**
	 * Count all records from database;
	 *
	 * @return
	 */
	long countAll();

	/**
	 * Check whether current record exist in database
	 *
	 * @param record
	 * @return
	 */
	boolean exist(final Record record);

	/**
	 * Query all records from database;
	 *
	 * @param modelName
	 * @return
	 */
	List<Record> queryAll();

	/**
	 * Query records by page
	 *
	 * @param modelName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Record> queryByPage(final int pageIndex, final int pageSize);

	/**
	 * @param pId
	 * @return
	 */
	Record queryById(final Value<?> pId);

	/**
	 * @param pIds
	 * @return
	 */
	Record queryById(final Map<String, Value<?>> pIds);

	/**
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	Record queryByUnique(final String fieldName, final Value<?> fieldValue);
}
