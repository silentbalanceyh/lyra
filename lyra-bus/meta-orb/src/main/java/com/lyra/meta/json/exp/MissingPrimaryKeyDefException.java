package com.lyra.meta.json.exp;

import com.lyra.exp.AbstractSchemaException;

/**
 * @author Lang
 * @package com.test.lyra.meta.exp.json
 * @name MissingPrimaryKeyDefException
 * @class com.test.lyra.meta.exp.json.MissingPrimaryKeyDefException
 * @date Nov 25, 2014 3:48:44 AM
 * @see
 */
public class MissingPrimaryKeyDefException extends AbstractSchemaException {

	// ~ Static Fields =======================================
	/**
     *
     */
	private static final long serialVersionUID = 1747210244583970940L;

	// ~ Constructors ========================================

	/** */
	public MissingPrimaryKeyDefException() {
		super(
				"[E] You must defined \"pKeys\" under \"__key__\" attribute, every model must have at least one primary key.");
	}

	// ~ Override Methods ====================================

	/** */
	@Override
	public int getErrorCode() {
		return -10004;
	}
}
