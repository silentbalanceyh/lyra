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
}
