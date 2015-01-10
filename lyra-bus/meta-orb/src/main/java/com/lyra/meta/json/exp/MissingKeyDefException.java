package com.lyra.meta.json.exp;

import com.lyra.exp.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name MissingKeyDefException
 * @class com.test.lyra.meta.exp.json.MissingKeyDefException
 * @date Nov 25, 2014 2:43:14 AM
 * @see
 */
public class MissingKeyDefException extends AbstractSchemaException {

	// ~ Static Fields =======================================
	/** */
	private static final long serialVersionUID = -2823599085384029796L;

	// ~ Constructors ========================================

	/** */
	public MissingKeyDefException() {
		super(
				"[E] You must defined \"__key__\" in the root of your schema file, \"__key__\" attribute missed.");
	}

	// ~ Override Methods ====================================

	/** */
	@Override
	public int getErrorCode() {
		return -10001;
	}
}
