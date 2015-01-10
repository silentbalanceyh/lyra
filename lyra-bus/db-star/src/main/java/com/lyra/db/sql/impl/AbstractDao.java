package com.lyra.db.sql.impl;

import static com.lyra.util.calculator.SetCalculator.diff;
import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.reflector.Factory.instance;
import static com.lyra.util.reflector.Factory.singleton;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lyra.db.conn.DataContext;
import com.lyra.db.sql.RecordReader;
import com.lyra.db.sql.RecordWriter;
import com.lyra.db.sql.util.stmt.IndexKit;
import com.lyra.meta.Context;
import com.lyra.meta.Record;
import com.lyra.meta.Value;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Resources;
import com.lyra.res.Symbol;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.impl
 * @name AbstractRecordService
 * @class com.test.lyra.db.sql.impl.AbstractRecordService
 * @date Oct 14, 2014 3:04:08 AM
 * @see
 */
public abstract class AbstractDao implements RecordWriter, RecordReader {
	// ~ Instance Fields =====================================
	/**
	 * Data context which could provide JDBC environment. *
	 */
	private transient final DataContext dbCtx;
	/**
	 * Context object which provide meta-data environment for current handled
	 * object.
	 */
	private transient final Context context;

	// ~ Constructors ========================================

	/**
	 * Protected constructor and it's used in sub-class only. *
	 */
	protected AbstractDao(final String modelName) {
		this.dbCtx = singleton(Resources.getPoolClass());
		this.context = instance(Resources.getContextClass(), modelName);
		// Inject self reference
		if (!nullable(this.context.getError())) {
			info(getClass(), this.context.getError());
		}
	}

	// ~ Static Methods ======================================

	/**
	 * Public method to init record service *
	 */
	public static AbstractDao init(final String modelName) {
		final String clazz = Resources.getDaoClass(Resources.DB_CATEGORY);
		return instance(clazz, modelName);
	}

	// ~ Methods =============================================

	/**
	 * Design to private first, if needed we'll set the scope to protected. *
	 */
	protected Context getContext() {
		return this.context;
	}

	/**
	 * @return
	 */
	protected DataContext getJdbc() {
		return this.dbCtx;
	}

	/**
	 * Extract field schema by name *
	 */
	protected FieldSchema getField(final String fieldName) {
		return this.getContext().getFields().get(fieldName);
	}

	/**
	 * Extract mass result from array *
	 */
	protected boolean getExecuteResult(final int... results) {
		boolean exeRet = true;
		if (results.length == Symbol.I_ONE) {
			exeRet = results[0] >= 0;
		} else {
			for (final int singleRet : results) {
				if (singleRet < 0) {
					exeRet = false;
					break;
				}
			}
		}
		return exeRet;
	}

	/**
	 * Generate schemaMap for prepared statement ( Only PK columns ) *
	 */
	protected void fillPkParams(final Map<String, Value<?>> inputPKs,
			final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap) {
		final Map<Integer, String> indexMap = IndexKit.prepPkIndexes(this
				.getContext());
		for (final Integer index : indexMap.keySet()) {
			final String fieldName = indexMap.get(index);
			schemaMap.put(index, this.getField(fieldName));
			valueMap.put(index, inputPKs.get(fieldName));
		}
	}

	/**
	 * Generate schemaMap for prepared statement ( All columns ) *
	 */
	protected void fillUpdateParams(final Record record,
			final Map<String, Integer> indexMap,
			final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap) {
		// Set parameters
		for (final String field : record.fieldNames()) {
			final String colName = this.getContext().getFtoC().get(field);
			final FieldSchema schema = this.getField(field);
			// Get index value from index map
			final int colIndex = indexMap.get(colName);
			final Value<?> inputValue = record.get(field);
			schemaMap.put(colIndex, schema);
			valueMap.put(colIndex, inputValue);
		}
	}

	/**
	 * @param record
	 * @param indexMap
	 * @param schemaList
	 * @param valueList
	 * @param filters
	 */
	protected void fillInsertParams(final List<Record> records,
			final Map<String, Integer> indexMap,
			final List<Map<Integer, FieldSchema>> schemaList,
			final List<Map<Integer, Value<?>>> valueList,
			final String... filters) {
		// Set parameters
		for (int i = 0; i < records.size(); i++) {
			final Map<Integer, FieldSchema> schemaMap = hashMap(true);
			final Map<Integer, Value<?>> valueMap = hashMap(true);
			this.fillInsertParams(records.get(i), indexMap, schemaMap,
					valueMap, filters);
			schemaList.add(schemaMap);
			valueList.add(valueMap);
		}
	}

	/**
	 * @param record
	 * @param indexMap
	 * @param schemaMap
	 * @param valueMap
	 * @param filters
	 */
	protected void fillInsertParams(final Record record,
			final Map<String, Integer> indexMap,
			final Map<Integer, FieldSchema> schemaMap,
			final Map<Integer, Value<?>> valueMap, final String... filters) {
		final Set<String> fields = diff(this.getContext().getFields().keySet(),
				hashSet(filters));
		// Set parameters
		for (final String field : fields) {
			final String colName = this.getContext().getFtoC().get(field);
			final FieldSchema schema = this.getField(field);
			// Get index value from index map
			final int colIndex = indexMap.get(colName);
			final Value<?> inputValue = record.get(field);
			schemaMap.put(colIndex, schema);
			valueMap.put(colIndex, inputValue);
		}
	}

	/** **/
	protected void fillUpdateParams(final List<Record> records,
			final Map<String, Integer> indexMap,
			final List<Map<Integer, FieldSchema>> schemaList,
			final List<Map<Integer, Value<?>>> valueList) {
		for (int i = 0; i < records.size(); i++) {
			final Map<Integer, FieldSchema> schemaMap = hashMap(true);
			final Map<Integer, Value<?>> valueMap = hashMap(true);
			this.fillUpdateParams(records.get(i), indexMap, schemaMap, valueMap);
			schemaList.add(schemaMap);
			valueList.add(valueMap);
		}
	}

	// ==========Return some useful object reference==============

	/**
	 * @param dataMap
	 * @return
	 */
	protected Record extractRecord(final Map<String, String> dataMap) {
		final Record record = this.getContext().createRecord();
		for (final String colName : dataMap.keySet()) {
			final String field = this.getContext().getCtoF().get(colName);
			record.set(field, dataMap.get(colName));
		}
		return record;
	}

	/**
	 * Extract result set to record list;
	 *
	 * @param dataMap
	 * @return
	 */
	protected List<Record> extractRecordList(
			final List<Map<String, String>> dataMap) {
		final List<Record> records = arrayList();
		for (final Map<String, String> item : dataMap) {
			records.add(this.extractRecord(item));
		}
		return records;
	}
}
