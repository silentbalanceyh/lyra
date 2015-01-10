package com.test.lyra.util.logger;

import com.test.base.AbstractTestCase;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import static com.lyra.util.logger.Logger.debug;
import static com.lyra.util.logger.Logger.trace;
import static org.junit.Assert.assertTrue;

/**
 * 单测方法：com.lyra.util.logger.Logger.debug
 *
 * @author Lang
 * @see
 */
public class LoggerDebugTestCase extends AbstractTestCase implements
		LoggerConstant {
	// ~ Constructors ========================================
	/**
     *
     */
	public LoggerDebugTestCase() {
		super(TestClasses.LOGGER);
		setMethod(M_DEBUG);
	}

	// ~ Methods =============================================

	/**
	 * Logger.debug(Class<?>,Object,Throwable...)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testLogger1() {
		debug(null, "debug 1");
		failure(null);
	}

	/**
	 * Logger.debug(Class<?>,Object,Throwable...)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testLogger2() {
		final Exception[] exps = new Exception[3];
		debug(getClass(), "debug 2", exps);
		failure(null);
	}

	/**
     * 
     */
	@Test
	public void testLogger3() {
		boolean ret = false;
		try {
			debug(getClass(), "DEBUG LEVEL!");
			ret = true;
		} catch (ConstraintsViolatedException ex) {
			trace(getClass(), ex);
			ret = false;
		}
		assertTrue(getPosition(), ret);
	}

	// ~ Private Methods =====================================
}
