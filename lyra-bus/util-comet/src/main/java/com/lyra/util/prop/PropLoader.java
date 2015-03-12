package com.lyra.util.prop;

import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.internal.Validator.numeric;
import static com.lyra.util.logger.Logger.trace;
import static com.lyra.util.reflector.Factory.singleton;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import net.sf.oval.constraint.Digits;
import net.sf.oval.constraint.Max;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import net.sf.oval.guard.PostValidateThis;
import net.sf.oval.guard.PreValidateThis;

import com.lyra.res.Symbol;

/**
 * 属性文件加载器
 *
 * @author Lang
 * @see
 */
@Guarded
public final class PropLoader {
	// ~ Static Fields =======================================
	/** 资源文件池 **/
	private static final Map<String, Properties> PROP_POOL = hashMap(true);
	// ~ Instance Fields =====================================
	/**
	 * 当前实例加载的资源文件信息
	 */
	@NotNull
	private transient Properties prop;

	// ~ Constructors ========================================
	/**
	 * 构造函数
	 * 
	 * @param clazz
	 * @param resource
	 */
	@PostValidateThis
	PropLoader(@NotNull final Class<?> clazz,
			@NotNull @NotEmpty @NotBlank final String resource) {
		final URL restUrl = clazz.getResource(resource);
		if (!nullable(restUrl)) {
			this.prop = singleton(Properties.class, resource, PROP_POOL);
			try {
				this.prop.load(clazz.getResourceAsStream(resource));
			} catch (IOException ex) {
				trace(getClass(), ex);
			}
			PROP_POOL.put(resource, this.prop);
		}
	}

	// ~ Methods =============================================
	/**
	 * 根据属性key获取Long属性值
	 * @param propKey
	 * @return
	 */
	@Digits
	@Max(Long.MAX_VALUE)
	@Min(Long.MIN_VALUE)
	public long getLong(
			@NotNull @NotEmpty @NotBlank final String propKey){
		return this.getInt(propKey);
	}
	/**
	 * 根据属性key获取Integer属性值
	 * @param propKey
	 * @return
	 */
	@Digits
	@Max(Integer.MAX_VALUE)
	@Min(Integer.MIN_VALUE)
	public int getInt(
			@NotNull @NotEmpty @NotBlank final String propKey){
		final String orgValue = this.getString(propKey);
		int retValue = Symbol.I_NEG_ONE;
		if(numeric(orgValue)){
			retValue = Integer.parseInt(orgValue.trim());
		}
		return retValue;
	}
	/**
	 * 根据属性key获取Boolean属性值
	 * @param propKey
	 * @return
	 */
	public boolean getBoolean(
			@NotNull @NotEmpty @NotBlank final String propKey){
		final String retValue = this.getString(propKey);
		boolean ret = false;
		if(!nullable(retValue)){
			ret = Boolean.parseBoolean(retValue.trim());
		}
		return ret;
	}
	/**
	 * 根据属性Key获取String属性值
	 * 
	 * @param propKey
	 * @return
	 */
	public String getString(
			@NotNull @NotEmpty @NotBlank final String propKey) {
		// 过滤值null
		String ret = this.getProp().getProperty(propKey);
		if(!nullable(ret) && Symbol.STR_NULL_LV.equals(ret)){
			ret = Symbol.STR_NULL;
		}
		return ret;
	}
	/**
	 * 
	 * @return
	 */
	@NotNull
	@PreValidateThis
	public Properties getProp(){
		return this.prop;
	}
	/**
	 * 
	 * @param resource
	 * @return
	 */
	public Properties getProp(
			@NotNull @NotEmpty @NotBlank final String resource){
		return PROP_POOL.get(resource);
	}
	// ~ Private Methods =====================================
	
	// ~ hashCode,equals,toString ============================
}
