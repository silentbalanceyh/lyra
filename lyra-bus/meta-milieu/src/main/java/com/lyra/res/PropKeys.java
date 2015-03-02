package com.lyra.res;

/**
 * @author Lang
 * @package com.lyra.res
 * @name PropKeys
 * @class com.lyra.res.PropKeys
 * @date Nov 28, 2014 5:02:05 PM
 * @see All properties key names
 */
final class PropKeys {

	// ~ Static Fields =======================================
	/**
	 * Default global property file path. *
	 */
	public static final String PROP_FILE = "/schema/system/global.properties";
	/**
	 * Default schema category to define model. *
	 */
	public static final String SMA_KEY = "schema.category";
	/**
	 * Default strategy implementation class name. *
	 */
	public static final String SMA_STRATEGY_KEY = "schema.{0}.context";
	/**
	 * Global Encoding *
	 */
	public static final String SYS_EN_KEY = "system.en.encoding";
	/**
	 * Global Chinese Encoding *
	 */
	public static final String SYS_CN_KEY = "system.cn.encoding";
	/**
	 * SQL/NOSQL mode of current database. *
	 */
	public static final String DB_MODE_KEY = "database.mode";
	/**
	 * Database connection pool *
	 */
	public static final String DB_POOL = "database.pool.impl";
	/**
	 * Database connection pool class
	 */
	public static final String DB_DATA_SOURCE = "database.pool.datasource";
	/**
	 * Shared in SQL, NoSQL, the category of database *
	 */
	public static final String DB_CATEGORY_KEY = "database.category";
	/**
	 * Database config file path. *
	 */
	public static final String DB_CONFIG_KEY = "database.config.file";
	/**
	 * Initialization SQL file script path. *
	 */
	public static final String DB_INITSQL_KEY = "database.sql.file";
	/**
	 * Default batch size for sql database. *
	 */
	public static final String DB_BATCH_SIZE_KEY = "database.batch.size";
	/**
	 * Default metadata builder classes configuration *
	 */
	public static final String DB_BUILDER_KEY = "database.meta.builder";
	/**
	 * Default dao classes configuration. *
	 */
	public static final String DB_DAO_KEY = "database.dao";
	/**
	 * Default dao classes configuration. *
	 */
	public static final String DB_ODAO_KEY = "database.oauth.dao";
	/**
	 * Spring configuration file of third part framework *
	 */
	public static final String TP_CFG_SPRING_KEY = "spring.config.file";

	// ~ Constructors ========================================
	private PropKeys() {
	}
}
