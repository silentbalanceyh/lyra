package com.lyra.util.converter;

import java.util.Locale;
import java.util.UUID;

import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * 字符串转换
 * 
 * @author Lang
 * @see
 */
@Guarded
public final class StringConverter {
	// ~ Static Methods ======================================
	/**
	 * 将输入字符串转换成大写：Upper Case
	 * 
	 * @param literal
	 * @return
	 */
	@NotNull
	@NotEmpty
	@NotBlank
	public static String upper(@NotNull @NotEmpty @NotBlank final String literal) {
		return literal.toUpperCase(Locale.getDefault());
	}

	/**
	 * 将输入字符串转换成小写：Lower Case
	 * 
	 * @param literal
	 * @return
	 */
	@NotNull
	@NotEmpty
	@NotBlank
	public static String lower(@NotNull @NotEmpty @NotBlank final String literal) {
		return literal.toLowerCase(Locale.getDefault());
	}

	/**
	 * 生成UUID字符串
	 * 
	 * @param isUpperCase
	 * @return
	 */
	@NotNull
	@NotEmpty
	@NotBlank
	@MinLength(36)
	@MaxLength(36)
	public static String uuid(final boolean isUpperCase) {
		String retUuid = UUID.randomUUID().toString();
		if (isUpperCase) {
			retUuid = upper(retUuid);
		} else {
			retUuid = lower(retUuid);
		}
		return retUuid;
	}

	// ~ Constructors ========================================
	private StringConverter() {
	}
}
