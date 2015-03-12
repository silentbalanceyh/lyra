package com.lyra.db;

import com.lyra.meta.Record;
import com.lyra.meta.Value;

import java.util.List;
import java.util.Map;

/**
 * @author Lang
 * @package com.test.lyra.db.test
 * @name RecordAccess
 * @class com.test.lyra.db.test.RecordAccess
 * @date Nov 3, 2014 7:31:40 AM
 * @see
 */
public interface RecordDao {
	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param record
	 * @return
	 */
	Record add(final Record record);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param records
	 * @return
	 */
	boolean add(final List<Record> records);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param record
	 * @return
	 */
	Record save(final Record record);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param record
	 * @return
	 */
	Record update(final Record record);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param records
	 * @return
	 */
	boolean update(final List<Record> records);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM )
	 *
	 * @param pId
	 * @return
	 */
	boolean deleteById(final Value<?> pId);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM )
	 *
	 * @param pIds
	 * @return
	 */
	boolean deleteById(final List<Value<?>> pIds);

	/**
	 * For Policy: ( MULTI only )
	 *
	 * @param pIds
	 * @return
	 */
	boolean deleteById(final Map<String, Value<?>> pIds);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	boolean deleteByUnique(final String fieldName, final Value<?> fieldValue);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @return
	 */
	boolean deleteAll();

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @return
	 */
	long countAll();

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param record
	 * @return
	 */
	boolean exist(final Record record);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @return
	 */
	List<Record> queryAll();

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Record> queryByPage(final int pageIndex, final int pageSize);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM )
	 *
	 * @param pId
	 * @return
	 */
	Record queryById(final Value<?> pId);

	/**
	 * For Policy: ( MULTI only )
	 *
	 * @param pIds
	 * @return
	 */
	Record queryById(final Map<String, Value<?>> pIds);

	/**
	 * For Policy: ( AUTO, GUID, RANDOM, MULTI )
	 *
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	Record queryByUnique(final String fieldName, final Value<?> fieldValue);
}
