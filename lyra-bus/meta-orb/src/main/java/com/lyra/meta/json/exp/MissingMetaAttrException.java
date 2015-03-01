package com.lyra.meta.json.exp;

import com.lyra.exception.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name MissingMetaAttrException
 * @class com.test.lyra.meta.exp.json.MissingMetaAttrException
 * @date Nov 25, 2014 4:16:45 AM
 * @see
 */
public class MissingMetaAttrException extends AbstractSchemaException {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = -7033446944797854952L;

	// ~ Constructors ========================================

	/** */
	public MissingMetaAttrException(final String attrName) {
		super(
				"[E] Attribute \""
						+ attrName
						+ "\" of \"__meta__\" missed, \"__meta__\" must contain four attributes (table,name,package,fullname).");
	}

	// ~ Override Methods ====================================

	/** */
	@Override
	public int getErrorCode() {
		return -10005;
	}
}
