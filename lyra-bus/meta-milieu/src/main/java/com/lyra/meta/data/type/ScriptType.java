package com.lyra.meta.data.type;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jodd.log.Logger;
import jodd.log.LoggerFactory;

import com.lyra.meta.DataType;
import com.lyra.meta.Value;

/**
 * 类型：Script格式【默认JavaScript】
 *
 * @author Lang
 * @see
 */
public class ScriptType extends StringType implements Value<String>{
	// ~ Static Fields =======================================
	/** **/
	private static final Logger LOGGER = LoggerFactory.getLogger(ScriptType.class);
	// ~ Instance Fields =====================================
	/** **/
	private static final ScriptEngine ENGINE = new ScriptEngineManager().getEngineByName("javascript");
	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	/** **/
	public ScriptType(final String value){
		super(value);
	}
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	/** **/
	@Override
	public boolean validate(final String value){
		boolean ret = false;
		try{
			ENGINE.eval(value);
			ret = true;
		}catch(ScriptException ex){
			if(LOGGER.isErrorEnabled()){
				LOGGER.error("[E] Script error! Input = " + value, ex);
			}
			ret = false;
		}
		return ret;
	}
	
	/** **/
	@Override
	public DataType getDataType(){
		return DataType.SCRIPT;
	}
	// ~ Methods =============================================
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
	/** **/
	@Override
	public String toString() {
		return "ScriptType [value=" + value + "]";
	}
}
