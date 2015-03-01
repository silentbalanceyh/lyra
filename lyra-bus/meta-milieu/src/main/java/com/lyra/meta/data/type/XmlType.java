package com.lyra.meta.data.type;

import jodd.jerry.Jerry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyra.meta.DataType;
import com.lyra.meta.Value;

/**
 * 类型：Xml格式
 *
 * @author Lang
 * @see
 */
public class XmlType extends StringType implements Value<String>{
	// ~ Static Fields =======================================
	/** **/
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlType.class);
	// ~ Instance Fields =====================================
	/** **/
	private static final Jerry.JerryParser PARSER = new Jerry.JerryParser();
	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	/** **/
	public XmlType(final String value){
		super(value);
	}
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	/** **/
	@Override
	public boolean validate(final String value){
		Jerry doc = PARSER.parse(value);
		return null != doc;
	}
	/** **/
	@Override
	public DataType getDataType(){
		return DataType.XML;
	}
	// ~ Methods =============================================
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
	/** **/
	@Override
	public String toString() {
		return "XmlType [value=" + value + "]";
	}
}
