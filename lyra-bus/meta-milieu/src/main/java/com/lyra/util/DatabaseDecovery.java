package com.lyra.util;

import jodd.util.StringUtil;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * 数据库分析
 *
 * @author Lang
 * @see
 */
@Guarded
public final class DatabaseDecovery {
	// ~ Static Fields =======================================
	// ~ Instance Fields =====================================
	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	/**
	 * 
	 * @param version
	 * @return
	 */
	public static String getSqlServer(
			@NotNull @NotBlank @NotEmpty final String version){
		String ret = null;
		if(-1 != StringUtil.startsWithOne(version,new String[]{"9","10","11","12"})){
			if(version.startsWith("9.")){
				ret = "2005";
			}else if(version.startsWith("10")){
				if(version.startsWith("10.5")){
					ret = "2008R2";
				}else{
					ret = "2008";
				}
			}else if(version.startsWith("11")){
				ret = "2012";
			}else if(version.startsWith("12")){
				ret = "2014";
			}
		}
		return ret;
	}
	// ~ Constructors ========================================
	private DatabaseDecovery(){}
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	// ~ Methods =============================================
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
