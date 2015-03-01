package com.lyra.meta.data.type;

import java.lang.reflect.Type;

import jodd.mutable.MutableLong;
import jodd.typeconverter.Convert;

import com.lyra.meta.DataType;
import com.lyra.meta.Value;

/**
 * 类型：长整类型
 *
 * @author Lang
 * @see
 */
public class LongType implements Value<Long> {
	// ~ Instance Fields =====================================
	/** **/
	private final MutableLong value = new MutableLong(0L);

	// ~ Constructors ========================================
	/** **/
	public LongType() {
		this(0L);
	}

	/** **/
	public LongType(final Integer value) {
		this.init(value);
	}

	/** **/
	public LongType(final Long value) {
		this.value.setValue(value);
	}

	/** **/
	public LongType(final String value) {
		this.init(value);
	}

	// ~ Override Methods ====================================
	/** **/
	@Override
	public Long getValue() {
		return this.value.getValue();
	}

	/** **/
	@Override
	public void setValue(final Long value) {
		this.value.setValue(value);
	}

	/** **/
	@Override
	public Type getType() {
		return Long.class;
	}

	/** **/
	@Override
	public DataType getDataType() {
		return DataType.LONG;
	}

	// ~ Methods =============================================
	/** **/
	public void setValue(final Integer value) {
		this.init(value);
	}

	/** **/
	public void setValue(final String value) {
		this.init(value);
	}

	// ~ Private Methods =====================================
	/**
	 * 
	 * @param value
	 */
	private void init(final Object value) {
		this.value.setValue(Convert.toLongValue(value, 0L));
	}

	// ~ hashCode,equals,toString ============================
	/** **/
	@Override
	public String toString() {
		return "LongType [value=" + value + "]";
	}

	/** **/
	@Override
	public int hashCode() {
		final int prime = 31;	// NOPMD
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/** **/
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;	// NOPMD
		if (obj == null)
			return false;	// NOPMD
		if (getClass() != obj.getClass())
			return false;	// NOPMD
		final LongType other = (LongType) obj;
		if (value == null) {
			if (other.value != null)
				return false;	// NOPMD
		} else if (!value.equals(other.value))
			return false;	// NOPMD
		return true;
	}
}