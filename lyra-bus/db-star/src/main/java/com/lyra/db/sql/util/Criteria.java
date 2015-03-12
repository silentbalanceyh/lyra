package com.lyra.db.sql.util;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.internal.Validator.nullable;

import com.lyra.res.Symbol;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.util
 * @name Criteria
 * @class com.test.lyra.db.sql.util.Criteria
 * @date Nov 16, 2014 9:26:27 AM
 * @see
 */
public final class Criteria {

	// ~ Instance Fields =====================================
	/**
	 * Where clause statement *
	 */
	private final transient StringBuilder whereClause;

	// ~ Constructors ========================================
	Criteria() {
		whereClause = builder();
		whereClause.append(Symbol.C_WHITESPACE).append(Restrictions.WHERE);
	}

	// ~ Override Methods ====================================

	/**
	 * Return final where clause. *
	 */
	@Override
	public String toString() {
		return nullable(whereClause) ? Symbol.STR_EMPTY : whereClause
				.toString();
	}

	// ~ Methods =============================================

	/**
	 * @param criterion
	 * @return
	 */
	public Criteria add(final Criterion criterion) {
		if (!whereClause.toString().trim().endsWith(Restrictions.WHERE)) {
			whereClause.append(Symbol.C_WHITESPACE).append(
					Restrictions._AND);
		}
		whereClause.append(Symbol.C_WHITESPACE).append(criterion);
		return this;
	}
}
