package com.lyra.db.conn;

import java.util.List;
import java.util.Set;
import java.util.Map;

/**
 * Meta-Data operations of SQL database
 *
 * @author Lang
 */
public interface MetadataContext {
	// region Metadata: Database Information

	/**
	 * Get database product name
	 *
	 * @return
	 */
	String getProductName();

	/**
	 * Get database version
	 *
	 * @return
	 */
	String getProductVersion();

	/**
	 * Get jdbc driver name
	 *
	 * @return
	 */
	String getDriverName();

	/**
	 * Get jdbc driver version
	 *
	 * @return
	 */
	String getDriverVersion();

	/**
	 * Get username
	 *
	 * @return
	 */
	String getUserName();

	/**
	 * Get database category
	 *
	 * @return
	 */
	String getDatabaseCategory();

	/**
	 * Get database name of current system.
	 *
	 * @return
	 */
	String getDatabaseName();

	// endregion

	// region Metadata: Readdata without parameters

	/**
	 * Execute sql statement
	 */
	void execute(final String ddlSql);

	/**
	 * Read single column value into Set
	 *
	 * @param querySql
	 * @param columnName
	 * @return
	 */
	Set<String> selectColumn(final String querySql, final String columnName);

	/**
	 * Read single row value into hashMap
	 *
	 * @param querySql
	 * @param columns
	 * @return
	 */
	Map<String, String> selectRow(final String querySql,
			final String... columns);

	/**
	 * Read multi rows value into hashMap
	 *
	 * @param querySql
	 * @param columns
	 * @return
	 */
	List<Map<String, String>> selectRows(final String querySql,
			final String... columns);

	/**
	 * @param countSql
	 * @return
	 */
	<T extends Number> T count(final String countSql, Class<T> retType);
	// endregion
}
