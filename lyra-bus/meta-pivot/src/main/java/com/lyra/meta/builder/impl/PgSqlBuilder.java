package com.lyra.meta.builder.impl;

import com.lyra.meta.Context;
import com.lyra.mod.def.FieldSchema;

import java.util.Set;

class PgSqlBuilder extends AbstractMetaBuilder {

	public PgSqlBuilder(final Context context) {
		super(context);
	}

	@Override
	public boolean existTable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createTable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeTable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateTable() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void buildField(StringBuilder builder, FieldSchema schema) {
		// TODO Auto-generated method stub

	}

	@Override
	protected Set<String> getAutoTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Set<String> getGuidTypes() {
		// TODO Auto-generated method stub
		return null;
	}
}
