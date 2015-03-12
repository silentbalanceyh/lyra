package com.lyra.exp;

/**
 * System exception, describe invalid system definition
 *
 * @author Lang
 * @package com.lyra.exp
 * @name AbstractSystemException
 * @class com.lyra.exp.AbstractSystemException
 * @date Oct 10, 2014 4:12:56 PM
 * @see
 */
public abstract class AbstractSystemException extends RuntimeException {
	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = -7714033567838757744L;

	// ~ Constructors ========================================

	/**
	 * @param message
	 */
	public AbstractSystemException(final String message) {
		super(message);
	}

	// ~ Abstract Methods ====================================

	/**
	 * Get type of schema exception. *
	 */
	public abstract int getErrorCode();
}
