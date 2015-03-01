package com.test.lyra.util;

import static com.lyra.util.Instance.instance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Properties;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.prop.PropertyLoader;
import com.lyra.util.test.AbstractTestCase;

/**
 * 测试com.lyra.util.prop.PropLoader
 *
 * @author Lang
 * @see
 */
public class PropLoaderTestCase extends AbstractTestCase implements PropConstant{
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public PropLoaderTestCase(){
		super(TestClasses.PROP_LOADER);
		loader = instance(PropertyLoader.class,getClass(),TEST_FILE);
	}
	// ~ Methods =============================================
	/**
	 * 构造函数测试
	 */
	@Test
	public void testPropLoader1(){
		setMethod("Constructor!");
		final PropertyLoader instance = instance(PropertyLoader.class,getClass(),TEST_FILE);
		assertNotNull(getPosition(),instance);
	}
	
	/**
	 * 构造函数的PostValidateThis测试
	 * @return
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testPropLoader2(){
		setMethod("Constructor!");
		final PropertyLoader instance = instance(PropertyLoader.class,getClass(),"x.properties");
		assertNotNull(getPosition(),instance);
	}
	/**
	 * 
	 */
	@Test
	public void testGetProp1(){
		setMethod(M_GET_PROP1);
		final Properties prop = this.getLoader().getProp();
		assertNotNull(getPosition(),prop);
	}
	/**
	 * 
	 */
	@Test
	public void testGetProp2(){
		setMethod(M_GET_PROP2);
		final Properties prop = this.getLoader().getProp(TEST_FILE);
		final Properties prop1 = this.getLoader().getProp();
		assertEquals(getPosition(),prop,prop1);
	}
	/**
	 * 
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testGetProp3(){
		setMethod(M_GET_PROP2);
		final Properties prop = this.getLoader().getProp(null);
		failure(prop);
	}
	/**
	 * 
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testGetProp4(){
		setMethod(M_GET_PROP2);
		final Properties prop = this.getLoader().getProp("");
		failure(prop);
	}
	/**
	 * 
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testGetProp5(){
		setMethod(M_GET_PROP2);
		final Properties prop = this.getLoader().getProp("   ");
		failure(prop);
	}
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
