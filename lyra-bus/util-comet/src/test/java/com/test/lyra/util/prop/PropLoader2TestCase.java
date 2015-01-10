package com.test.lyra.util.prop;

import static com.lyra.util.reflector.Factory.instance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.prop.PropLoader;
import com.test.base.AbstractTestCase;

/**
 * 
 *
 * @author Lang
 * @see
 */
public class PropLoader2TestCase extends AbstractTestCase implements
		PropConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public PropLoader2TestCase() {
		super(TestClasses.PROP_LOADER);
		loader = instance(PropLoader.class, getClass(), TEST_FILE);
	}

	// ~ Methods =============================================
	/**
	 * 
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testGetString1(){
		setMethod(M_GET_STRING);
		final String ret = this.getLoader().getString(null);
		failure(ret);
	}
	/**
	 * 
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testGetString2(){
		setMethod(M_GET_STRING);
		final String ret = this.getLoader().getString("");
		failure(ret);
	}
	/**
	 * 
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testGetString3(){
		setMethod(M_GET_STRING);
		final String ret = this.getLoader().getString("   ");
		failure(ret);
	}
	/**
	 * 
	 */
	@Test
	public void testGetString4(){
		setMethod(M_GET_STRING);
		final String ret = this.getLoader().getString("x.test");
		assertNull(getPosition(),ret);
	}
	/**
	 * 
	 */
	@Test
	public void testGetString5(){
		setMethod(M_GET_STRING);
		final String ret = this.getLoader().getString("test.null");
		assertNull(getPosition(),ret);
	}
	/**
	 * 
	 */
	@Test
	public void testGetString6(){
		setMethod(M_GET_STRING);
		final String ret = this.getLoader().getString("test.str");
		assertEquals(getPosition(),"Hello Lang Yu",ret);
	}
	
	// ~ Private Methods =====================================
}
