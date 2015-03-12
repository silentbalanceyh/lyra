package com.lyra.db.impl;

import com.lyra.db.OAuthDao;
import com.lyra.db.sql.OAuthWriter;
import com.lyra.db.sql.oauth.impl.BridgeOAuthDao;
import com.lyra.mod.oauth.AuthenticationCode;

import static com.lyra.util.reflector.Factory.singleton;

/**
 * @author Lang
 * @package com.test.lyra.db.test.impl
 * @name SqlOAuthDao
 * @class com.test.lyra.db.test.impl.SqlOAuthDao
 * @date Dec 9, 2014 4:46:40 PM
 * @see
 */
class SqlOAuthDao implements OAuthDao {
	// ~ Instance Fields =====================================
	/**
	 * Sql oauth database writer *
	 */
	private final transient OAuthWriter writer;

	/** Sql oauth database reader **/
	/* private final transient OAuthReader reader; */

	/**
	 * Public constructor *
	 */
	SqlOAuthDao() {
		this.writer = singleton(BridgeOAuthDao.class);
		/* this.reader = singleton(BridgeOAuthDao.class); */
	}

	// ~ Constructors ========================================
	private OAuthWriter getWriter() {
		return this.writer;
	}

	/*
	 * private OAuthReader getReader(){ return this.reader; }
	 */

	// ~ Override Methods ====================================

	/**
	 * Create new authentication code
	 */
	@Override
	public AuthenticationCode add(final AuthenticationCode codeObj) {
		return this.getWriter().add(codeObj);
	}

	/**
     *
     */
	@Override
	public AuthenticationCode remove(final String code) {
		return this.getWriter().remove(code);
	}

}
