package com.lyra.db.impl;

import com.lyra.db.MetaDao;
import com.lyra.db.OAuthDao;
import com.lyra.db.RecordDao;
import com.lyra.res.Constants;
import com.lyra.res.Resources;

import java.util.Locale;
import java.util.Map;

import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.reflector.Factory.singleton;

/**
 * @author Lang
 * @package com.test.lyra.db.test.impl
 * @name DaoFactory
 * @class com.test.lyra.db.test.impl.DaoFactory
 * @date Nov 4, 2014 4:10:29 AM
 * @see
 */
public final class DaoFactory {
	// ~ Static Fields =======================================
	/**
	 * Singleton pool *
	 */
	private static final Map<String, RecordDao> DAO_POOL = hashMap(true);
	/**
	 * Singleton pool *
	 */
	private static final Map<String, MetaDao> META_DAO_POOL = hashMap(true);
	/**
	 * Database mode *
	 */
	private static final boolean IS_SQL;

	// ~ Static Block ========================================
	static {
		IS_SQL = Constants.DM_SQL.equals(Resources.DB_MODE.toUpperCase(Locale
				.getDefault()));
	}

	// ~ Constructors ========================================

	/**
	 * Private constructor *
	 */
	private DaoFactory() {
	}

	// ~ Static Methods ======================================

	/**
	 * Static public api to get accessor *
	 */
	public static RecordDao getRecordDao(final String modelName) {
		RecordDao rDao = nullObj();
		if (IS_SQL) {
			rDao = singleton(SqlRecordDao.class, DAO_POOL, modelName);
		} else {
			rDao = singleton(NoSqlRecordDao.class, DAO_POOL, modelName);
		}
		return rDao;
	}

	/* 8 Static public api to get meta accessor * */

	/**
	 * Get meta-data accessor. *
	 */
	public static MetaDao getMetadataDao(final String modelName) {
		MetaDao rDao = nullObj();
		if (IS_SQL) {
			rDao = singleton(SqlMetaDao.class, META_DAO_POOL, modelName);
		}
		return rDao;
	}

	/**
	 * Get oauth dao *
	 */
	public static OAuthDao getOAuthDao() {
		OAuthDao rDao = nullObj();
		if (IS_SQL) {
			rDao = singleton(SqlOAuthDao.class);
		}
		return rDao;
	}
}
