package com.lyra.meta.json;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.instance.SetInstance.hashSet;

import java.util.Map;
import java.util.Set;

import com.lyra.meta.Record;
import com.lyra.meta.Value;
import com.lyra.meta.type.BooleanType;
import com.lyra.meta.type.NumberType;
import com.lyra.meta.type.StringType;

/**
 * Data record information
 *
 * @author Lang
 */
final class GenericRecord implements Record {

	// ~ Instance Fields =====================================
	/**
	 * Field map information. *
	 */
	private transient final Map<String, Value<?>> fields = hashMap(true);
	/**
	 * Current record's model name. *
	 */
	private transient final String _name;

	// ~ Constructors ========================================

	/**
	 * Default constructor, must be created by name.
	 *
	 * @param name
	 */
	GenericRecord(final String name) {
		this._name = name;
	}

	// ~ Methods =============================================

	/**
	 * Set field value.
	 */
	@Override
	public void set(final String name, final Value<?> value) {
		fields.put(name, value);
	}

	/**
	 * Get field value.
	 */
	@Override
	public Value<?> get(final String name) {
		Value<?> retValue = nullObj();
		if (fields.containsKey(name)) {
			retValue = fields.get(name);
		}
		return retValue;
	}

	/**
	 * Get current entity name.
	 */
	@Override
	public String name() {
		return this._name;
	}

	/**
	 * Get field name collection.
	 */
	@Override
	public Set<String> fieldNames() {
		final Set<String> rFields = hashSet();
		for (final String field : this.fields.keySet()) {
			rFields.add(field);
		}
		return rFields;
	}

	/**
     *
     */
	@Override
	public String toString() {
		final StringBuilder retStr = builder();
		retStr.append("{ Model Name: ").append(this._name)
				.append(", Attributes: {");
		for (final String field : this.fields.keySet()) {
			retStr.append(field).append('=')
					.append(this.fields.get(field).getValue()).append(',');
		}
		retStr.delete(retStr.length() - 1, retStr.length());
		retStr.append("}}");
		return retStr.toString();
	}

	/**
     *
     */
	@Override
	public void set(final String name, final boolean value) {
		fields.put(name, new BooleanType(value));
	}

	/**
     *
     */
	@Override
	public void set(final String name, final int value) {
		fields.put(name, new NumberType(value));
	}

	/**
     *
     */
	@Override
	public void set(final String name, final String value) {
		fields.put(name, new StringType(value));
	}
}
