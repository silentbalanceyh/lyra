package com.test.lyra.util.reflector;

import static com.lyra.util.reflector.Types.interfaces;
import static org.junit.Assert.assertEquals;

import java.util.List;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;
import com.test.lyra.util.assist.C;
import com.test.lyra.util.assist.D;
import com.test.lyra.util.assist.E;
import com.test.lyra.util.assist.E1;

/**
 * 测试方法：com.lyra.util.reflector.Types.interfaces
 *
 * @author Lang
 * @see
 */
public class TypesInterfacesTestCase extends AbstractTestCase implements
		ReflectorConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public TypesInterfacesTestCase(){
		super(TestClasses.TYPES);
		setMethod(M_INTERFACES);
	}
	// ~ Methods =============================================
	/**
	 * Types.types(null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testInterfaces1(){
		final List<Class<?>> ret = interfaces(null);
		failure(ret);
	}
	/**
	 * Types.types(E)
	 */
	@Test
	public void testTypes2(){
		final List<Class<?>> ret = interfaces(E.class);
		assertEquals(getPosition(),3,ret.size());
	}
	/**
	 * Types.types(C)
	 */
	@Test
	public void testTypes3(){
		final List<Class<?>> ret = interfaces(C.class);
		assertEquals(getPosition(),4,ret.size());
	}
	/**
	 * Types.types(E1): interface
	 */
	@Test
	public void testTypes4(){
		final List<Class<?>> ret = interfaces(E1.class);
		assertEquals(getPosition(),2,ret.size());
	}
	
	/**
	 * Types.types(D)
	 */
	@Test
	public void testTypes5(){
		final List<Class<?>> ret = interfaces(D.class);
		assertEquals(getPosition(),8,ret.size());
	}
	// ~ Private Methods =====================================
}
