package com.test.lyra.util.logger;

import static com.lyra.util.logger.Logger.trace;
import static org.junit.Assert.assertTrue;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;

/**
 * 单测方法：com.lyra.util.logger.Logger.trace
 *
 * @author Lang
 * @see
 */
public class LoggerTraceTestCase extends AbstractTestCase implements LoggerConstant{
    // ~ Constructors ========================================
    /**
     *
     */
    public LoggerTraceTestCase(){
        super(TestClasses.LOGGER);
        setMethod(M_TRACE);
    }
    // ~ Methods =============================================

    /**
     * Logger.trace(Class<?>,Object,Throwable...)
     */
    @Test(expected = ConstraintsViolatedException.class)
    public void testLogger1(){
        trace(null,"Trace 1");
        failure(null);
    }

    /**
     * Logger.trace(Class<?>,Object,Throwable...)
     */
    @Test(expected = ConstraintsViolatedException.class)
    public void testLogger2(){
        final Exception[] exps = new Exception[3];
        trace(getClass(),"Trace 2",exps);
        failure(null);
    }
	/**
     * 
     */
	@Test
	public void testLogger3() {
		boolean ret = false;
		try {
			trace(getClass(), "TRACE LEVEL!");
			ret = true;
		} catch (ConstraintsViolatedException ex) {
			trace(getClass(), ex);
			ret = false;
		}
		assertTrue(getPosition(), ret);
	}
    // ~ Private Methods =====================================
}
