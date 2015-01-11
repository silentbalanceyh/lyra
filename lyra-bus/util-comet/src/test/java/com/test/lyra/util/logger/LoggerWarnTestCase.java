package com.test.lyra.util.logger;

import static com.lyra.util.logger.Logger.trace;
import static com.lyra.util.logger.Logger.warn;
import static org.junit.Assert.assertTrue;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;

/**
 *
 * 单测方法：com.lyra.util.logger.Logger.warn
 *
 * @author Lang
 * @see
 */
public class LoggerWarnTestCase extends AbstractTestCase implements LoggerConstant{
    // ~ Constructors ========================================
    /**
     *
     */
    public LoggerWarnTestCase(){
        super(TestClasses.LOGGER);
        setMethod(M_WARN);
    }
    // ~ Methods =============================================

    /**
     * Logger.warn(Class<?>,Object,Throwable...)
     */
    @Test(expected = ConstraintsViolatedException.class)
    public void testLogger1(){
        warn(null,"warn 1");
        failure(null);
    }

    /**
     * Logger.warn(Class<?>,Object,Throwable...)
     */
    @Test(expected = ConstraintsViolatedException.class)
    public void testLogger2(){
        final Exception[] exps = new Exception[3];
        warn(getClass(),"warn 2",exps);
        failure(null);
    }
	/**
     * 
     */
	@Test
	public void testLogger3() {
		boolean ret = false;
		try {
			warn(getClass(), "WARN LEVEL!");
			ret = true;
		} catch (ConstraintsViolatedException ex) {
			trace(getClass(), ex);
			ret = false;
		}
		assertTrue(getPosition(), ret);
	}
    // ~ Private Methods =====================================
}
