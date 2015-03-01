package com.lyra.meta.data.type;

import java.lang.reflect.Type;
import java.util.Date;

import jodd.typeconverter.Convert;

import com.lyra.meta.DataType;
import com.lyra.meta.Value;
/**
 * 类型：时间/日期类型
 *
 * @author Lang
 * @see
 */
public class DateType implements Value<Date>{
	// ~ Instance Fields =====================================
	/** **/
	private Date value = new Date();
	// ~ Constructors ========================================
	/** **/
	public DateType(){
		this(new Date());
	}
	/** **/
	public DateType(final Long value){
		this.value = new Date(value);
	}
	/** **/
	public DateType(final String value){
		this.value = Convert.toDate(value,new Date());
	}
	/** **/
	public DateType(final Date value){
		this.value = value;
	}
	// ~ Override Methods ====================================
	/** **/
	@Override
	public Date getValue(){
		return this.value;
	}
	/** **/
	@Override
	public void setValue(final Date value){
		this.value = value;
	}
	/** **/
	@Override
	public Type getType(){
		return Date.class;
	}
	/** **/
	@Override
	public DataType getDataType(){
		return DataType.DATE;
	}
	// ~ Methods =============================================
	/** **/
	public void setValue(final Long value){
		this.value = new Date(value);
	}
	/** **/
	public void setValue(final String value){
		this.value = Convert.toDate(value, new Date());
	}
	// ~ hashCode,equals,toString ============================
	/** **/
	@Override
	public String toString() {
		return "DateType [value=" + value + "]";
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
		final DateType other = (DateType) obj;
		if (value == null) {
			if (other.value != null)
				return false;	// NOPMD
		} else if (!value.equals(other.value))
			return false;	// NOPMD
		return true;
	}
}
