package com.lyra.meta.json.exp;

import com.lyra.exp.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name ConflictPolicyNMMatchingException
 * @class com.test.lyra.meta.exp.json.ConflictPolicyNMMatchingException
 * @date Nov 25, 2014 5:35:04 PM
 * @see
 */
public class ConflictPolicyNMMatchingException extends AbstractSchemaException {
	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 8619415315339655147L;

	// ~ Constructors ========================================

	/** */
	public ConflictPolicyNMMatchingException(final String policy) {
		super(
				"[E] Defined policy is \""
						+ policy
						+ "\", there should be only one field schema which contain \"policy\" attribute, conflict with more thant one definition.");
	}

	// ~ Override Methods ====================================

	/** */
	@Override
	public int getErrorCode() {
		return -10010;
	}
}
