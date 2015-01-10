package com.lyra.meta.type;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

import java.lang.reflect.Type;

import com.lyra.meta.Value;
import com.lyra.res.Symbol;

/**
 * Boolean type information
 *
 * @author Lang
 * @package com.test.lyra.meta.json.type
 * @name StringType
 * @class com.test.lyra.meta.json.type.StringType
 * @date Oct 15, 2014 6:15:45 AM
 * @see
 */
public class StringType implements Value<String> {

	// ~ Instance Fields =====================================
	/**
	 * The value of string type item. *
	 */
	private final String value;

	// ~ Constructors ========================================

	/**
	 * StringType's constructor *
	 */
	public StringType(final String value) {
		this.value = value;
	}

	// ~ Override Methods ====================================

	/**
	 * Get current string item's value
	 */
	@Override
	public String getValue() {
		return value;
	}

	/**
	 * Get current string item's data type
	 */
	@Override
	public Type getType() {
		return String.class;
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
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(final Object obj) {
		boolean retValue = true & equal(this, obj, getClass(), true);
		if (retValue) {
			final StringType that = (StringType) obj;
			retValue &= equal(this.value, that.value);
		}
		return retValue;
	}
}
