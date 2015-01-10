package com.lyra.meta.type;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;
import static com.lyra.util.internal.Validator.nullable;

import java.lang.reflect.Type;

import com.lyra.meta.Value;
import com.lyra.res.Symbol;

/**
 * Number type information
 *
 * @author Lang
 * @package com.test.lyra.meta.json.type
 * @name NumberType
 * @class com.test.lyra.meta.json.type.NumberType
 * @date Oct 15, 2014 6:15:28 AM
 * @see
 */
public class NumberType implements Value<Number> {

	// ~ Instance Fields =====================================
	/**
	 * The value of number type item. *
	 */
	private final Number value;
	/**
	 * The type of current item: Short/Integer/Long *
	 */
	private final Type type;

	// ~ Constructors ========================================

	/**
	 * Constructor by long value *
	 */
	public NumberType(final Long value) {
		this.value = value;
		this.type = Long.class;
	}

	/**
	 * Constructor by integer value *
	 */
	public NumberType(final Integer value) {
		this.value = value;
		this.type = Integer.class;
	}

	/**
	 * Constructor by short value. *
	 */
	public NumberType(final Short value) {
		this.value = value;
		this.type = Short.class;
	}

	/**
	 * Constructor by String value *
	 */
	public NumberType(final String value) {
		this.value = nullable(value) ? -1L : Long.parseLong(value);
		this.type = Long.class;
	}

	// ~ Override Methods ====================================

	/**
	 * Get value of current item
	 */
	@Override
	public Number getValue() {
		return this.value;
	}

	/**
	 * Get data type of current item
	 */
	@Override
	public Type getType() {
		return this.type;
	}

	// ~ hashCode,equals,toString ============================

	/**
	 * Override toString() of java.lang.Object
	 */
	@Override
	public String toString() {
		final StringBuilder retStr = builder();
		retStr.append(Symbol.C_COMMA).append("Type:").append(this.type)
				.append(",Value:").append(this.value);
		return retStr.toString();
	}

	/**
     *
     */
	@Override
	public int hashCode() {
		return hash(1, this.value);
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean retValue = true & equal(this, obj, getClass(), true);
		if (retValue) {
			final NumberType that = (NumberType) obj;
			retValue &= equal(this.value, that.value);
		}
		return retValue;
	}
}
