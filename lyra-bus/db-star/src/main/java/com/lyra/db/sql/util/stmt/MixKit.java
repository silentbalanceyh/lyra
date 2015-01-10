package com.lyra.db.sql.util.stmt;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.internal.Validator.nullable;

import java.util.List;
import java.util.Set;

import com.lyra.meta.Context;
import com.lyra.res.Symbol;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.util.stmt
 * @name MixKit
 * @class com.test.lyra.db.sql.util.stmt.MixKit
 * @date Dec 1, 2014 4:34:46 PM
 * @see
 */
public final class MixKit {
	// ~ Constructors ========================================
	private MixKit() {
	}

	// ~ Static Methods ======================================

	/**
	 * Prepare for selecting ( Count ) statement without where parts. *
	 */
	// Function
	public static String prepCount(final Context context) {
		final StringBuilder countSql = builder();
		countSql.append("SELECT COUNT(*) FROM ").append(context.getTable());
		return countSql.toString();
	}

	// Select

	/**
	 * Prepare for selecting statement without where parts. *
	 */
	public static String prepSelect(final Context context) {
		final StringBuilder selectSql = builder();
		selectSql.append("SELECT * FROM ").append(context.getTable());
		return selectSql.toString();
	}

	/**
	 * Prepare for selecting statement with id. *
	 */
	public static String prepSelectById(final Context context) {
		final StringBuilder sqlStr = builder(
				MixKit.prepSelect(context));
		sqlStr.append(WhereKit.prepPkWhere(context));
		return sqlStr.toString();
	}

	/**
	 * Prepare for order by clause ( Prepared ) *
	 */
	public static String prepOrderBy(final Context context) {
		final StringBuilder orderBy = builder();
		final List<String> columnList = context.getOrderColumns();
		orderBy.append(Symbol.C_WHITESPACE).append("ORDER BY")
				.append(Symbol.C_WHITESPACE);
		for (final String columnOd : columnList) {
			orderBy.append(columnOd).append(Symbol.C_COMMA);
		}
		orderBy.delete(orderBy.length() - 1, orderBy.length());
		return orderBy.toString();
	}

	/**
	 * @param kvPair
	 * @param kvBuf
	 * @param filters
	 */
	// Update
	public static Set<String> getColumns(final Context context,
			final String... colFilters) {
		// TreeSet object is kept nature sequence of column name
		final Set<String> rSet = hashSet();
		for (final String field : context.getFields().keySet()) {
			rSet.add(context.getFtoC().get(field));
		}
		// Remove columns which are in colFilters
		if (!nullable(colFilters) && colFilters.length > 0) {
			for (final String colFilter : colFilters) {
				if (!nullable(colFilter) && rSet.contains(colFilter)) {
					rSet.remove(colFilter);
				}
			}
		}
		return rSet;
	}
}
