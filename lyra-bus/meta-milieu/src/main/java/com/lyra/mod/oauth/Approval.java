package com.lyra.mod.oauth;

import java.io.Serializable;
import java.util.Date;

import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

/**
 * @author Lang
 * @package com.lyra.orb.model.oauth
 * @name Approval
 * @class com.lyra.orb.model.oauth.Approval
 * @date Nov 26, 2014 11:56:03 PM
 * @see
 */
public class Approval implements Serializable {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 6032908106641398374L;

	// ~ Instance Fields =====================================
	/**
	 * User Id *
	 */
	private String userId;
	/**
	 * Client Id *
	 */
	private String clientId;
	/**
	 * OAuth scope *
	 */
	private String scope;
	/**
	 * Approval status *
	 */
	private String status;
	/**
	 * Expires time *
	 */
	private Date expiresAt;
	/**
	 * Last modified time *
	 */
	private Date lastModifiedAt;

	// ~ Constructors ========================================

	/** **/
	public Approval() {
		// For Jackson only
	}

	/**
	 * Constructor by userId and clientId *
	 */
	public Approval(final String userId, final String clientId) {
		setUserId(userId);
		setClientId(clientId);
	}

	// ~ Methods =============================================

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(final String userId) {
		this.userId = userId;
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
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            the scope to set
	 */
	public void setScope(final String scope) {
		this.scope = scope;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @return the expiresAt
	 */
	public Date getExpiresAt() {
		return expiresAt;
	}

	/**
	 * @param expiresAt
	 *            the expiresAt to set
	 */
	public void setExpiresAt(final Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	/**
	 * @return the lastModifiedAt
	 */
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	/**
	 * @param lastModifiedAt
	 *            the lastModifiedAt to set
	 */
	public void setLastModifiedAt(final Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	// ~ hashCode,equals,toString ============================

	/**
     *
     */
	@Override
	public int hashCode() {
		int result = hash(1, this.clientId);
		result = hash(result, this.expiresAt);
		result = hash(result, this.lastModifiedAt);
		result = hash(result, this.scope);
		result = hash(result, this.status);
		result = hash(result, this.userId);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final Approval that = (Approval) obj;
			ret &= equal(this.clientId, that.clientId);
			ret &= equal(this.expiresAt, that.expiresAt);
			ret &= equal(this.lastModifiedAt, that.lastModifiedAt);
			ret &= equal(this.scope, that.scope);
			ret &= equal(this.status, that.status);
			ret &= equal(this.userId, that.userId);
		}
		return ret;
	}
}
