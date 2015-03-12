package com.lyra.db.sql.oauth.impl;

import com.lyra.db.sql.OAuthReader;
import com.lyra.db.sql.OAuthWriter;
import com.lyra.mod.oauth.AuthenticationCode;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.oauth.impl
 * @name BridgeOAuthDao
 * @class com.test.lyra.db.sql.oauth.impl.BridgeOAuthDao
 * @date Dec 8, 2014 9:48:01 PM
 * @see
 */
public final class BridgeOAuthDao implements OAuthReader, OAuthWriter {

	// ~ Instance Fields =====================================
	/**
	 * Security reference and this class will call methods. *
	 */
	private transient final AbstractOAuthDao ref;

	// ~ Constructors ========================================
	BridgeOAuthDao() {
		this.ref = AbstractOAuthDao.init();
	}

	// ~ Override Methods ====================================

	/** **/
	@Override
	public AuthenticationCode add(final AuthenticationCode codeObj) {
		return this.getRef().add(codeObj);
	}

	/**
     *
     */
	@Override
	public AuthenticationCode remove(final String code) {
		return this.getRef().remove(code);
	}

	// ~ Private Methods =====================================

	/**
	 * Return current oauth dao *
	 */
	private AbstractOAuthDao getRef() {
		return this.ref;
	}
}
