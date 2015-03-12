package com.lyra.db.sql.impl;

import com.lyra.meta.Record;
import com.lyra.meta.Value;

import java.util.List;
import java.util.Map;

class MySqlDao extends AbstractDao {

	public MySqlDao(final String modelName) {
		super(modelName);
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
	public boolean deleteById(Value<?> id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(List<Value<?>> ids) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByUnique(String fieldName, Value<?> fieldValue) {
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
	public Record queryById(Value<?> id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Record queryByUnique(String fieldName, Value<?> fieldValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteAll() {
		return false;
	}

	@Override
	public long countAll() {
		return -1;
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
