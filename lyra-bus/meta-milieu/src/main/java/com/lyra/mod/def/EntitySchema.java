package com.lyra.mod.def;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.instance.SortedSetInstance.treeSet;
import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Logger.error;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.lyra.res.Symbol;

/**
 * @author Lang
 */
@Component
public final class EntitySchema {

	// ~ Instance Fields =====================================
	/**
	 * Map from column to field *
	 */
	private final transient Map<String, String> ctfMap = hashMap(true);
	/**
	 * Map from field to column *
	 */
	private final transient Map<String, String> ftcMap = hashMap(true);
	/**
	 * Keys field schema map. *
	 */
	private final transient Map<String, FieldSchema> keysMap = hashMap(true);
	/**
	 * Whole fields schema map. ( Include Keys ) *
	 */
	private final transient Map<String, FieldSchema> fieldsMap = hashMap(true);
	/**
	 * Order map for current entity. *
	 */
	private final transient Set<OrderSchema> ordersSet = hashSet();
	/**
	 * Primary Key Names *
	 */
	private final transient Set<String> pkSet = hashSet();
	/**
	 * Metadata of current entity *
	 */
	private transient MetaSchema metadata;

	// ~ Constructors ========================================

	/**
	 * Private constructor to prevent create current instance directly *
	 */
	EntitySchema() {
		// For Jackson
	}

	// ~ Methods =============================================

	/**
	 * Initialize fields map;
	 *
	 * @param fieldsMap
	 */
	public void initFields(final Map<String, FieldSchema> fieldsMap) {
		if (!nullable(this.fieldsMap) && !this.fieldsMap.isEmpty()) {
			this.fieldsMap.putAll(fieldsMap);
		}
	}

	/**
	 * @param colName
	 * @param schema
	 */
	public void putOrder(final OrderSchema schema) {
		if (!nullable(this.ordersSet) && !this.ordersSet.contains(schema)) {
			this.ordersSet.add(schema);
		}
	}

	/**
	 * Add one item to current fields map.
	 *
	 * @param name
	 * @param schema
	 */
	public void putField(final String name, final FieldSchema schema) {
		if (!nullable(name) && !nullable(this.fieldsMap)
				&& !this.fieldsMap.containsKey(name)) {
			this.fieldsMap.put(name, schema);
			// Fill two mapping
			if (nullable(ftcMap.get(name))) {
				ftcMap.put(name,
						nullable(schema.getColumnName()) ? Symbol.STR_EMPTY
								: schema.getColumnName());
			}
			if (!nullable(schema.getColumnName())
					&& nullable(ctfMap.get(schema.getColumnName()))) {
				ctfMap.put(schema.getColumnName(), name);
			}
		}
	}

	/**
	 * Initialize current entity metadata.
	 *
	 * @param metadata
	 */
	public void initMetadata(final MetaSchema metadata) {
		this.metadata = metadata;
	}

	/**
	 * Initialize current entity key names.
	 *
	 * @param keyNames
	 */
	public void initKeys(final KeyNames keyNames) {
		if (this.fieldsMap.isEmpty()) {
			error(getClass(),
					"[E] Please call initFields first and then you could process Keys!");
		}
		// Load Primary Key
		for (final String key : keyNames.getpKeys()) {
			final FieldSchema schema = this.fieldsMap.get(key);
			if (!nullable(schema)) {
				schema.setNullable(false);
				schema.setUnique(true);
				keysMap.put(key, schema);
				pkSet.add(key);
			}
		}
		// Load Not Null Key
		for (final String key : keyNames.getnKeys()) {
			final FieldSchema schema = this.fieldsMap.get(key);
			if (!nullable(schema)) {
				schema.setNullable(false);
				keysMap.put(key, schema);
			}
		}
		// Load Unique Key
		for (final String key : keyNames.getuKeys()) {
			final FieldSchema schema = this.fieldsMap.get(key);
			if (!nullable(schema)) {
				schema.setUnique(true);
				keysMap.put(key, schema);
			}
		}
		// Load Foreign Key
		for (final String key : keyNames.getfKeys()) {
			keysMap.put(key, this.fieldsMap.get(key));
		}
	}

