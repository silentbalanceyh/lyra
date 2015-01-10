package com.lyra.meta.builder.impl;

import static com.lyra.util.instance.BufferInstance.builder;

import org.mockito.internal.verification.Only;

/**
 * @author Lang
 * @package com.test.lyra.meta.test.builder.impl
 * @name DdlHelper
 * @class com.test.lyra.meta.test.builder.impl.DdlHelper
 * @date Dec 1, 2014 1:58:50 PM
 * @see Only help for ddl sql statements
 */
final class DdlHelper {

	// ~ Constructors ========================================
	private DdlHelper() {
	}

	// ~ Static Methods ======================================

	/**
	 * @param table
	 * @return
	 */
	public static String buildCreate(final String table) {
		final StringBuilder sql = builder();
		sql.append("CREATE TABLE ").append(table).append("( ");
		return sql.toString();
	}

	/**
	 * @param table
	 * @return
	 */
	public static String buildDrop(final String table) {
		final StringBuilder sql = builder();
		sql.append("DROP TABLE ").append(table);
		return sql.toString();
	}

	/**
	 * @param table
	 * @return
	 */
	public static String buildUpdate(final String table) {
		final StringBuilder sql = builder();
		sql.append("DROP TABLE ").append(table);
		return sql.toString();
	}

	/**
	 * @return
	 */
	public static String buildFooter() {
		return " )";
	}
}
