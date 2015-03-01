package com.lyra.db.conn.util;

import com.lyra.meta.Value;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Resources;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.logger.Logger.info;

/**
 * @author Lang
 * @package com.lyra.db.conn.impl
 * @name Input
 * @class com.lyra.db.conn.impl.InOut
 * @date Dec 1, 2014 3:43:08 PM
 * @see
 */
public final class Input {
	// ~ Constructors ========================================
	private Input() {
	}

	// ~ Static Methods ======================================

	/**
	 * Prepare for single condition of query sql statement
	 *
	 * @param sqlStr
	 * @param schema
	 * @param inputValue
	 * @return
	 */
	public static PreparedStatementCreator prepStmt(final String sqlStr,
			final FieldSchema schema, final Value<?> inputValue) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					final Connection conn) throws SQLException {
				final PreparedStatement pStmt = conn.prepareStatement(sqlStr);
				Input.setParameters(pStmt, 1, schema.getType(), inputValue);
				return pStmt;
			}
		};
	}

	/**
	 * @param sqlStr
	 * @param schemaMap
	 * @param valueMap
	 * @param isRetKey
	 * @return
	 */
	public static PreparedStatementCreator prepStmt(final String sqlStr,
			final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap, final boolean isRetKey) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					final Connection conn) throws SQLException {
				PreparedStatement stmt = nullObj();
				if (isRetKey) {
					stmt = conn.prepareStatement(sqlStr,
							Statement.RETURN_GENERATED_KEYS);
				} else {
					stmt = conn.prepareStatement(sqlStr);
				}
				for (final Integer colIndex : schemaMap.keySet()) {
					setParameters(stmt, colIndex, schemaMap.get(colIndex)
							.getType(), valueMap.get(colIndex));
				}
				return stmt;
			}
		};
	}

	/**
	 * @param sql
	 * @param schemaMap
	 * @param valueMap
	 * @return
	 */
	public static PreparedStatementCreator prepStmt(final String sql,
			final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap) {
		return new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(
					final Connection conn) throws SQLException {
				final PreparedStatement stmt = conn.prepareStatement(sql);
				for (final Integer colIndex : schemaMap.keySet()) {
					setParameters(stmt, colIndex, schemaMap.get(colIndex)
							.getType(), valueMap.get(colIndex));
				}
				return stmt;
			}
		};
	}

	/**
	 * Create batch prepared statement for mass delete
	 *
	 * @param pId
	 * @param idSchema
	 * @return
	 */
	public static BatchPreparedStatementSetter prepStmt(
			final FieldSchema idSchema, final List<Value<?>> pId) {
		return new BatchPreparedStatementSetter() {
			@Override
			public void setValues(final PreparedStatement stmt, final int index)
					throws SQLException {
				// ArrayInstance index check to avoid index out of bound
				if (index < pId.size()) {
					final Value<?> inputValue = pId.get(index);
					setParameters(stmt, 1, idSchema.getType(), inputValue);
				} else {
					info(getClass(), "Record list size is: " + pId.size()
							+ ", but input index is " + index);
				}
			}

			@Override
			public int getBatchSize() {
				return Math.min(pId.size(), Resources.DB_BATCH_SIZE);
			}
		};
	}

	/**
	 * @param schemaList
	 * @param valueList
	 * @param filters
	 * @return
	 */
	public static BatchPreparedStatementSetter prepStmt(
			final List<Map<Integer, FieldSchema>> schemaList,
			final List<Map<Integer, Value<?>>> valueList,
			final String... filters) {
		return new BatchPreparedStatementSetter() {
			@Override
			public void setValues(final PreparedStatement stmt, final int index)
					throws SQLException {
				// ArrayInstance index check to avoid index ouf of bound
				if (index < valueList.size()) {
					for (final Integer colIndex : schemaList.get(index)
							.keySet()) {
						final String type = schemaList.get(index).get(colIndex)
								.getType();
						final Value<?> value = valueList.get(index).get(
								colIndex);
						Input.setParameters(stmt, colIndex, type, value);
					}
				} else {
					info(getClass(),
							"Record list size is: " + valueList.size()
									+ ", but input index is " + index);
				}
			}

			@Override
			public int getBatchSize() {
				return Math.min(valueList.size(), Resources.DB_BATCH_SIZE);
			}
		};
	}

	/**
	 * @param stmt
	 * @param colIndex
	 * @param fieldType
	 * @param inputValue
	 * @throws SQLException
	 */
	public static void setParameters(final PreparedStatement stmt,
			final int colIndex, final String fieldType,
			final Value<?> inputValue) throws SQLException {
		switch (fieldType) {
		default: {
			stmt.setString(colIndex, inputValue.getValue().toString());
		}
			break;
		}
	}
}
