package com.lyra.db.sql.util.stmt;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.logger.Logger.error;

import java.util.Map;
import java.util.Set;

import com.lyra.meta.Context;
import com.lyra.res.Symbol;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.util.stmt
 * @name InsertKit
 * @class com.test.lyra.db.sql.util.stmt.InsertKit
 * @date Dec 2, 2014 2:58:18 PM
 * @see
 */
public final class InsertKit {
	// ~ Constructors ========================================
	private InsertKit() {
	}

	// ~ Static Methods ======================================
	// Insert

	/**
	 * Prepare for insert sql statement with all columns *
	 */
	public static String prepInsert(final Context context,
			final Map<String, Integer> indexMap) {
		return prepInsert(context, indexMap, Symbol.T_STR_ARR);
	}

	/**
	 * Prepare for inserting statement *
	 */
	public static String prepInsert(final Context context,
			final Map<String, Integer> indexMap, final String... filters) {
		final Map<String, String> kvPair = hashMap(true);
		return prepInsert(context, indexMap, kvPair, filters);
	}

	/**
	 * Prepare for insert sql statement with all columns and special
	 * column=value
	 */
	public static String prepInsert(final Context context,
			final Map<String, Integer> indexMap,
			final Map<String, String> kvPair) {
		return prepInsert(context, indexMap, kvPair, Symbol.T_STR_ARR);
	}

	/**
	 * Prepare for inserting statement *
	 */
	public static String prepInsert(final Context context,
			final Map<String, Integer> indexMap,
			final Map<String, String> kvPair, final String... filters) {
		final StringBuilder insertSql = builder();
		insertSql.append("INSERT INTO ").append(context.getTable());
		// Prepare buffer for column/value part in insert sql statement
		final StringBuilder columnBuf = builder();
		final StringBuilder valueBuf = builder();
		// Fill sql statement buffer
		fillICVBuf(context, indexMap, kvPair, columnBuf, valueBuf, filters);
		insertSql.append(Symbol.C_WHITESPACE).append(columnBuf)
				.append(" VALUES ").append(valueBuf);
		return insertSql.toString();
	}

	/**
	 * Workflow: [1] Remove columns which have been in filters. [2] Prepare the
	 * SQL statement with left columns. [3] Replace some special values which
	 * are in kvPair ? => Special Value
	 *
	 * @param kvPair
	 * @param columnBuf
	 * @param valueBuf
	 * @param filters
	 */
	private static void fillICVBuf(final Context context,
			final Map<String, Integer> indexMap,
			final Map<String, String> kvPair, final StringBuilder columnBuf,
			final StringBuilder valueBuf, final String... filters) {
		if (columnBuf.length() == 0 && valueBuf.length() == 0) {
			// Column/Value start charactor
			columnBuf.append(Symbol.C_L_BRACKET);
			valueBuf.append(Symbol.C_L_BRACKET);
			// Get column list
			final Set<String> columns = MixKit.getColumns(context, filters);
			int colIndex = 1; // Fill index map data structure here.
			for (final String column : columns) {
				columnBuf.append(column).append(Symbol.C_COMMA);
				if (kvPair.containsKey(column)) {
					valueBuf.append(kvPair.get(column)).append(
							Symbol.C_COMMA);
				} else {
					valueBuf.append("?,");
				}
				indexMap.put(column, colIndex);
				colIndex++;
			}
			// Delete the last ','
			columnBuf.delete(columnBuf.length() - 1, columnBuf.length());
			columnBuf.append(Symbol.C_R_BRACKET);

			valueBuf.delete(valueBuf.length() - 1, valueBuf.length());
			valueBuf.append(Symbol.C_R_BRACKET);
		} else {
			final StringBuilder errMsg = builder();
			errMsg.append(
					"Input buffer parameters should be empty,the length should be 0. Variables: ('columnBuf' length=")
					.append(columnBuf.length()).append(",'valueBuf' length=")
					.append(valueBuf.length()).append(')');
			error(MixKit.class, errMsg.toString());
		}
	}
}
