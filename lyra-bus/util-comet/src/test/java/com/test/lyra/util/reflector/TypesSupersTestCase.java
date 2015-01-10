package com.test.lyra.util.reflector;

import static com.lyra.util.reflector.Types.supers;
import static org.junit.Assert.assertEquals;

import java.util.List;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.test.base.AbstractTestCase;
import com.test.lyra.util.assist.AA;
import com.test.lyra.util.assist.CA;
import com.test.lyra.util.assist.D;
import com.test.lyra.util.assist.E1;

/**
 * 测试方法：com.lyra.util.reflector.Types.supers
 *
 * @author Lang
 * @see
 */
public class TypesSupersTestCase extends AbstractTestCase implements
		ReflectorConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public TypesSupersTestCase(){
		super(TestClasses.TYPES);
		setMethod(M_SUPERS);
	}
	// ~ Methods =============================================
	/**
	 * Types.supers(null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testSupers1(){
		final List<Class<?>> supers = supers(null);
		failure(supers);
	}
	/**
	 * Types.supers(D)
	 */
	@Test
	public void testSupers2(){
		final List<Class<?>> supers = supers(D.class);
		assertEquals(getPosition(),7,supers.size());
	}
	/**
	 * Types.supers(AA)
	 */
	@Test
	public void testSupers3(){
		final List<Class<?>> supers = supers(AA.class);
		assertEquals(getPosition(),4,supers.size());
	}
	/**
	 * Types.supers(E1)
	 */
	@Test
	public void testSupers4(){
		final List<Class<?>> supers = supers(E1.class);
		assertEquals(getPosition(),0,supers.size());
	}
	/**
	 * Types.supers(CA)
	 */
	@Test
	public void testSupers5(){
		final List<Class<?>> supers = supers(CA.class);
		assertEquals(getPosition(),2,supers.size());
	}
}
