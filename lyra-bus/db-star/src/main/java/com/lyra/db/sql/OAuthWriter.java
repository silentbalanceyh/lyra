package com.lyra.db.sql;

import com.lyra.mod.oauth.AuthenticationCode;

/**
 * @author Lang
 * @package com.test.lyra.db.sql
 * @name OAuthWriter
 * @class com.test.lyra.db.sql.OAuthWriter
 * @date Dec 8, 2014 9:48:23 PM
 * @see
 */
public interface OAuthWriter {
	/**
	 * Generate new temporary authentication code
	 *
	 * @param code
	 * @return
	 */
	AuthenticationCode add(final AuthenticationCode codeObj);

	/**
	 * Remote oauth by code value
	 *
	 * @param code
	 * @return
	 */
	AuthenticationCode remove(final String code);
}
