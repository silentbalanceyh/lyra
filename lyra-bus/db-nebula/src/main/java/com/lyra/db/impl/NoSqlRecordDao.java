package com.lyra.db.impl;

import com.lyra.db.RecordDao;
import com.lyra.meta.Record;
import com.lyra.meta.Value;

import java.util.List;
import java.util.Map;

/**
 * @author Lang
 * @package com.test.lyra.db.test.impl
 * @name NoSqlRecordDao
 * @class com.test.lyra.db.test.impl.NoSqlRecordDao
 * @date Nov 4, 2014 4:10:59 AM
 * @see
 */
final class NoSqlRecordDao implements RecordDao {

	NoSqlRecordDao(final String modelName) {
	}

	@Override
	public Record add(Record record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(List<Record> records) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Record save(Record record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record update(Record record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(List<Record> records) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Value<?> pId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(List<Value<?>> pIds) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByUnique(String fieldName, Value<?> fieldValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAll() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Record> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Record> queryByPage(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record queryById(Value<?> pId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record queryByUnique(String fieldName, Value<?> fieldValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countAll() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteById(Map<String, Value<?>> pIds) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Record queryById(Map<String, Value<?>> pIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exist(Record record) {
		// TODO Auto-generated method stub
		return false;
	}

}
