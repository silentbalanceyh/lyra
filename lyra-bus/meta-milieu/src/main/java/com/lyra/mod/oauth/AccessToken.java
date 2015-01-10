package com.lyra.mod.oauth;

import java.io.Serializable;
import java.util.Arrays;

import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

/**
 * @author Lang
 * @package com.lyra.orb.model.oauth
 * @name AccessToken
 * @class com.lyra.orb.model.oauth.AccessToken
 * @date Nov 26, 2014 11:49:06 PM
 * @see Mapping to OAU_ACCESS_TOKEN
 */
public class AccessToken implements Serializable {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 6312015262611084324L;

	// ~ Instance Fields =====================================
	/**
	 * Client id *
	 */
	private String clientId;
	/**
	 * Authentication Id *
	 */
	private String authenticationId;
	/**
	 * User name or login account *
	 */
	private String username;
	/**
	 * Token content of byte[] stream data *
	 */
	private byte[] token;
	/**
	 * RefreshToken content of byte[] stream data *
	 */
	private byte[] refreshToken;
	/**
	 * Authentication content of byte[] stream data *
	 */
	private byte[] authentication;

	// ~ Constructors ========================================

	/** **/
	public AccessToken() {
		// For Jackson only
	}

	/**
	 * Consturctor by clientId and username *
	 */
	public AccessToken(final String clientId, final String username) {
		setClientId(clientId);
		setUsername(username);
	}

	// ~ Methods =============================================

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the authenticationId
	 */
	public String getAuthenticationId() {
		return authenticationId;
	}

	/**
	 * @param authenticationId
	 *            the authenticationId to set
	 */
	public void setAuthenticationId(final String authenticationId) {
		this.authenticationId = authenticationId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

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
	 * @return the refreshToken
	 */
	public byte[] getRefreshToken() {
		return Arrays.copyOf(refreshToken, refreshToken.length);
	}

	/**
	 * @param refreshToken
	 *            the refreshToken to set
	 */
	public void setRefreshToken(final byte[] refreshToken) {
		this.refreshToken = Arrays.copyOf(refreshToken, refreshToken.length);
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
		int result = hash(1, this.clientId);
		result = hash(result, this.authentication);
		result = hash(result, this.authenticationId);
		result = hash(result, this.refreshToken);
		result = hash(result, this.token);
		result = hash(result, this.username);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final AccessToken that = (AccessToken) obj;
			ret &= equal(this.clientId, that.clientId);
			ret &= equal(this.authentication, that.authentication);
			ret &= equal(this.authenticationId, that.authenticationId);
			ret &= equal(this.refreshToken, that.refreshToken);
			ret &= equal(this.token, that.token);
			ret &= equal(this.username, that.username);
		}
		return ret;
	}
}
