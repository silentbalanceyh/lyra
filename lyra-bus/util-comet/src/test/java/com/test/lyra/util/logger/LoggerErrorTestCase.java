package com.test.lyra.util.logger;

import static com.lyra.util.logger.Logger.error;
import static com.lyra.util.logger.Logger.trace;
import static org.junit.Assert.assertTrue;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.test.base.AbstractTestCase;

/**
 * 单测方法：com.lyra.util.logger.Logger.error
 *
 * @author Lang
 * @see
 */
public class LoggerErrorTestCase extends AbstractTestCase implements LoggerConstant{
    // ~ Constructors ========================================
    /**
     *
     */
    public LoggerErrorTestCase(){
        super(TestClasses.LOGGER);
        setMethod(M_ERROR);
    }
    // ~ Methods =============================================

    /**
     * Logger.error(Class<?>,Object,Throwable...)
     */
    @Test(expected = ConstraintsViolatedException.class)
    public void testLogger1(){
        error(null,"error 1");
        failure(null);
    }

    /**
     * Logger.error(Class<?>,Object,Throwable...)
     */
    @Test(expected = ConstraintsViolatedException.class)
    public void testLogger2(){
        final Exception[] exps = new Exception[3];
        error(getClass(),"error 2",exps);
        failure(null);
    }
	/**
     * 
     */
	@Test
	public void testLogger3() {
		boolean ret = false;
		try {
			error(getClass(), "ERROR LEVEL!");
			ret = true;
		} catch (ConstraintsViolatedException ex) {
			trace(getClass(), ex);
			ret = false;
		}
		assertTrue(getPosition(), ret);
	}
    // ~ Private Methods =====================================
}
