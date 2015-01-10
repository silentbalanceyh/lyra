package com.lyra.db.sql.util;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.internal.Validator.nullable;

import java.util.Collection;

import com.lyra.res.Symbol;

/**
 * @author Lang
 * @see
 */
public final class Restrictions {

	// ~ Static Fields =======================================
	/**
	 * Equal charactor *
	 */
	public static final String _EQ = "=";
	/**
	 * Not Equal charactor *
	 */
	public static final String NOT_EQ = "<>";
	/**
	 * Less Than charactor *
	 */
	public static final String LESS_THAN = "<";
	/**
	 * Less equal Than charactor *
	 */
	public static final String LESS_EQUAL_THAN = "<=";
	/**
	 * Great than charactor *
	 */
	public static final String GREAT_THAN = ">";
	/**
	 * Great equal than charactor *
	 */
	public static final String GREAT_EQUAL_THAN = ">=";
	/**
	 * Question Mark charactor *
	 */
	public static final String Q_MARK = "?";
	/**
	 * And connect word *
	 */
	public static final String _AND = "AND";
	/**
	 * Or connect word *
	 */
	public static final String _OR = "OR";
	/**
	 * Null checking *
	 */
	public static final String IS_NULL = "IS NULL";
	/**
	 * Not Null checking *
	 */
	public static final String IS_NOT_NULL = "IS NOT NULL";
	/**
	 * Input statement *
	 */
	public static final String _IN = "IN";
	/**
	 * Not in statement *
	 */
	public static final String NOT_IN = "NOT IN";
	/**
	 * Like statement for matching. *
	 */
	public static final String _LIKE = "LIKE";
	/**
	 * Not like statement for matching. *
	 */
	public static final String UN_LIKE = "NOT LIKE";
	/**
	 * Where statement *
	 */
	public static final String WHERE = "WHERE";

	/**
	 * Like statement's mode *
	 */
	public static enum MatchMode {
		ANYWHERE, START, END, EXACT
	}

	// ~ Constructors ========================================
	private Restrictions() {
	}

	// ~ Static Methods ======================================

	/**
	 * {COLUMN = value}: Equal method for direct sql statement
	 *
	 * @param colName
	 * @param value
	 * @return
	 */
	public static Criterion eq(final String colName, final Object value) {
		final String liberal = extractValue(value);
		return Criterion.instance(colName, _EQ, liberal);
	}

	/**
	 * {COLUMN = ?}: Equal method for prepared sql statement
	 *
	 * @param colName
	 * @return
	 */
	public static Criterion eq(final String colName) {
		return eq(colName, Q_MARK);
	}

	/**
	 * {COLUMN <> value}: Not equal method for direct sql statement
	 *
	 * @param colName
	 * @param value
	 * @return
	 */
	public static Criterion ne(final String colName, final Object value) {
		final String liberal = extractValue(value);
		return Criterion.instance(colName, NOT_EQ, liberal);
	}

	/**
	 * {COLUMN <> ?}: Not equal method for prepared sql statement
	 *
	 * @param colName
	 * @return
	 */
	public static Criterion ne(final String colName) {
		return ne(colName, Q_MARK);
	}

	/**
	 * Less than method for direct sql statement
	 *
	 * @param colName
	 * @param value
	 * @return
	 */
	public static Criterion lt(final String colName, final Object value) {
		final String liberal = extractValue(value);
		return Criterion.instance(colName, LESS_THAN, liberal);
	}

	/**
	 * Less than method for prepared sql statement
	 *
	 * @param colName
	 * @return
	 */
	public static Criterion lt(final String colName) {
		return lt(colName, Q_MARK);
	}

	/**
	 * Less equal than method for direct sql statement
	 *
	 * @param colName
	 * @param value
	 * @return
	 */
	public static Criterion le(final String colName, final Object value) {
		final String liberal = extractValue(value);
		return Criterion.instance(colName, LESS_EQUAL_THAN, liberal);
	}

	/**
	 * Less equal than method for prepared sql statement
	 *
	 * @param colName
	 * @return
	 */
	public static Criterion le(final String colName) {
		return le(colName, Q_MARK);
	}

	/**
	 * Great than method for direct sql statement
	 *
	 * @param colName
	 * @param value
	 * @return
	 */
	public static Criterion gt(final String colName, final Object value) {
		final String liberal = extractValue(value);
		return Criterion.instance(colName, GREAT_THAN, liberal);
	}

	/**
	 * Great than method for prepared sql statement
	 *
	 * @param colName
	 * @return
	 */
	public static Criterion gt(final String colName) {
		return gt(colName, Q_MARK);
	}

	/**
	 * Great equal than method for direct sql statement
	 *
	 * @param colName
	 * @param value
	 * @return
	 */
	public static Criterion ge(final String colName, final Object value) {
		final String liberal = extractValue(value);
		return Criterion.instance(colName, GREAT_EQUAL_THAN, liberal);
	}

