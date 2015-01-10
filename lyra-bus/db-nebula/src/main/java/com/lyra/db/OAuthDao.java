package com.lyra.db;

import com.lyra.mod.oauth.AuthenticationCode;

/**
 * @author Lang
 * @package com.test.lyra.db.test
 * @name OAuthDao
 * @class com.test.lyra.db.test.OAuthDao
 * @date 2014年11月27日 上午3:36:22
 * @see
 */
public interface OAuthDao {
	/**
	 * @param codeObj
	 * @return
	 */
	AuthenticationCode add(final AuthenticationCode codeObj);

	/**
	 * @param code
	 * @return
	 */
	AuthenticationCode remove(final String code);
}
