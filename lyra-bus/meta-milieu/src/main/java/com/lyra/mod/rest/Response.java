package com.lyra.mod.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.stereotype.Component;

import java.util.Map;

import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

/**
 * @author Lang
 * @package com.lyra.orb.model.rest
 * @name Response
 * @class com.lyra.orb.model.rest.RespMessage
 * @date Nov 26, 2014 11:00:41 PM
 * @see Restful web service response message format.
 */
@Component
public final class Response {

	// ~ Instance Fields =====================================
	/**
	 * Error category *
	 */
	@JsonProperty("error")
	private String error;
	/**
	 * Error description *
	 */
	@JsonProperty("description")
	private String description;
	/**
	 * Http status code *
	 */
	@JsonProperty("statusCode")
	private String statusCode;
	/**
	 * Data of response *
	 */
	@JsonProperty("data")
	private Map<String, String> data;

	// ~ Constructors ========================================

	/**
	 * Public constructor. *
	 */
	public Response() {
		// For Jackson serialization
	}

	/**
	 * Public constructor. *
	 */
	public Response(final String error, final String description) {
		setError(error);
		setDescription(description);
	}

	// ~ Methods =============================================

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(final String error) {
		this.error = error;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(final String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the data
	 */
	public Map<String, String> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(final Map<String, String> data) {
		this.data = data;
	}

	// ~ hashCode,equals,toString ============================

	/**
     *
     */
	@Override
	public int hashCode() {
		int result = hash(1, this.data);
		result = hash(result, this.description);
		result = hash(result, this.error);
		result = hash(result, this.statusCode);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final Response that = (Response) obj;
			ret &= equal(this.data, that.data);
			ret &= equal(this.description, that.description);
			ret &= equal(this.error, that.error);
			ret &= equal(this.statusCode, that.statusCode);
		}
		return ret;
	}
}
