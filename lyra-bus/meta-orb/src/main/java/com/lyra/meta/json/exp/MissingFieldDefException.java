package com.lyra.meta.json.exp;

import com.lyra.exp.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name MissingFieldDefException
 * @class com.test.lyra.meta.exp.json.MissingFieldDefException
 * @date Nov 25, 2014 3:09:41 AM
 * @see
 */
public class MissingFieldDefException extends AbstractSchemaException {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 4606286274120116783L;

	// ~ Constructors ========================================

	/** */
	public MissingFieldDefException() {
		super(
				"[E] You must defined \"__fields__\" in the root of your schema file, \"__fields__\" attribute missed.");
	}

	// ~ Override Methods ====================================

	/** */
	@Override
	public int getErrorCode() {
		return -10003;
	}
}
