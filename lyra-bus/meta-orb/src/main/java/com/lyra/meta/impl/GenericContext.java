package com.lyra.meta.impl;

import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.reflector.Factory.singleton;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lyra.exception.AbstractSchemaException;
import com.lyra.meta.Context;
import com.lyra.meta.Record;
import com.lyra.meta.Strategy;
import com.lyra.meta.Value;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Constants;
import com.lyra.res.Resources;
import com.lyra.res.Symbol;

/**
 * @author Lang
 * @package com.test.lyra.meta.impl
 * @name GenericContext
 * @class com.test.lyra.meta.impl.GenericContext
 * @date Nov 29, 2014 12:54:29 PM
 * @see
 */
final class GenericContext implements Context {

	// ~ Static Fields =======================================
	/**
	 * Pool singlton mode, every model should have only one Strategy to
	 * processed.
	 */
	private static final Map<String, Strategy> CTX_POOL = hashMap(true);

	// ~ Instance Fields =====================================
	/**
	 * Strategy interface of current context *
	 */
	private transient Strategy strategy;
	/**
	 * Model name that current system used, the core meta data here. *
	 */
	private transient final String modelName;

	// ~ Constructors ========================================

	/**
	 * Construct context object by model name. *
	 */
	GenericContext(final String modelName) {
		this(modelName, Resources.SCHEMA_MODE);
	}

	/**
	 * Construct context object by model name and strategy both *
	 */
	GenericContext(final String modelName, final String strategy) {
		this.modelName = modelName;
		final String clazz = Resources.getStrategyClass(strategy);
		this.strategy = singleton(clazz, CTX_POOL, this.modelName);
	}

	// ~ Override Methods ====================================
	// region Record creating Api.

	/**
	 * Create new Data Record Api. *
	 */
	@Override
	public Record createRecord() {
		return this.strategy.createRecord();
	}

	/**
	 * Create new Data Record by Map. *
	 */
	@Override
	public Record createRecord(final Map<String, Value<?>> inputMap) {
		return this.strategy.createRecord(inputMap);
	}

	/**
	 * Create new Data Record by Json content. *
	 */
	@Override
	public Record createRecord(final String jsonContent) {
		return this.strategy.createRecord(jsonContent);
	}

	/**
	 * Read records from data file *
	 */
	@Override
	public List<Record> readRecords() {
		return this.strategy.readRecords();
	}

	// endregion

	// region Extrace database metadata

	/**
	 * Get all keys schema information
	 *
	 * @return
	 */
	@Override
	public Map<String, FieldSchema> getKeys() {
		return this.strategy.getKeys();
	}

	/**
	 * Get all fields schema information
	 *
	 * @return
	 */
	@Override
	public Map<String, FieldSchema> getFields() {
		return this.strategy.getFields();
	}

	/**
	 * Get database table name
	 *
	 * @return
	 */
	@Override
	public String getTable() {
		return this.strategy.getTable();
	}

	// endregion

	/**
	 * Get current processed model name. *
	 */
	@Override
	public String getModel() {
		return this.modelName;
	}

	/**
	 * Get column to field map *
	 */
	@Override
	public Map<String, String> getCtoF() {
		return this.strategy.getCtoF();
	}

	/**
	 * Get field to column map *
	 */
	@Override
	public Map<String, String> getFtoC() {
		return this.strategy.getFtoC();
	}

	/**
	 * Get order columns. *
	 */
	@Override
	public List<String> getOrderColumns() {
		return this.strategy.getOrderColumns();
	}

	/**
	 * Get Schema exception error message;
	 *
	 * @return
	 */
	@Override
	public AbstractSchemaException getError() {
		return this.strategy.getError();
	}

	/**
	 * @param error
	 */
	@Override
	public void setError(final AbstractSchemaException error) {
		this.strategy.setError(error);
	}

	// ===Only for (Non-Multi) Primary Key====

	/**
	 * Get ID policy
	 *
	 * @return
	 */
	@Override
	public String getPolicy() {
		String rPolicy = Symbol.STR_NULL;
		for (final FieldSchema schema : this.getKeys().values()) {
			if (!nullable(schema.getPolicy())) {
				rPolicy = schema.getPolicy();
				break;
			}
		}
		return rPolicy;
	}

	/**
	 * Extract primary key schema: Non-Multi
	 *
	 * @return
	 */
	@Override
	public FieldSchema getPK() {
		FieldSchema rSchema = nullObj();
		for (final FieldSchema schema : this.getKeys().values()) {
			if (!nullable(schema.getPolicy())
					&& !schema.getPolicy().equals(Constants.KP_MULTI)) {
				rSchema = schema;
				break;
			}
		}
		return rSchema;
	}

	/**
	 * Extract primary keys schemata: Multi
	 *
	 * @return
	 */
	@Override
	public Set<FieldSchema> getPKs() {
		final Set<FieldSchema> pkSet = hashSet();
		for (final FieldSchema schema : this.getKeys().values()) {
			if (!nullable(schema.getPolicy())
					&& schema.getPolicy().equals(Constants.KP_MULTI)) {
				pkSet.add(schema);
			}
		}
		return pkSet;
	}
}
