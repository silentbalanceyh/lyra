package com.lyra.mod.oauth;

import java.io.Serializable;
import java.util.Arrays;

import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

/**
 * @author Lang
 * @package com.lyra.orb.model.oauth
 * @name AuthenticationCode
 * @class com.lyra.orb.model.oauth.AuthenticationCode
 * @date Nov 26, 2014 11:02:08 PM
 * @see Mapping to database OAU_CODE table
 */
public class AuthenticationCode implements Serializable {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 3725738260952697620L;

	// ~ Instance Fields =====================================
	/**
	 * OAuth code string content *
	 */
	private String code;
	/**
	 * OAuth code content of byte[] stream data *
	 */
	private byte[] authentication;

	// ~ Constructors ========================================

	/**
	 * Default public constructor *
	 */
	public AuthenticationCode() {
		// For Jackson only
	}

	/**
	 * Constructor by code *
	 */
	public AuthenticationCode(final String code) {
		setCode(code);
	}

	// ~ Methods =============================================

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the authentication
	 */
	public byte[] getAuthentication() {
		return Arrays.copyOf(authentication, authentication.length);
	}

	/**
	 * @param authentication
	 *            the authentication to set
	 */
	public void setAuthentication(final byte[] authentication) {
		this.authentication = Arrays.copyOf(authentication,
				authentication.length);
	}

	// ~ hashCode,equals,toString ============================

	/**
     *
     */
	@Override
	public int hashCode() {
		int result = hash(1, this.code);
		result = hash(result, this.authentication);
		return result;
	}

	/**
     *
     **/
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final AuthenticationCode that = (AuthenticationCode) obj;
			ret &= equal(this.code, that.code);
			ret &= equal(this.authentication, that.authentication);
		}
		return ret;
	}

}