	/**
	 * Great equal than method for prepared sql statement
	 *
	 * @param colName
	 * @return
	 */
	public static Criterion ge(final String colName) {
		return ge(colName, Q_MARK);
	}

	/**
	 * The column name should be in the range.
	 *
	 * @param colName
	 * @param lValue
	 * @param rValue
	 * @return
	 */
	public static Criterion between(final String colName, final Object lValue,
			final Object rValue) {
		final Criterion lCriterion = ge(colName, lValue);
		final Criterion rCriterion = le(colName, rValue);
		return and(lCriterion, rCriterion);
	}

	/**
	 * @param colName
	 * @param value
	 * @param mode
	 * @return
	 */
	public static Criterion like(final String colName, final Object value,
			final MatchMode mode) {
		final String liberal = extractValue(value, mode);
		return Criterion.instance(colName, _LIKE, liberal);
	}

	/**
	 * @param colName
	 * @param value
	 * @return
	 */
	public static Criterion like(final String colName, final Object value) {
		return like(colName, value, MatchMode.ANYWHERE);
	}

	/**
	 * @param colName
	 * @param value
	 * @param mode
	 * @return
	 */
	public static Criterion ilike(final String colName, final Object value,
			final MatchMode mode) {
		final String liberal = extractValue(value, mode);
		return Criterion.instance(colName, UN_LIKE, liberal);
	}

	/**
	 * @param colName
	 * @param value
	 * @return
	 */
	public static Criterion ilike(final String colName, final Object value) {
		return ilike(colName, value, MatchMode.ANYWHERE);
	}

	/**
	 * And connecting two criterion
	 *
	 * @param lCriterion
	 * @param rCriterion
	 * @return
	 */
	public static Criterion and(final Criterion lCriterion,
			final Criterion rCriterion) {
		return Criterion.instance(lCriterion, _AND, rCriterion);
	}

	/**
	 * Or connecting two criterion
	 *
	 * @param lCriterion
	 * @param rCriterion
	 * @return
	 */
	public static Criterion or(final Criterion lCriterion,
			final Criterion rCriterion) {
		return Criterion.instance(lCriterion, _OR, rCriterion);
	}

	/**
	 * @param colName
	 * @return
	 */
	public static Criterion isNull(final String colName) {
		return Criterion.instance(colName, IS_NULL, null);
	}

	/**
	 * @param colName
	 * @return
	 */
	public static Criterion isNotNull(final String colName) {
		return Criterion.instance(colName, IS_NOT_NULL, null);
	}

	/**
	 * @param colName
	 * @param values
	 * @return
	 */
	public static Criterion in(final String colName, final Object[] values) {
		return Criterion.instance(colName, _IN, extractValues(values));
	}

	/**
	 * @param colName
	 * @param values
	 * @return
	 */
	public static Criterion in(final String colName,
			final Collection<Object> values) {
		return in(colName, values.toArray());
	}

	/**
	 * @param colName
	 * @param values
	 * @return
	 */
	public static Criterion notIn(final String colName, final Object[] values) {
		return Criterion.instance(colName, NOT_IN, extractValues(values));
	}

	/**
	 * @param colName
	 * @param values
	 * @return
	 */
	public static Criterion notIn(final String colName,
			final Collection<Object> values) {
		return notIn(colName, values.toArray());
	}

	// ~ Private Methods =====================================

	/**
	 * @param value
	 * @param mode
	 * @return
	 */
	private static String extractValue(final Object value, final MatchMode mode) {
		String liberal = Symbol.STR_NULL;
		switch (mode) {
		case START:
			liberal = extractValue(value) + Symbol.C_PERCENT;
			break;
		case END:
			liberal = Symbol.C_PERCENT + extractValue(value);
			break;
		case EXACT:
			liberal = extractValue(value);
			break;
		case ANYWHERE:
		default:
			liberal = Symbol.C_PERCENT + extractValue(value)
					+ Symbol.C_PERCENT;
			break;
		}
		return liberal;
	}

	/**
	 * @param value
	 * @return
	 */
	private static String extractValue(final Object value) {
		String retValue = Symbol.STR_NULL;
		if (value instanceof String) {
			if (((String) value).trim().equals(Q_MARK)) {
				retValue = value.toString();
			} else {
				retValue = Symbol.C_S_QUOTE
						+ (nullable(value) ? Symbol.STR_EMPTY : value
								.toString()) + Symbol.C_S_QUOTE;
			}
		} else {
			retValue = nullable(value) ? Symbol.STR_EMPTY : value.toString();
		}
		return retValue;
	}

	/**
	 * @param values
	 * @return
	 */
	private static String extractValues(final Object[] values) {
		final StringBuilder valueBuf = builder();
		valueBuf.append(Symbol.C_L_BRACKET);
		for (final Object value : values) {
			valueBuf.append(extractValue(value)).append(Symbol.C_COMMA);
		}
		valueBuf.delete(valueBuf.length() - 1, valueBuf.length());
		valueBuf.append(Symbol.C_R_BRACKET);
		return valueBuf.toString();
	}
}
