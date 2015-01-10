package com.lyra.meta.json.exp;

import com.lyra.exp.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name ConflictPolicyMMatchingException
 * @class com.test.lyra.meta.exp.json.ConflictPolicyMMatchingException
 * @date Nov 25, 2014 5:41:13 PM
 * @see
 */
public class ConflictPolicyMMatchingException extends AbstractSchemaException {
	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 7969192468606781399L;

	// ~ Constructors ========================================

	/** */
	public ConflictPolicyMMatchingException() {
		super(
				"[E] Defined policy is \"MULTI\", there should be more than one fields schema which contain \"policy\" attribute with value \"MULTI\", conflict with only one definition.");
	}

	// ~ Override Methods ====================================

	/** */
	@Override
	public int getErrorCode() {
		return -10011;
	}
}
