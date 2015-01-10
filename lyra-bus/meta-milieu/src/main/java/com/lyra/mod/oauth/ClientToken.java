package com.lyra.mod.oauth;

import java.io.Serializable;
import java.util.Arrays;

import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

/**
 * @author Lang
 * @package com.lyra.orb.model.oauth
 * @name ClientToken
 * @class com.lyra.orb.model.oauth.ClientToken
 * @date Nov 26, 2014 11:17:34 PM
 * @see Mapping to database OAU_CLIENT_TOKEN
 */
public class ClientToken implements Serializable {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = -6322146688020650738L;

	// ~ Instance Fields =====================================
	/**
	 * Authentication Id *
	 */
	private String authenticationId;
	/**
	 * Client Id *
	 */
	private String clientId;
	/**
	 * User name or login account here *
	 */
	private String username;
	/**
	 * Token content of byte[] stream data *
	 */
	private byte[] token;

	// ~ Constructors ========================================

	/**
	 * Default constructor *
	 */
	public ClientToken() {
		// For Jackson only
	}

	/**
	 * Constructor by clientId and username *
	 */
	public ClientToken(final String clientId, final String username) {
		setClientId(clientId);
		setUsername(username);
	}

	// ~ Methods =============================================

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

	// ~ hashCode,equals,toString ============================

	/**
     *
     */
	@Override
	public int hashCode() {
		int result = hash(1, this.token);
		result = hash(result, this.username);
		result = hash(result, this.clientId);
		result = hash(result, this.authenticationId);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final ClientToken that = (ClientToken) obj;
			ret &= equal(this.token, that.token);
			ret &= equal(this.username, that.username);
			ret &= equal(this.clientId, that.clientId);
			ret &= equal(this.authenticationId, that.authenticationId);
		}
		return ret;
	}

}
