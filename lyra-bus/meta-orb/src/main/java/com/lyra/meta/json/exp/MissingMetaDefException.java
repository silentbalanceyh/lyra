package com.lyra.meta.json.exp;

import com.lyra.exception.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name MissingMetaDefException
 * @class com.test.lyra.meta.exp.json.MissingMetaDefException
 * @date Nov 25, 2014 3:06:57 AM
 * @see
 */
public class MissingMetaDefException extends AbstractSchemaException {
	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = -7089763880305784404L;

	// ~ Constructors ========================================

	/** */
	public MissingMetaDefException() {
		super(
				"[E] You must defined \"__meta__\" in the root of your schema file, \"__meta__\" attribute missed.");
	}

	// ~ Override Methods ====================================

	/** */
	@Override
	public int getErrorCode() {
		return -10002;
	}
}
