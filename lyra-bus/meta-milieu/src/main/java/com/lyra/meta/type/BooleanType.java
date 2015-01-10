package com.lyra.meta.type;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;
import static com.lyra.util.internal.Validator.nullable;

import java.lang.reflect.Type;

import com.lyra.meta.Value;
import com.lyra.res.Symbol;

/**
 * Boolean type information
 *
 * @author Lang
 * @package com.test.lyra.meta.json.type
 * @name BooleanType
 * @class com.test.lyra.meta.json.type.BooleanType
 * @date Oct 15, 2014 6:15:57 AM
 * @see
 */
public class BooleanType implements Value<Boolean> {

	// ~ Instance Fields =====================================
	/**
	 * The value of boolean type item. *
	 */
	private final Boolean value;

	// ~ Constructors ========================================

	/**
	 * Constructor by boolean value. *
	 */
	public BooleanType(final Boolean value) {
		this.value = value;
	}

	/**
	 * Constructor by string value. *
	 */
	public BooleanType(final String value) {
		this.value = nullable(value) ? false : Boolean.parseBoolean(value);
	}

	// ~ Override Methods ====================================

	/**
	 * Get value of current item
	 */
	@Override
	public Boolean getValue() {
		return this.value;
	}

	/**
	 * Get data type of current item
	 */
	@Override
	public Type getType() {
		return Boolean.class;
	}

	// ~ hashCode,equals,toString ============================

	/**
	 * Override toString() of java.lang.Object
	 */
	@Override
	public String toString() {
		final StringBuilder retStr = builder();
		retStr.append(Symbol.C_COMMA).append("Value:").append(this.value);
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
			final BooleanType that = (BooleanType) obj;
			retValue &= equal(this.value, that.value);
		}
		return retValue;
	}
}
