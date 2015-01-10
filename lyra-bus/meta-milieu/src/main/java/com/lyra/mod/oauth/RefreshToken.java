package com.lyra.mod.oauth;

import java.io.Serializable;
import java.util.Arrays;

import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

/**
 * @author Lang
 * @package com.lyra.orb.model.oauth
 * @name RefreshToken
 * @class com.lyra.orb.model.oauth.RefreshToken
 * @date Nov 26, 2014 11:39:49 PM
 * @see Mapping to database OAU_REFRESH_TOKEN
 */
public class RefreshToken implements Serializable {
	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 2325486085001606214L;

	// ~ Instance Fields =====================================
	/**
	 * Token content *
	 */
	private byte[] token;
	/**
	 * Authentication content *
	 */
	private byte[] authentication;

	// ~ Constructors ========================================

	/**
	 * Default constructor *
	 */
	public RefreshToken() {
		// For Jackson only
	}

	/**
	 * Constructory by data *
	 */
	public RefreshToken(final byte[] token, final byte[] authentication) {
		setToken(token);
		setAuthentication(authentication);
	}

	// ~ Methods =============================================

	/**
	 * @return the token
	 */
	public byte[] getToken() {
		return Arrays.copyOf(token, token.length);
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(final byte[] token) {
		this.token = Arrays.copyOf(token, token.length);
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
		int result = hash(1, this.token);
		result = hash(result, this.authentication);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final RefreshToken that = (RefreshToken) obj;
			ret &= equal(this.token, that.token);
			ret &= equal(this.authentication, that.authentication);
		}
		return ret;
	}
}
