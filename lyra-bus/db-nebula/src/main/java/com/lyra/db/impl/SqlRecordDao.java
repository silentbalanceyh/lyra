package com.lyra.db.impl;

import com.lyra.db.RecordDao;
import com.lyra.db.sql.RecordReader;
import com.lyra.db.sql.RecordWriter;
import com.lyra.db.sql.impl.BridgeDao;
import com.lyra.meta.Record;
import com.lyra.meta.Value;

import java.util.List;
import java.util.Map;

import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.reflector.Factory.singleton;

/**
 * @author Lang
 * @package com.test.lyra.db.test.impl
 * @name RecordAccessorImpl
 * @class com.test.lyra.db.test.impl.RecordAccessorImpl
 * @date Nov 3, 2014 7:36:17 AM
 * @see
 */
final class SqlRecordDao implements RecordDao {
	// ~ Static Fields =======================================
	/**
	 * Reader pool *
	 */
	private static final Map<String, RecordReader> R_POOL = hashMap(true);
	/**
	 * Writer pool *
	 */
	private static final Map<String, RecordWriter> W_POOL = hashMap(true);

	// ~ Instance Fields =====================================
	/**
	 * Sql database writer *
	 */
	private final transient RecordWriter writer;
	/**
	 * Sql database reader *
	 */
	private final transient RecordReader reader;

	// ~ Constructors ========================================

	/**
	 * Public constructor *
	 */
	SqlRecordDao(final String modelName) {
		this.writer = singleton(BridgeDao.class, W_POOL, modelName);
		this.reader = singleton(BridgeDao.class, R_POOL, modelName);
	}

	// ~ Override Methods ====================================

	/**
	 * Add new record into database *
	 */
	@Override
	public Record add(final Record record) {
		return this.getWriter().add(record);
	}

	/**
	 * Mass add record list into database *
	 */
	@Override
	public boolean add(final List<Record> records) {
		return this.getWriter().add(records);
	}

	/**
	 * Save current record into database *
	 */
	@Override
	public Record save(final Record record) {
		return this.getWriter().save(record);
	}

	/**
	 * Update current record into database *
	 */
	@Override
	public Record update(final Record record) {
		return this.getWriter().update(record);
	}

	/**
	 * Mass update record list into database *
	 */
	@Override
	public boolean update(final List<Record> records) {
		return this.getWriter().update(records);
	}

	/**
	 * Delete by id from database ( AUTO, GUID, RANDOM ) *
	 */
	@Override
	public boolean deleteById(final Value<?> pId) {
		return this.getWriter().deleteById(pId);
	}

	/**
	 * Delete by id from database ( MULTI )
	 */
	@Override
	public boolean deleteById(final Map<String, Value<?>> pIds) {
		return this.getWriter().deleteById(pIds);
	}

	/**
	 * Mass delete by id list from database *
	 */
	@Override
	public boolean deleteById(final List<Value<?>> pIds) {
		return this.getWriter().deleteById(pIds);
	}

	/**
	 * Delete by unique field from database *
	 */
	@Override
	public boolean deleteByUnique(final String fieldName,
			final Value<?> fieldValue) {
		return this.getWriter().deleteByUnique(fieldName, fieldValue);
	}

	/**
	 * Delete all records from database *
	 */
	@Override
	public boolean deleteAll() {
		return this.getWriter().deleteAll();
	}

	/**
	 * Query all records from database *
	 */
	@Override
	public List<Record> queryAll() {
		return this.getReader().queryAll();
	}

	/**
	 * Query records by page from database *
	 */
	@Override
	public List<Record> queryByPage(final int pageIndex, final int pageSize) {
		return this.getReader().queryByPage(pageIndex, pageSize);
	}

	/**
	 * Query records by Id from database *
	 */
	@Override
	public Record queryById(final Value<?> pId) {
		return this.getReader().queryById(pId);
	}

	/**
	 * Query records by Id from database ( MULTI Only )
	 */
	@Override
	public Record queryById(final Map<String, Value<?>> pIds) {
		return this.getReader().queryById(pIds);
	}

	/**
	 * Query records by unique field from database *
	 */
	@Override
	public Record queryByUnique(final String fieldName,
			final Value<?> fieldValue) {
		return this.getReader().queryByUnique(fieldName, fieldValue);
	}

	/**
	 * Count records in database *
	 */
	@Override
	public long countAll() {
		return this.getReader().countAll();
	}

	/**
	 * Check if record exist in database. *
	 */
	@Override
	public boolean exist(final Record record) {
		return this.getReader().exist(record);
	}

	// ~ Private Methods =====================================
	private RecordWriter getWriter() {
		return this.writer;
	}

	private RecordReader getReader() {
		return this.reader;
	}
}
