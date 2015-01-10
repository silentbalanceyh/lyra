package com.lyra.meta.json.exp;

import com.lyra.exp.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name MissingPrimaryKeyPolicyException
 * @class com.test.lyra.meta.exp.json.MissingPrimaryKeyPolicyException
 * @date Nov 25, 2014 6:49:53 AM
 * @see
 */
public class MissingPrimaryKeyPolicyException extends AbstractSchemaException {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 4715358614107305712L;

	// ~ Constructors ========================================

	/** */
	public MissingPrimaryKeyPolicyException(final String pKeyName) {
		super(
				"[E] Primary Key \""
						+ pKeyName
						+ "\"'s \"policy\" attribute missed, please set \"policy\" for \""
						+ pKeyName + "\".");
	}

	// ~ Override Methods ====================================

	/** */
	@Override
	public int getErrorCode() {
		return -10008;
	}
}
