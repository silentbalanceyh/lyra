package com.lyra.exp;

/**
 * Schema exception, describe invalid schema definition
 *
 * @author Lang
 * @package com.lyra.exp
 * @name AbstractSchemaException
 * @class com.lyra.exp.AbstractSchemaException
 * @date Oct 10, 2014 4:13:21 PM
 * @see
 */
public abstract class AbstractSchemaException extends Exception {
	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 9007713506522349143L;

	// ~ Constructors ========================================

	/**
	 * @param message
	 */
	public AbstractSchemaException(final String message) {
		super(message);
	}

	// ~ Abstract Methods ====================================

	/**
	 * Get type of schema exception. *
	 */
	public abstract int getErrorCode();
}
