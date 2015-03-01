package com.lyra.meta.data.type;

import java.lang.reflect.Type;

import com.lyra.exception.unchecked.TypeInitException;
import com.lyra.meta.DataType;
import com.lyra.meta.Validator;
import com.lyra.meta.Value;

/**
 * 类型：字符串类型
 * 
 * @author Lang
 * @see
 */
public class StringType implements Value<String>, Validator {
	// ~ Instance Fields =====================================
	/**
	 * 字符串缓冲池
	 */
	protected StringBuilder value = new StringBuilder(16); // NOPMD
	/**
	 * Runtime异常
	 */
	protected transient TypeInitException exp = null;	// NOPMD

	// ~ Constructors ========================================
	/**
	 * 构造一个新的空字符串
	 */
	public StringType() {
		this("");
	}

	/**
	 * 清空缓冲池，重新构造
	 * 
	 * @param value
	 */
	public StringType(final String value) {
		this.init(value);
	}

	// ~ Override Methods ====================================
	/**
	 * 获取字符串的值
	 */
	@Override
	public String getValue() {
		String ret = null;
		if (null != this.value) {
			ret = this.value.toString();
		}
		return ret;
	}

	/**
	 * 重新设置字符串的值，防止新建对象
	 */
	@Override
	public void setValue(final String value) {
		this.init(value);
	}

	/**
	 * 获取原始值的java类型，返回java.lang.String
	 */
	@Override
	public Type getType() {
		return String.class;
	}

	/**
	 * 获取DataType自定义的Lyra数据类型
	 */
	@Override
	public DataType getDataType() {
		return DataType.STRING;
	}

	/**
	 * 字符串验证规则
	 */
	@Override
	public boolean validate(final String value) {
		return true;
	}
	// ~ Methods =============================================
	/** **/
	public RuntimeException getError(){
		return this.exp;
	}
	// ~ Private Methods =====================================
	/**
	 * 内部初始化函数
	 * 
	 * @param value
	 */
	private void init(final String value) {
		if (null == value) {
			this.value = null;	// NOPMD
		} else {
			// StringType直接返回true
			if (this.validate(value)) {
				this.value.delete(0, this.value.length());
				this.value.append(value);
			} else {
				// 验证不通过初始化失败
				this.exp = new TypeInitException(getClass(), "void init(String)", value);
				throw exp;
			}
		}

	}

	// ~ hashCode,equals,toString ============================
	/** **/
	@Override
	public String toString() {
		return "StringType [value=" + value + "]";
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
		final StringType other = (StringType) obj;
		if (value == null) {
			if (other.value != null)
				return false;	// NOPMD
		} else if (!value.toString().equals(other.value.toString()))
			return false;	// NOPMD
		return true;
	}
}