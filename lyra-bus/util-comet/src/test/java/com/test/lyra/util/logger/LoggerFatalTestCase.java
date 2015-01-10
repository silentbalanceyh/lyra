package com.test.lyra.util.logger;

import static com.lyra.util.logger.Logger.fatal;
import static com.lyra.util.logger.Logger.trace;
import static org.junit.Assert.assertTrue;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.test.base.AbstractTestCase;

/**
 * 单测方法：com.lyra.util.logger.Logger.fatal
 *
 * @author Lang
 * @see
 */
public class LoggerFatalTestCase extends AbstractTestCase implements LoggerConstant{
    // ~ Constructors ========================================
    /**
     *
     */
    public LoggerFatalTestCase(){
        super(TestClasses.LOGGER);
        setMethod(M_FATAL);
    }
    // ~ Methods =============================================

    /**
     * Logger.fatal(Class<?>,Object,Throwable...)
     */
    @Test(expected = ConstraintsViolatedException.class)
    public void testLogger1(){
        fatal(null,"fatal 1");
        failure(null);
    }

    /**
     * Logger.fatal(Class<?>,Object,Throwable...)
     */
    @Test(expected = ConstraintsViolatedException.class)
    public void testLogger2(){
        final Exception[] exps = new Exception[3];
        fatal(getClass(),"fatal 2",exps);
        failure(null);
    }
	/**
     * 
     */
	@Test
	public void testLogger3() {
		boolean ret = false;
		try {
			fatal(getClass(), "FATAL LEVEL!");
			ret = true;
		} catch (ConstraintsViolatedException ex) {
			trace(getClass(), ex);
			ret = false;
		}
		assertTrue(getPosition(), ret);
	}
    // ~ Private Methods =====================================
}
