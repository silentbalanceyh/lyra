package com.lyra.meta.json.exp;

import com.lyra.exp.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name MissingFieldAttrException
 * @class com.test.lyra.meta.exp.json.MissingFieldAttrException
 * @date Nov 25, 2014 4:51:01 AM
 * @see
 */
public class MissingFieldAttrException extends AbstractSchemaException {
	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = -7324101923432179819L;

	// ~ Constructors ========================================

	/** */
	public MissingFieldAttrException(final int fieldCount) {
		super(
				"[E] There are "
						+ fieldCount
						+ " fields missing required attributes, every field must contain required four attributes.(name,type,columnName,columnType)");
	}

	// ~ Override Methods ====================================

	/** */
	@Override
	public int getErrorCode() {
		return -10006;
	}
}
