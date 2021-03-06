package com.test.lyra.util.prop;

import static com.lyra.util.reflector.Factory.instance;
import static org.junit.Assert.assertEquals;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.prop.PropLoader;
import com.lyra.util.test.AbstractTestCase;
/**
 * 
 *
 * @author Lang
 * @see
 */
public class PropLoader4TestCase extends AbstractTestCase implements
		PropConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public PropLoader4TestCase() {
		super(TestClasses.PROP_LOADER);
		loader = instance(PropLoader.class, getClass(), TEST_FILE);
	}

	// ~ Methods =============================================
	/**
	 * 
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testGetInt1() {
		setMethod(M_GET_INT);
		final int ret = this.getLoader().getInt(null);
		failure(ret);
	}
	/**
	 * 
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testGetInt2() {
		setMethod(M_GET_INT);
		final int ret = this.getLoader().getInt("");
		failure(ret);
	}
	/**
	 * 
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testGetInt3() {
		setMethod(M_GET_INT);
		final int ret = this.getLoader().getInt("   ");
		failure(ret);
	}
	/**
	 * 
	 */
	@Test
	public void testGetInt4() {
		setMethod(M_GET_INT);
		final int ret = this.getLoader().getInt("x.test");
		assertEquals(getPosition(),-1,ret);
	}
	/**
	 * 
	 */
	@Test
	public void testGetInt5() {
		setMethod(M_GET_INT);
		final int ret = this.getLoader().getInt("test.intexp");
		assertEquals(getPosition(),-1,ret);
	}
	
	/**
	 * 
	 */
	@Test
	public void testGetInt6() {
		setMethod(M_GET_INT);
		final int ret = this.getLoader().getInt("test.int");
		assertEquals(getPosition(),2015,ret);
	}
	// ~ Private Methods =====================================
}
