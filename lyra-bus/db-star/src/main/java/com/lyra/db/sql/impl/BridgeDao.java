package com.lyra.db.sql.impl;

import com.lyra.db.sql.RecordReader;
import com.lyra.db.sql.RecordWriter;
import com.lyra.meta.Record;
import com.lyra.meta.Value;

import java.util.List;
import java.util.Map;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.impl
 * @name RecordService
 * @class com.test.lyra.db.sql.impl.RecordService
 * @date Oct 14, 2014 3:48:00 AM
 * @see
 */
public final class BridgeDao implements RecordReader, RecordWriter {
	// ~ Instance Fields =====================================
	/**
	 * Record Service reference and this class will call methods.*
	 */
	private transient final AbstractDao ref;

	// ~ Constructors ========================================

	/**
	 * Public constructor *
	 */
	BridgeDao(final String modelName) {
		this.ref = AbstractDao.init(modelName);
	}

	// ~ Override Methods ====================================

	/**
	 * Add new record into database
	 *
	 * @param record
	 * @return
	 */
	@Override
	public Record add(final Record record) {
		return this.getRef().add(record);
	}

	/**
	 * Add new records into database
	 *
	 * @param records
	 * @return
	 */
	@Override
	public boolean add(final List<Record> records) {
		return this.getRef().add(records);
	}

	/**
	 * Add/Update current record into database
	 *
	 * @param record
	 * @return
	 */
	@Override
	public Record save(final Record record) {
		return this.getRef().save(record);
	}

	/**
	 * Update current record into database
	 *
	 * @param record
	 * @return
	 */
	@Override
	public Record update(final Record record) {
		return this.getRef().update(record);
	}

	/**
	 * Update current records into database
	 *
	 * @param records
	 * @return
	 */
	@Override
	public boolean update(final List<Record> records) {
		return this.getRef().update(records);
	}

	/**
	 * Delete current record into database by provided id;
	 *
	 * @param pId
	 * @return
	 */
	@Override
	public boolean deleteById(final Value<?> pId) {
		return this.getRef().deleteById(pId);
	}

	/**
	 * Delete current records into database by provided ids;
	 *
	 * @param modelName
	 * @param ids
	 * @return
	 */
	@Override
	public boolean deleteById(final List<Value<?>> ids) {
		return this.getRef().deleteById(ids);
	}

	/**
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	@Override
	public boolean deleteByUnique(final String fieldName,
			final Value<?> fieldValue) {
		return this.getRef().deleteByUnique(fieldName, fieldValue);
	}

	/**
	 * Query all records from database;
	 *
	 * @param modelName
	 * @return
	 */
	@Override
	public List<Record> queryAll() {
		return this.getRef().queryAll();
	}

	/**
	 * Query records by page
	 *
	 * @param modelName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@Override
	public List<Record> queryByPage(final int pageIndex, final int pageSize) {
		return this.getRef().queryByPage(pageIndex, pageSize);
	}

	/**
	 * @param pId
	 * @return
	 */
	@Override
	public Record queryById(final Value<?> pId) {
		return this.getRef().queryById(pId);
	}

	/**
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	@Override
	public Record queryByUnique(final String fieldName,
			final Value<?> fieldValue) {
		return this.getRef().queryByUnique(fieldName, fieldValue);
	}

	/**
     *
     */
	@Override
	public boolean deleteAll() {
		return this.getRef().deleteAll();
	}

	/**
     *
     */
	@Override
	public long countAll() {
		return this.getRef().countAll();
	}

	/**
	 * @param pIds
	 * @return
	 */
	@Override
	public boolean deleteById(final Map<String, Value<?>> pIds) {
		return this.getRef().deleteById(pIds);
	}

	/**
	 * @param pIds
	 * @return
	 */
	@Override
	public Record queryById(final Map<String, Value<?>> pIds) {
		return this.getRef().queryById(pIds);
	}

	/**
	 * Check whether current record exist in database
	 *
	 * @param record
	 * @return
	 */
	@Override
	public boolean exist(final Record record) {
		return this.getRef().exist(record);
	}

	// ~ Private Methods =====================================

	/**
	 * Return current record service reference *
	 */
	private AbstractDao getRef() {
		return this.ref;
	}
}
