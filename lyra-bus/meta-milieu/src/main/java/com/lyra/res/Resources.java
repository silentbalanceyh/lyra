package com.lyra.res;

import static com.lyra.util.converter.StringConverter.upper;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.reflector.Factory.singleton;

import java.text.MessageFormat;

import com.lyra.util.prop.PropLoader;

/**
 * Static resource loeader to read configuration
 *
 * @author Lang
 */
public final class Resources {

	// ~ Static Fields =======================================
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
	private static final PropLoader LOADER;

	// ~ Static Block ========================================

	/** Static Loading */
	static {
		LOADER = singleton(PropLoader.class,PropKeys.class, PropKeys.PROP_FILE);

		SCHEMA_MODE = LOADER.getString(PropKeys.SMA_KEY);
		// System default encoding method
		SYS_ENCODING = LOADER.getString(PropKeys.SYS_EN_KEY);
		// System encoding method for Chinese charactors only
		SYS_CN_ENCODING = LOADER.getString(PropKeys.SYS_CN_KEY);

		DB_MODE = upper(LOADER.getString(PropKeys.DB_MODE_KEY));

		DB_CATEGORY = upper(LOADER.getString(PropKeys.DB_CATEGORY_KEY));

		DB_BUILDER = LOADER.getString(PropKeys.DB_BUILDER_KEY);

		DB_R_DAO = LOADER.getString(PropKeys.DB_DAO_KEY);

		DB_SEC_O_DAO = LOADER.getString(PropKeys.DB_ODAO_KEY);

		DB_CFG_FILE = LOADER.getString(PropKeys.DB_CONFIG_KEY);

		DB_SQL_FILE = LOADER.getString(PropKeys.DB_INITSQL_KEY);

		DB_BATCH_SIZE = LOADER.getInt(PropKeys.DB_BATCH_SIZE_KEY);

		SPRING_CONFIG = LOADER.getString(PropKeys.TP_CFG_SPRING_KEY);

		DB_DATA_SOURCE = LOADER.getString(PropKeys.DB_DATA_SOURCE);
	}

	// ~ Constructors ========================================

	/**
	 * Private constructor to prevent created directly *
	 */
	private Resources() {
	}

	// ~ Static Methods ======================================

	/**
	 * @param strategy
	 * @return
	 */
	public static String getStrategyClass(final String strategy) {
		return LOADER.getString(MessageFormat.format(PropKeys.SMA_STRATEGY_KEY,
				strategy));
	}

	/**
	 * @return db connection pool
	 */
	public static String getPoolClass() {
		final String dbPool = LOADER.getString(PropKeys.DB_POOL);
		return nullable(dbPool) ? Classes.DBP_BONECP : dbPool;
	}

	/**
	 * Static method *
	 */
	public static String getBuilderClass(final String category) {
		String builder = nullObj();
		switch (category) {
		case Constants.DF_MSSQL:
			builder = Classes.BLD_MSSQL;
			break;
		case Constants.DF_MYSQL:
			builder = Classes.BLD_MYSQL;
			break;
		case Constants.DF_ORACLE:
			builder = Classes.BLD_ORACLE;
			break;
		case Constants.DF_PGSQL:
			builder = Classes.BLD_PGSQL;
			break;
		default:
			builder = Resources.DB_BUILDER;
			break;
		}
		return builder;
	}

	/**
	 * Get context *
	 */
	public static String getContextClass() {
		return Classes.CTX_CONTEXT;
	}

	/**
	 * Static method *
	 */
	public static String getDaoClass(final String category) {
		String rDao = nullObj();
		switch (category) {
		case Constants.DF_MSSQL:
			rDao = Classes.DAO_MSSQL;
			break;
		case Constants.DF_MYSQL:
			rDao = Classes.DAO_MYSQL;
			break;
		case Constants.DF_ORACLE:
			rDao = Classes.DAO_ORACLE;
			break;
		case Constants.DF_PGSQL:
			rDao = Classes.DAO_PGSQL;
			break;
		default:
			rDao = Resources.DB_R_DAO;
			break;
		}
		return rDao;
	}

	/**
	 * Static method. *
	 */
	public static String getODaoClass(final String category) {
		String rDao = nullObj();
		switch (category) {
		case Constants.DF_MSSQL:
			rDao = Classes.DAO_OAUTH_MSSQL;
			break;
		case Constants.DF_MYSQL:
			break;
		case Constants.DF_ORACLE:
			break;
		case Constants.DF_PGSQL:
			break;
		default:
			rDao = Resources.DB_SEC_O_DAO;
			break;
		}
		return rDao;
	}
}
