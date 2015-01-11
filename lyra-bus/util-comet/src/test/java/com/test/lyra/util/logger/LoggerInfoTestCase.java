package com.test.lyra.util.logger;

import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.logger.Logger.trace;
import static org.junit.Assert.assertTrue;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;

/**
 * 单测试方法：com.lyra.util.logger.Logger.info
 *
 * @author Lang
 * @see
 */
public class LoggerInfoTestCase extends AbstractTestCase implements LoggerConstant{
    // ~ Constructors ========================================
    /**
     *
     */
    public LoggerInfoTestCase(){
        super(TestClasses.LOGGER);
        setMethod(M_INFO);
    }
    // ~ Methods =============================================

    /**
     * Logger.info(Class<?>,Object,Throwable...)
     */
    @Test(expected = ConstraintsViolatedException.class)
    public void testLogger1(){
        info(null,"info 1");
        failure(null);
    }

    /**
     * Logger.info(Class<?>,Object,Throwable...)
     */
    @Test(expected = ConstraintsViolatedException.class)
    public void testLogger2(){
        final Exception[] exps = new Exception[3];
        info(getClass(),"info 2",exps);
        failure(null);
    }
	/**
     * 
     */
	@Test
	public void testLogger3() {
		boolean ret = false;
		try {
			info(getClass(), "INFO LEVEL!");
			ret = true;
		} catch (ConstraintsViolatedException ex) {
			trace(getClass(), ex);
			ret = false;
		}
		assertTrue(getPosition(), ret);
	}
    // ~ Private Methods =====================================
}
