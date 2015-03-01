package com.lyra.meta.data.type;

import jodd.json.JsonException;
import jodd.json.JsonParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyra.meta.DataType;
import com.lyra.meta.Value;

/**
 * 类型: Xml格式的字符串
 *
 * @author Lang
 * @see
 */
public class JsonType extends StringType implements Value<String> {
	// ~ Static Fields =======================================
	/** **/
	private static final Logger LOGGER = LoggerFactory.getLogger(JsonType.class);
	// ~ Instance Fields =====================================
	/** **/
	private static final JsonParser PARSER = new JsonParser();
	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	/** **/
	public JsonType(final String value){
		super(value);
	}
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	/** **/
	@Override
	public boolean validate(final String value) {
		boolean ret = false;
		try {
			PARSER.parse(value);
			ret = true;
		} catch (JsonException ex) {
			if(LOGGER.isErrorEnabled()){
				LOGGER.error("[E] Json format error! Input = " + value,ex);
			}
			ret = false;
		}
		return ret;
	}

	/** **/
	@Override
	public DataType getDataType() {
		return DataType.JSON;
	}

	// ~ Methods =============================================
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
	/** **/
	@Override
	public String toString() {
		return "JsonType [value=" + value + "]";
	}
}
