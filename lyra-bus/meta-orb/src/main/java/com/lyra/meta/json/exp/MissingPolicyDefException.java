package com.lyra.meta.json.exp;

import com.lyra.exception.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name MissingPolicyDefException
 * @class com.test.lyra.meta.exp.json.MissingPolicyDefException
 * @date Nov 25, 2014 5:31:52 AM
 * @see
 */
public class MissingPolicyDefException extends AbstractSchemaException {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = -8228480174072277847L;

	// ~ Constructors ========================================

	/** */
	public MissingPolicyDefException() {
		super(
				"[E] There is no \"policy\" attribute existing in defined primary keys, please check field schemata to set policy for primary key(s).");
	}

	// ~ Override Methods ====================================

	/** */
	@Override
	public int getErrorCode() {
		return -10007;
	}
}
