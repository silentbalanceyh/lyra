package com.lyra.meta.builder;

import com.lyra.exp.AbstractSchemaException;
import com.lyra.meta.Context;

/**
 * Builder method to build metadata of table.
 *
 * @author Lang
 */
public interface MetaBuilder {
	/**
	 * Check if the table existed in database
	 *
	 * @return
	 */
	boolean existTable();

	/**
	 * Create table in database
	 *
	 * @return
	 */
	boolean createTable();

	/**
	 * Drop existed table
	 *
	 * @return
	 */
	boolean removeTable();

	/**
	 * Update existed table
	 *
	 * @return
	 */
	boolean updateTable();

	/**
	 * @return
	 */
	boolean postValidate();

	/**
	 * @return
	 */
	Context getContext();

	/**
	 * @return
	 */
	AbstractSchemaException getError();
}
