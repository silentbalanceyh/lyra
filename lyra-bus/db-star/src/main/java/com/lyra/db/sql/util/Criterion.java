package com.lyra.db.sql.util;

import static com.lyra.util.internal.Validator.nullable;

import com.lyra.res.Symbol;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.util
 * @name Criterion
 * @class com.test.lyra.db.sql.util.Criterion
 * @date Nov 16, 2014 10:49:18 AM
 * @see
 */
public final class Criterion {

	// ~ Instance Fields =====================================
	/**
	 * Column name of database table. *
	 */
	private transient String leftExp;
	/**
	 * Column value which should be passed. *
	 */
	private transient String rightExp;
	/**
	 * Connect word such as =,<,> *
	 */
	private transient String connectWord;

	// ~ Constructors ========================================
	private Criterion(final String leftExp, final String connectWord,
			final String rightExp) {
		this.leftExp = leftExp;
		this.rightExp = rightExp;
		this.connectWord = connectWord;
	}

	// ~ Static Methods ======================================

	/**
	 * Initializing method to create new Criterion Object
	 *
	 * @param leftExp
	 * @param connectWord
	 * @param rightExp
	 * @return
	 */
	public static Criterion instance(final String leftExp,
			final String connectWord, final String rightExp) {
		return new Criterion(leftExp, connectWord, rightExp);
	}

	/**
	 * @param leftExp
	 * @param connectWord
	 * @param rightExp
	 * @return
	 */
	public static Criterion instance(final Criterion leftExp,
			final String connectWord, final Criterion rightExp) {
		return new Criterion(leftExp.toString(), connectWord,
				rightExp.toString());
	}

	// ~ Methods =============================================

	/**
	 * @param leftExp
	 *            the leftExp to set
	 */
	public void setLeftExp(final String leftExp) {
		this.leftExp = leftExp;
	}

	/**
	 * @param rightExp
	 *            the rightExp to set
	 */
	public void setRightExp(final String rightExp) {
		this.rightExp = rightExp;
	}

	/**
	 * @param connectWord
	 *            the connectWord to set
	 */
	public void setConnectWord(final String connectWord) {
		this.connectWord = connectWord;
	}

	// ~ hashCode,equals,toString ============================

	/**
	 * Override the toString method to generate sql clause
	 */
	@Override
	public String toString() {
		final String retStr = (nullable(this.leftExp) ? Symbol.STR_EMPTY
				: this.leftExp)
				+ Symbol.C_WHITESPACE
				+ this.connectWord
				+ Symbol.C_WHITESPACE
				+ (nullable(this.rightExp) ? Symbol.STR_EMPTY
						: this.rightExp);
		return retStr.intern();
	}
}
