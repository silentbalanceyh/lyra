package com.lyra.db.sql.util.stmt;

import static com.lyra.util.instance.BufferInstance.builder;

import com.lyra.meta.Context;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.util.stmt
 * @name DeleteKit
 * @class com.test.lyra.db.sql.util.stmt.DeleteKit
 * @date Dec 2, 2014 3:03:55 PM
 * @see
 */
public final class DeleteKit {
	// ~ Constructors ========================================
	private DeleteKit() {
	}

	// ~ Static Methods ======================================
	// Delete

	/**
	 * Prepare for deleting statement without where parts. *
	 */
	public static String prepDelete(final Context context) {
		final StringBuilder deleteSql = builder();
		deleteSql.append("DELETE FROM ").append(context.getTable());
		return deleteSql.toString();
	}

	/**
	 * Prepare for deleting statement with id. *
	 */
	public static String prepDeleteById(final Context context) {
		final StringBuilder sqlStr = builder(prepDelete(context));
		sqlStr.append(WhereKit.prepPkWhere(context));
		return sqlStr.toString();
	}
}
