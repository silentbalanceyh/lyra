package com.lyra.meta.json;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Logger.debug;
import static com.lyra.util.reflector.Factory.instance;
import static com.lyra.util.reflector.Factory.singleton;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lyra.db.conn.util.Output;
import com.lyra.exception.AbstractSchemaException;
import com.lyra.meta.Value;
import com.lyra.meta.json.exp.MissingFieldAttrException;
import com.lyra.meta.json.exp.MissingFieldDefException;
import com.lyra.meta.json.exp.MissingKeyDefException;
import com.lyra.meta.json.exp.MissingMetaDefException;
import com.lyra.meta.sys.OldModelConfigurator;
import com.lyra.mod.def.EntitySchema;
import com.lyra.mod.def.FieldSchema;
import com.lyra.mod.def.KeyNames;
import com.lyra.mod.def.MetaSchema;
import com.lyra.mod.def.OrderSchema;
import com.lyra.prop.PropertyLoader;
import com.lyra.res.Resources;
import com.lyra.res.Symbol;

/**
 * This class contains all data information
 *
 * @author Lang
 */
final class GenericSchema {
	// ~ Static Fields =======================================
	/**
	 * Default values for concurrent map. *
	 */
	private static final Map<String, String> DV_MAP = hashMap(true);

	// ~ Instance Fields =====================================
	// region Private members
	/**
	 * Entity name. *
	 */
	private transient final String name;
	/**
	 * Model Configurator *
	 */
	private transient final OldModelConfigurator configurator;
	/**
	 * Mix schema information (Meta, Keys, Fields) *
	 */
	private transient final EntitySchema schema;
	/**
	 * Jackson mapper tools *
	 */
	private transient final ObjectMapper mapper = new ObjectMapper();
	/**
	 * Resource loader tools *
	 */
	private transient final PropertyLoader loader;
	/**
	 * Schema validator *
	 */
	private transient final GenericValidator validator;

	// ~ Constructors ========================================

	/**
	 * @param name
	 */
	GenericSchema(final String name) {
		this.name = name;
		// Init model configurator pool design
		this.configurator = singleton(OldModelConfigurator.class,
				OldModelConfigurator.getPool(), name); // OldModelConfigurator.singleton(name);
		// Init schema
		this.schema = instance(EntitySchema.class);
		// Validator initializing
		this.validator = instance(GenericValidator.class, this.schema);
		// Load properties
		this.loader = instance(PropertyLoader.class,getClass(),
				this.configurator.getMappingFilePath());
		// Load schema information
		this.loadSchema();
		// Validation schema
		this.validator.validateSchema();
		debug(getClass(), "[VE] Schema validation result: "
				+ (nullable(validator.getError()) ? "Success"
						: "Error Code -> "
								+ validator.getError().getErrorCode()));
	}

	// ~ Methods =============================================

	/**
	 * @param name
	 * @return
	 */
	public Value<?> extractValue(final String name) {
		final String defValue = this.getDefaultValue(name);
		return this.extractValue(name, defValue);
	}

	/**
	 * @param name
	 * @param value
	 * @return
	 */
	public Value<?> extractValue(final String name, final String value) {
		final FieldSchema schema = this.getFieldSchema(name);
		return Output.extractValue(schema, value);
	}

	/**
	 * Get field schema by field name.
	 *
	 * @param name
	 * @return
	 */
	public FieldSchema getFieldSchema(final String name) {
		return this.schema.getFields().get(name);
	}

	// Input using

	/**
	 * Read default value from configuration file.
	 *
	 * @param name
	 * @return
	 */
	public String getDefaultValue(final String name) {
		final String propKey = "default." + this.name + "." + name;
		// Logger.info(getClass(),"Default Attr Name: " + propKey);
		String rValue = nullObj();
		if (DV_MAP.containsKey(propKey)) {
			rValue = DV_MAP.get(propKey);
		} else {
			rValue = this.loader.getString(propKey);
			DV_MAP.put(propKey, rValue);
		}
		return rValue;
	}

	/**
	 * Get keys field schema map.
	 *
	 * @return
	 */
	public Map<String, FieldSchema> getKeys() {
		return this.schema.getKeys();
	}

