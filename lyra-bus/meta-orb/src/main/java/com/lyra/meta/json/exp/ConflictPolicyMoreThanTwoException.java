package com.lyra.meta.json.exp;

import com.lyra.exception.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name ConflictPolicyMoreThanTwoException
 * @class com.test.lyra.meta.exp.json.ConflictPolicyMoreThanTwoException
 * @date Dec 5, 2014 4:09:58 PM
 * @see
 */
public class ConflictPolicyMoreThanTwoException extends AbstractSchemaException {
	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 6948594379907224751L;

	// ~ Constructors ========================================

	/**
     *
     */
	public ConflictPolicyMoreThanTwoException(final String policies) {
		super(
				"[E] Defined policy is \""
						+ policies
						+ "\", there should be only one primary key \"policy\" exsiting in schema definition.");
	}

	// ~ Override Methods ====================================

	/**
     *
     */
	@Override
	public int getErrorCode() {
		return -10009;
	}
}
