package com.lyra.db.sql.util.stmt;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Logger.error;

import java.util.Map;
import java.util.Set;

import com.lyra.db.sql.util.Restrictions;
import com.lyra.meta.Context;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Constants;
import com.lyra.res.Symbol;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.util.stmt
 * @name UpdateKit
 * @class com.test.lyra.db.sql.util.stmt.UpdateKit
 * @date Dec 2, 2014 2:57:57 PM
 * @see
 */
public final class UpdateKit {
	// ~ Constructors ========================================
	private UpdateKit() {
	}

	// ~ Static Methods ======================================

	/**
	 * Prepare for update statement by id. *
	 */
	public static String prepUpdateById(final Context context,
			final Map<String, Integer> indexMap) {
		final StringBuilder sqlStr = builder(prepUpdate(context,
				indexMap));
		sqlStr.append(WhereKit.prepPkWhere(context, indexMap));
		return sqlStr.toString();
	}

	// Update

	/**
	 * Prepare for update sql statement with all columns. *
	 */
	public static String prepUpdate(final Context context,
			final Map<String, Integer> indexMap) {
		return prepUpdate(context, indexMap, Symbol.T_STR_ARR);
	}

	/**
	 * Prepare for update sql statement with filters *
	 */
	public static String prepUpdate(final Context context,
			final Map<String, Integer> indexMap, final String... filters) {
		final Map<String, String> kvPair = hashMap(true);
		return prepUpdate(context, indexMap, kvPair, filters);
	}

	/**
	 * Prepare for update sql statement with special column=value. *
	 */
	public static String prepUpdate(final Context context,
			final Map<String, Integer> indexMap,
			final Map<String, String> kvPair) {
		return prepUpdate(context, indexMap, kvPair, Symbol.T_STR_ARR);
	}

	/** **/
	public static String prepUpdate(final Context context,
			final Map<String, Integer> indexMap,
			final Map<String, String> kvPair, final String... filters) {
		final StringBuilder updateSql = builder();
		updateSql.append("UPDATE ").append(context.getTable()).append(" SET ");
		// Prepare for update part
		final StringBuilder kvBuf = builder();
		// File sql statement buffer
		fillUCVBuf(context, indexMap, kvPair, kvBuf, filters);
		updateSql.append(kvBuf);
		return updateSql.toString();
	}

	// Private static method
	private static void fillUCVBuf(final Context context,
			final Map<String, Integer> indexMap,
			final Map<String, String> kvPair, final StringBuilder kvBuf,
			final String... filters) {
		if (kvBuf.length() == 0) {
			// Get column list
			final Set<String> columns = MixKit.getColumns(context, filters);
			final String policy = context.getPolicy();
			if (!nullable(policy)) {
				if (policy.trim().equals(Constants.KP_MULTI)) {
					final Set<FieldSchema> schemata = context.getPKs();
					for (final FieldSchema schema : schemata) {
						columns.remove(schema.getColumnName());
					}
				} else {
					final FieldSchema schema = context.getPK();
					columns.remove(schema.getColumnName());
				}
			}
			// Preparing update parts, at the same time fill the index map
			int colIndex = 1;
			for (final String column : columns) {
				String columnVal = nullObj();
				if (kvPair.containsKey(column)) {
					columnVal = kvPair.get(column);
				} else {
					columnVal = Restrictions.Q_MARK;
				}
				kvBuf.append(column).append(" = ").append(columnVal)
						.append(Symbol.C_COMMA);
				indexMap.put(column, colIndex);
				colIndex++;
			}
			kvBuf.delete(kvBuf.length() - 1, kvBuf.length());
			kvBuf.append(Symbol.C_WHITESPACE);
		} else {
			final StringBuilder errMsg = builder();
			errMsg.append(
					"Input buffer parameters should be empty, the length should be 0. Variable: ('kvBuf' length=")
					.append(kvBuf.length()).append(')');
			error(MixKit.class, errMsg.toString());
		}
	}
}
