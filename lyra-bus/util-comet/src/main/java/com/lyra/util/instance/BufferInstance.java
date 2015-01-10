package com.lyra.util.instance;

import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

import com.lyra.res.Symbol;

/**
 * 封装了创建String Buffer/Builder的方法
 *
 * @author Lang
 * @see
 */
@Guarded
public final class BufferInstance {
	// ~ Static Methods ======================================

	/**
	 * 新建{@link java.lang.StringBuilder}缓冲区: Empty
	 *
	 * @return
	 */
	@NotNull
	public static StringBuilder builder() {
		return new StringBuilder(Symbol.I_STR_SIZE);
	}

	/**
	 * 新建{@link java.lang.StringBuilder}缓冲区：By Size
	 *
	 * @param size
	 * @return
	 */
	@NotNull
	public static StringBuilder builder(@Min(0) final int size) {
		return new StringBuilder(size);
	}

	/**
	 * 新建{@link java.lang.StringBuilder}缓冲区：By Content
	 *
	 * @param content
	 * @return
	 */
	@NotNull
	public static StringBuilder builder(@NotNull final String content) {
		return new StringBuilder(content);
	}

	/**
	 * 新建{@link java.lang.StringBuffer}缓冲区：Empty
	 *
	 * @return
	 */
	@NotNull
	public static StringBuffer buffer() {
		return new StringBuffer(Symbol.I_STR_SIZE);
	}

	/**
	 * 新建{@link java.lang.StringBuffer}缓冲区：By Size
	 *
	 * @param size
	 * @return
	 */
	@NotNull
	public static StringBuffer buffer(@Min(0) final int size) {
		return new StringBuffer(Symbol.I_STR_SIZE);
	}

	/**
	 * 新建{@link java.lang.StringBuffer}缓冲区：By Content
	 *
	 * @param content
	 * @return
	 */
	@NotNull
	public static StringBuffer buffer(@NotNull final String content) {
		return new StringBuffer(content);
	}

	// ~ Constructors ========================================
	private BufferInstance() {
	}
}
