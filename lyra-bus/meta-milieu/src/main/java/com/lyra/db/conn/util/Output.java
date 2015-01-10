package com.lyra.db.conn.util;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.reflector.Factory.instance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.lyra.meta.Value;
import com.lyra.meta.type.BooleanType;
import com.lyra.meta.type.NumberType;
import com.lyra.meta.type.StringType;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Constants;
import com.lyra.res.Symbol;

/**
 * @author Lang
 * @package com.lyra.db.conn.util
 * @name Output
 * @class com.lyra.db.conn.util.Output
 * @date Dec 2, 2014 9:43:53 AM
 * @see
 */
public final class Output {
	// ~ Constructors ========================================
	private Output() {
	}

	// ~ Static Methods ======================================

	/**
	 * @param schema
	 * @param retSet
	 * @param inputIdx
	 * @return
	 * @throws SQLException
	 */
	public static Value<?> extractValue(final FieldSchema schema,
			final ResultSet retSet, final int inputIdx) throws SQLException {
		Value<?> retValue = nullObj();
		switch (schema.getType()) {
		case Value.BOOLEAN: {
			retValue = instance(BooleanType.class,
					Boolean.valueOf(retSet.getBoolean(inputIdx)));
		}
			break;
		case Value.LONG: {
			retValue = instance(NumberType.class,
					Long.valueOf(retSet.getLong(inputIdx)));
		}
			break;
		case Value.INT: {
			retValue = instance(NumberType.class,
					Integer.valueOf(retSet.getInt(inputIdx)));
		}
			break;
		case Value.SHORT: {
			retValue = instance(NumberType.class,
					Short.valueOf(retSet.getShort(inputIdx)));
		}
			break;
		default: {
			retValue = instance(StringType.class, retSet.getString(inputIdx));
		}
			break;
		}
		return retValue;
	}

	/**
	 * @param name
	 * @param value
	 * @return
	 */
	public static Value<?> extractValue(final FieldSchema schema,
			final String value) {
		// Get schema of field
		Value<?> retValue;
		switch (schema.getType()) {
		case Value.BOOLEAN:
			// replace wrapped BooleanType, huarui 2014.12.24
			retValue = instance(BooleanType.class,
			// retValue = instance(Boolean.class,
					Boolean.valueOf(Boolean.parseBoolean(value)));
			break;
		case Value.LONG:
			retValue = instance(NumberType.class,
					Long.valueOf(Long.parseLong(value)));
			break;
		case Value.INT:
			retValue = instance(NumberType.class,
					Integer.valueOf(Integer.parseInt(value)));
			break;
		case Value.SHORT:
			retValue = instance(NumberType.class,
					Short.valueOf(Short.parseShort(value)));
			break;
		default:
			retValue = instance(StringType.class, value);
			break;
		}
		return retValue;
	}

	/**
	 * @return
	 */
	public static PreparedStatementCallback<Value<?>> extractPk(
			final FieldSchema pkSchema) {
		return new PreparedStatementCallback<Value<?>>() {
			@Override
			public Value<?> doInPreparedStatement(final PreparedStatement stmt)
					throws SQLException, DataAccessException {
				final int rows = stmt.executeUpdate();
				Value<?> retValue = nullObj();
				if (rows >= 0 && !nullable(pkSchema)
						&& pkSchema.getPolicy().equals(Constants.KP_AUTO)) {
					{
						try (final ResultSet retSet = stmt.getGeneratedKeys()) {
							if (retSet.next()) {
								retValue = extractValue(pkSchema, retSet, 1);
							}
						}
					}
				}
				return retValue;
			}

		};
	}

	/**
	 * @param columns
	 * @return
	 */
	public static ResultSetExtractor<List<Map<String, String>>> extractDataList(
			final String... columns) {
		return new ResultSetExtractor<List<Map<String, String>>>() {
			@Override
			public List<Map<String, String>> extractData(final ResultSet rsSet)
					throws SQLException, DataAccessException {
				final List<Map<String, String>> retList = arrayList();
				while (rsSet.next()) {
					final Map<String, String> dataMap = hashMap(true);
					for (final String column : columns) {
						final String value = nullable(rsSet.getString(column)) ? Symbol.STR_EMPTY
								: rsSet.getString(column);
						dataMap.put(column, value);
					}
					retList.add(dataMap);
				}
				return retList;
			}
		};
	}

	/**
	 * @param columns
	 * @return
	 */
	public static ResultSetExtractor<List<Map<String, String>>> extractDataList() {
		return new ResultSetExtractor<List<Map<String, String>>>() {
			@Override
			public List<Map<String, String>> extractData(final ResultSet rsSet)
					throws SQLException, DataAccessException {
				final List<Map<String, String>> retList = arrayList();
				while (rsSet.next()) {
					final Map<String, String> dataMap = hashMap(true);
					fillRecord(dataMap, rsSet);
					retList.add(dataMap);
				}
				return retList;
			}
		};
	}

	/**
	 * @param columns
	 * @return
	 */
	public static ResultSetExtractor<Map<String, String>> extractData(
			final String... columns) {
		return new ResultSetExtractor<Map<String, String>>() {

			@Override
			public Map<String, String> extractData(final ResultSet rsSet)
					throws SQLException, DataAccessException {
				final Map<String, String> dataMap = hashMap(true);
				if (rsSet.next()) {
					for (final String column : columns) {
						final String value = nullable(rsSet.getString(column)) ? Symbol.STR_EMPTY
								: rsSet.getString(column);
						dataMap.put(column, value);
					}
				}
				return dataMap;
			}
		};
	}

	/**
	 * @param column
	 * @return
	 */
	public static ResultSetExtractor<Set<String>> extractData(
			final String column) {
		return new ResultSetExtractor<Set<String>>() {
			/** Return type of collection ( Set ). **/
			@Override
			public Set<String> extractData(final ResultSet rsSet)
					throws SQLException, DataAccessException {
				final Set<String> retSet = hashSet();
				while (rsSet.next()) {
					retSet.add(rsSet.getString(column));
				}
				return retSet;
			}
		};
	}

	/**
	 * @return
	 */
	public static ResultSetExtractor<Map<String, String>> extractData() {
		return new ResultSetExtractor<Map<String, String>>() {
			@Override
			public Map<String, String> extractData(final ResultSet rsSet)
					throws SQLException, DataAccessException {
				final Map<String, String> dataMap = hashMap(true);
				if (rsSet.next()) {
					fillRecord(dataMap, rsSet);
				}
				return dataMap;
			}
		};
	}

	private static void fillRecord(final Map<String, String> dataMap,
			final ResultSet rsSet) throws SQLException {
		// Loop all columns to set record
		final ResultSetMetaData metadata = rsSet.getMetaData();
		for (int i = 1; i <= metadata.getColumnCount(); i++) {
			final String colName = metadata.getColumnName(i);
			final String value = rsSet.getString(colName);
			dataMap.put(colName, nullable(value) ? Symbol.STR_EMPTY : value);
		}
	}
}
