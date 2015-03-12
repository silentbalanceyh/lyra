package com.lyra.util.logger;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Logger.info;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

import com.lyra.res.Symbol;

/**
 * 用于INFO级别的输出信息，在Console中输出不同数据结构的内容 不支持嵌套输出，Deep对象直接调用的是toString
 * 
 * @author Lang
 * @see
 */
@Guarded
public final class Console {
	// ~ Static Methods ======================================
	/**
	 * 输出Collection集合信息
	 * 
	 * @param clazz
	 * @param collection
	 */
	public static void console(@NotNull final Class<?> clazz,
			@NotNull final Collection<?> collection) {
		final StringBuilder content = builder();
		if (collection instanceof List) {
			content.append("[List] : ");
		} else {
			content.append("[Set] : ");
		}
		setBuffer(content, collection.toArray());
		info(clazz, content);
	}

	/**
	 * 输出Array数组的信息
	 * 
	 * @param clazz
	 * @param array
	 */
	public static <T> void console(@NotNull final Class<?> clazz,
			@NotNull final T[] array) {
		final StringBuilder content = builder();
		content.append("[Array] : ");
		setBuffer(content, array);
		info(clazz, content);
	}

	/**
	 * 输出Map的信息
	 * 
	 * @param clazz
	 * @param map
	 */
	public static <T> void console(@NotNull final Class<?> clazz,
			@NotNull final Map<?, ?> map) {
		final StringBuilder content = builder();
		content.append("[Map] : ").append(Symbol.C_L_BRACKET)
				.append(" Items: ").append(map.size()).append(" ) => [");
		for (final Object key : map.keySet()) {
			content.append(key).append(" -> ").append(map.get(key))
					.append(Symbol.C_WHITESPACE).append(Symbol.C_COMMA);
		}
		if (!map.isEmpty()) {
			content.delete(content.length() - 1, content.length());
		}
		content.append(Symbol.C_RS_BRACKET);
		info(clazz, content);
	}

	// ~ Constructors ========================================
	private Console() {
	}

	// ~ Private Methods =====================================
	private static <T> void setBuffer(@NotNull final StringBuilder builder,
			@NotNull final T[] array) {
		builder.append(Symbol.C_L_BRACKET).append(" Items: ")
				.append(array.length).append(" ) => [");
		final int length = array.length;
		for (int i = 0; i < length; i++) {
			builder.append(nullable(array[i]) ? Symbol.STR_EMPTY : array[i]
					.toString());
			if ((i + 1) < length) {
				builder.append(Symbol.C_COMMA);
			}
		}
		builder.append(Symbol.C_RS_BRACKET);
	}
	// ~ hashCode,equals,toString ============================
}