	/**
	 * Get key fields schema map.
	 *
	 * @return
	 */
	public Map<String, FieldSchema> getKeys() {
		final Map<String, FieldSchema> retMap = hashMap(true);
		if (!nullable(this.keysMap)) {
			retMap.putAll(this.keysMap);
		}
		return retMap;
	}

	/**
	 * Get whole fields schema map.
	 *
	 * @return
	 */
	public Map<String, FieldSchema> getFields() {
		final Map<String, FieldSchema> retMap = hashMap(true);
		if (!nullable(this.keysMap)) {
			retMap.putAll(this.keysMap);
		}
		if (!nullable(this.fieldsMap)) {
			retMap.putAll(this.fieldsMap);
		}
		return retMap;
	}

	/**
	 * Get column to field map
	 *
	 * @return
	 */
	public Map<String, String> getCtoF() {
		return this.ctfMap;
	}

	/**
	 * Get field to column map
	 *
	 * @return
	 */
	public Map<String, String> getFtoC() {
		return this.ftcMap;
	}

	/**
	 * Get full name of current entity.
	 *
	 * @return
	 */
	public String getFullName() {
		return nullable(this.metadata) ? Symbol.STR_NULL : this.metadata
				.getFullName();
	}

	/**
	 * Get database table name of current entity.
	 *
	 * @return
	 */
	public String getTable() {
		return nullable(this.metadata) ? Symbol.STR_NULL : this.metadata
				.getTable();
	}

	/**
	 * Get short name of current entity.
	 *
	 * @return
	 */
	public String getModel() {
		return nullable(this.metadata) ? Symbol.STR_NULL : this.metadata
				.getName();
	}

	/**
	 * Get package name of current entity.
	 *
	 * @return
	 */
	public String getPackage() {
		return nullable(this.metadata) ? Symbol.STR_NULL : this.metadata
				.getPkg();
	}

	/**
	 * Get Primary Keys
	 *
	 * @return
	 */
	public Set<String> getPrimaryKeys() {
		return this.pkSet;
	}

	/**
	 * Get order columns
	 *
	 * @return
	 */
	public List<String> getOrderCols() {
		final Map<Integer, String> columnMap = hashMap(true);
		// Set sequence of column in order by, automatically ordering.
		final Set<Integer> seqSet = treeSet();
		for (final OrderSchema column : this.ordersSet) {
			seqSet.add(column.getOrder());
			columnMap.put(column.getOrder(),
					column.getColumn() + ' ' + column.getMode());
		}
		// Ret List
		final List<String> retList = arrayList();
		for (final Integer seq : seqSet) {
			retList.add(columnMap.get(seq));
		}
		return retList;
	}

	// ~ hashCode,equals,toString ============================

	/**
     *
     */
	@Override
	public int hashCode() {
		int result = hash(1, this.ctfMap);
		result = hash(result, this.ftcMap);
		result = hash(result, this.keysMap);
		result = hash(result, this.fieldsMap);
		result = hash(result, this.metadata);
		result = hash(result, this.ordersSet);
		result = hash(result, this.pkSet);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final EntitySchema that = (EntitySchema) obj;
			ret &= equal(this.ctfMap, that.ctfMap);
			ret &= equal(this.ftcMap, that.ftcMap);
			ret &= equal(this.keysMap, that.keysMap);
			ret &= equal(this.fieldsMap, that.fieldsMap);
			ret &= equal(this.metadata, that.metadata);
			ret &= equal(this.ordersSet, that.ordersSet);
			ret &= equal(this.pkSet, that.pkSet);
		}
		return ret;
	}
}
