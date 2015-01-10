package com.lyra.util.logger;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Size;
import net.sf.oval.guard.Guarded;

import org.apache.log4j.Level;

import com.lyra.res.Symbol;

/**
 * 调试用工具，用于记录日志
 *
 * @author Lang
 * @see
 */
@Guarded
public final class Logger {
	// ~ Static Methods ======================================
	/**
	 * TRACE LEVEL
	 * 
	 * @param clazz
	 * @param throwable
	 */
	public static void trace(@NotNull final Class<?> clazz,
			final Object message,
			@NotNull @Size(min = 0, max = 1) final Throwable... throwable) {
		final org.apache.log4j.Logger logger = getLogger(clazz);
		if (logger.isTraceEnabled()) {
			if (Symbol.I_ZERO < throwable.length) {
				logger.trace(message, throwable[Symbol.I_ZERO]);
			} else {
				logger.trace(message);
			}
		}
	}

	/**
	 * DEBUG LEVEL
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void debug(@NotNull final Class<?> clazz,
			final Object message,
			@NotNull @Size(min = 0, max = 1) final Throwable... throwable) {
		final org.apache.log4j.Logger logger = getLogger(clazz);
		if (logger.isDebugEnabled()) {
			if (Symbol.I_ZERO < throwable.length) {
				logger.debug(message, throwable[Symbol.I_ZERO]);
			} else {
				logger.debug(message);
			}
		}
	}

	/**
	 * INFO LEVEL
	 * 
	 * @param clazz
	 * @param message
	 */
	public static void info(@NotNull final Class<?> clazz,
			final Object message,
			@NotNull @Size(min = 0, max = 1) final Throwable... throwable) {
		final org.apache.log4j.Logger logger = getLogger(clazz);
		if (logger.isInfoEnabled()) {
			if (Symbol.I_ZERO < throwable.length) {
				logger.info(message, throwable[Symbol.I_ZERO]);
			} else {
				logger.info(message);
			}
		}
	}

	/**
	 * WARN LEVEL
	 * 
	 * @param clazz
	 * @param message
	 * @param throwable
	 */
	public static void warn(@NotNull final Class<?> clazz,
			final Object message,
			@NotNull @Size(min = 0, max = 1) final Throwable... throwable) {
		final org.apache.log4j.Logger logger = getLogger(clazz);
		if (logger.isEnabledFor(Level.WARN)) {
			if (Symbol.I_ZERO < throwable.length) {
				logger.warn(message, throwable[Symbol.I_ZERO]);
			} else {
				logger.warn(message);
			}
		}
	}

	/**
	 * ERROR LEVEL
	 * 
	 * @param clazz
	 * @param message
	 * @param throwable
	 */
	public static void error(@NotNull final Class<?> clazz,
			final Object message,
			@NotNull @Size(min = 0, max = 1) final Throwable... throwable) {
		final org.apache.log4j.Logger logger = getLogger(clazz);
		if (logger.isEnabledFor(Level.ERROR)) {
			if (Symbol.I_ZERO < throwable.length) {
				logger.error(message, throwable[Symbol.I_ZERO]);
			} else {
				logger.error(message);
			}
		}
	}
	/**
	 * FATAL LEVEL
	 * @param clazz
	 * @param message
	 * @param throwable
	 */
	public static void fatal(@NotNull final Class<?> clazz,
			final Object message,
			@NotNull @Size(min = 0, max = 1) final Throwable... throwable){
		final org.apache.log4j.Logger logger = getLogger(clazz);
		if (logger.isEnabledFor(Level.FATAL)) {
			if (Symbol.I_ZERO < throwable.length) {
				logger.fatal(message, throwable[Symbol.I_ZERO]);
			} else {
				logger.fatal(message);
			}
		}
	}

	// ~ Constructors ========================================
	private Logger() {
	}

	// ~ Private Methods =====================================
	@NotNull
	private static org.apache.log4j.Logger getLogger(
			@NotNull final Class<?> clazz) {
		return org.apache.log4j.Logger.getLogger(clazz);
	}
	// ~ hashCode,equals,toString ============================
}
