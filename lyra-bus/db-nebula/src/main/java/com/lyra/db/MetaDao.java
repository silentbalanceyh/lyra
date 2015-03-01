package com.lyra.db;

import com.lyra.exception.AbstractSchemaException;

import java.util.Set;

/**
 * @author Lang
 * @package com.test.lyra.db.test
 * @name MetaDao
 * @class com.test.lyra.db.test.MetaDao
 * @date 2014年11月22日 上午5:36:30
 * @see
 */
public interface MetaDao {
	/**
	 * Get all model names into set;
	 *
	 * @return
	 */
	Set<String> getModelNames();

	/**
	 * Validate schema based on schema specification
	 *
	 * @return
	 */
	void validateSchema() throws AbstractSchemaException;

	/**
	 * Sync between database and schema: schema -> database
	 *
	 * @return
	 */
	boolean syncMetadata();
}
