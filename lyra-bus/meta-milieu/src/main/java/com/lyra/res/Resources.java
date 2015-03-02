package com.lyra.res;

import java.util.Locale;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyra.prop.PropertyLoader;

/**
 * Static resource loeader to read configuration
 *
 * @author Lang
 */
@Guarded
public final class Resources {	// NOPMD
	// ~ Static Fields =======================================
	/** **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Resources.class);
	/**
	 * Schema mode of current system, default is: orb.schema=json
	 */
	public static final String SCHEMA_MODE;
	/**
	 * System: Encoding method *
	 */
	public static final String SYS_ENCODING;
	/**
	 * System: Chinese encoding for current system. *
	 */
	public static final String SYS_CN_ENCODING;
	/**
	 * Database: The database pool implementation class
	 */
	public static final String DB_DATA_SOURCE;
	/**
	 * Database: The database mode for current system *
	 */
	public static final String DB_MODE;
	/**
	 * Database: The system database name, default is: mssql *
	 */
	public static final String DB_CATEGORY;
	/**
	 * Database: The system database configuration properties file path. *
	 */
	public static final String DB_CFG_FILE;
	/**
	 * Database: The database builder java class name. *
	 */
	public static final String DB_BUILDER;
	/**
	 * Database: The database record service java class name. *
	 */
	public static final String DB_R_DAO;
	/**
	 * Database: The database oauth service java class name. *
	 */
	public static final String DB_SEC_O_DAO;
	/**
	 * Database: The database init sql file. *
	 */
	public static final String DB_SQL_FILE;
	/**
	 * Database: The database default batch size. *
	 */
	public static final int DB_BATCH_SIZE;
	/**
	 * Spring: Configuration file path *
	 */
	public static final String SPRING_CONFIG;
	/**
	 * Private singleton resource LOADER. *
	 */
	private static final PropertyLoader LOADER;

	// ~ Static Block ========================================

	/** Static Loading */
	static {
		/**
		 * 因为PropertyLoader内部已经实现了Properties的单件模式，这里没有必要使用反射的方式创建
		 */
		LOADER = new PropertyLoader(PropKeys.class, PropKeys.PROP_FILE); // singleton(PropertyLoader.class,
																			// PropKeys.class,
																			// PropKeys.PROP_FILE);
		if (null == LOADER && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] LOADER is null: LOADER = " + LOADER);
		}

		SCHEMA_MODE = LOADER.getString(PropKeys.SMA_KEY);
		if (null == SCHEMA_MODE && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] SCHEMA_MODE is null: SCHEMA_MODE = "
					+ SCHEMA_MODE);
		}
		// System default encoding method
		SYS_ENCODING = LOADER.getString(PropKeys.SYS_EN_KEY);
		if (null == SYS_ENCODING && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] SYS_ENCODING is null: SYS_ENCODING = "
					+ SYS_ENCODING);
		}
		// System encoding method for Chinese charactors only
		SYS_CN_ENCODING = LOADER.getString(PropKeys.SYS_CN_KEY);
		if (null == SYS_CN_ENCODING && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] SYS_CN_ENCODING is null: SYS_CN_ENCODING = "
					+ SYS_CN_ENCODING);
		}

		DB_MODE = null == LOADER.getString(PropKeys.DB_MODE_KEY) ? Constants.DM_SQL
				: LOADER.getString(PropKeys.DB_MODE_KEY).toUpperCase(
						Locale.getDefault());
		if (null == DB_MODE && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] DB_MODE is null: DB_MODE = " + DB_MODE);
		}

		DB_CATEGORY = null == LOADER.getString(PropKeys.DB_CATEGORY_KEY) ? Constants.DF_MSSQL
				: LOADER.getString(PropKeys.DB_CATEGORY_KEY).toUpperCase(
						Locale.getDefault());
		if (null == DB_CATEGORY && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] DB_CATEGORY is null: DB_CATEGORY = "
					+ DB_CATEGORY);
		}

		DB_BUILDER = LOADER.getString(PropKeys.DB_BUILDER_KEY);
		if (null == DB_BUILDER && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] DB_BUILDER is null: DB_BUILDER = " + DB_BUILDER);
		}

		DB_R_DAO = LOADER.getString(PropKeys.DB_DAO_KEY);
		if (null == DB_R_DAO && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] DB_R_DAO is null: DB_BUILDER = " + DB_R_DAO);
		}

		DB_SEC_O_DAO = LOADER.getString(PropKeys.DB_ODAO_KEY);
		if (null == DB_SEC_O_DAO && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] DB_SEC_O_DAO is null: DB_SEC_O_DAO = "
					+ DB_SEC_O_DAO);
		}

		DB_CFG_FILE = LOADER.getString(PropKeys.DB_CONFIG_KEY);
		if (null == DB_CFG_FILE && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] DB_CFG_FILE is null: DB_CFG_FILE = "
					+ DB_CFG_FILE);
		}

		DB_SQL_FILE = LOADER.getString(PropKeys.DB_INITSQL_KEY);
		if (null == DB_SQL_FILE && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] DB_SQL_FILE is null: DB_SQL_FILE = "
					+ DB_SQL_FILE);
		}

		DB_BATCH_SIZE = LOADER.getInt(PropKeys.DB_BATCH_SIZE_KEY);
		if (-1 == DB_BATCH_SIZE && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] DB_BATCH_SIZE is null: DB_BATCH_SIZE = "
					+ DB_BATCH_SIZE);
		}

		SPRING_CONFIG = LOADER.getString(PropKeys.TP_CFG_SPRING_KEY);
		if (null == SPRING_CONFIG && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] SPRING_CONFIG is null: SPRING_CONFIG = "
					+ SPRING_CONFIG);
		}

		DB_DATA_SOURCE = LOADER.getString(PropKeys.DB_DATA_SOURCE);
		if (null == DB_DATA_SOURCE && LOGGER.isDebugEnabled()) {
			LOGGER.debug("[E] DB_DATA_SOURCE is null: DB_DATA_SOURCE = "
					+ DB_DATA_SOURCE);
		}
	}

	// ~ Constructors ========================================

	/**
	 * Private constructor to prevent created directly *
	 */
	private Resources() {
	}

	// ~ Static Methods ======================================
	/**
	 * 获取数据库Loader，返回值不能为NULL
	 * 
	 * @return
	 */
	@NotNull
	public static PropertyLoader getLoader() {
		return LOADER;
	}
}