	/**
	 * Get field name list collection.
	 *
	 * @return
	 */
	public Map<String, FieldSchema> getFields() {
		return this.schema.getFields();
	}

	/**
	 * Get column to field map;
	 *
	 * @return
	 */
	public Map<String, String> getCtoF() {
		return this.schema.getCtoF();
	}

	/**
	 * Get field to column map;
	 *
	 * @return
	 */
	public Map<String, String> getFtoC() {
		return this.schema.getFtoC();
	}

	/**
	 * Get full name of current entity.
	 *
	 * @return
	 */
	public String getTable() {
		return this.schema.getTable();
	}

	/**
	 * @return
	 */
	public String getFullName() {
		return this.schema.getFullName();
	}

	/**
	 * @return
	 */
	public OldModelConfigurator getConfigurator() {
		return this.configurator;
	}

	/**
	 * @return
	 */
	public List<String> getOrderColumns() {
		return this.schema.getOrderCols();
	}

	/**
	 * @return
	 */
	public AbstractSchemaException getError() {
		return this.validator.getError();
	}

	/**
	 * @param error
	 */
	public void setError(final AbstractSchemaException error) {
		this.validator.setError(error);
	}

	// ~ Private Methods =====================================
	private void loadSchema() {
		try {
			final InputStream inStream = this.getClass().getResourceAsStream(
					this.configurator.getSchemaFilePath());
			final Map<String, JsonNode> schema = mapper.readValue(inStream,
					new TypeReference<Map<String, JsonNode>>() {
					});
			// Load Fields ( Required )
			if (schema.containsKey("__fields__")) {
				final List<FieldSchema> fieldList = mapper.readValue(schema
						.get("__fields__").toString(),
						new TypeReference<List<FieldSchema>>() {
						});
				int invalidCounter = 0;
				for (final FieldSchema field : fieldList) {
					field.setColumnType(this.extractType(field.getColumnType()));
					this.schema.putField(field.getName(), field);
					// 10006 Validation
					invalidCounter += this.validator.validate10006(field);
				}
				if (0 < invalidCounter) {
					this.validator.setError(new MissingFieldAttrException(
							invalidCounter));
				}
			} else {
				// 10003 Validation
				this.validator.setError(new MissingFieldDefException());
				debug(getClass(),
						"[E] Schema file does not contain '__fields__' parts.");
			}
			// Load Keys ( Required )
			if (schema.containsKey("__key__")) {
				this.schema.initKeys(this.mapper.readValue(schema
						.get("__key__").toString(), KeyNames.class));
			} else {
				// 10001 Validation
				this.validator.setError(new MissingKeyDefException());
				debug(getClass(),
						"[E] Schema file does not contain '__key__' parts.");
			}
			// Load Meta ( Required )
			if (schema.containsKey("__meta__")) {
				this.schema.initMetadata(mapper.readValue(schema
						.get("__meta__").toString(), MetaSchema.class));
			} else {
				// 10002 Validation
				this.validator.setError(new MissingMetaDefException());
				debug(getClass(),
						"[E] Schema file does not contain '__meta__' parts.");
			}
			// Load Order ( Optional )
			if (schema.containsKey("__order__")) {
				final List<OrderSchema> orderList = mapper.readValue(schema
						.get("__order__").toString(),
						new TypeReference<List<OrderSchema>>() {
						});
				for (final OrderSchema order : orderList) {
					this.schema.putOrder(order);
				}
			}
		} catch (IOException ex) {
			debug(getClass(), ex);
		}
	}

	private String extractType(final String type) {
		final StringBuilder fieldKey = builder();
		String retType = Symbol.STR_NULL;
		if (Symbol.STR_NULL != type) {
			fieldKey.append("db.")
					.append(Resources.DB_CATEGORY.toLowerCase(Locale
							.getDefault())).append('.')
					.append(type.trim().substring(2, type.length() - 1));
			// Convert ${var} to var to build key
			final Properties prop = this.loader.getProp(this.configurator
					.getMappingFilePath());
			retType = prop.getProperty(fieldKey.toString());
		}
		return retType;
	}
	// endregion
}
