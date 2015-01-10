package com.lyra.meta.json.exp;

import com.lyra.exp.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name ConflictPolicyPkTypeMatchingException
 * @class com.test.lyra.meta.exp.json.ConflictPolicyPkTypeMatchingException
 * @date Oct 11, 2014 12:27:44 AM
 * @see
 */
public class ConflictPolicyPkTypeMatchingException extends
		AbstractSchemaException {
	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 7500739848236591613L;

	// ~ Constructors ========================================

	/**
     *
     */
	public ConflictPolicyPkTypeMatchingException() {
		super(
				"[E] Your primary key's policy is not matching with SQL data type, please use correct SQL data type to define primary key.");
	}

	// ~ Override Methods ====================================

	/**
     *
     */
	@Override
	public int getErrorCode() {
		return -10012;
	}

}
