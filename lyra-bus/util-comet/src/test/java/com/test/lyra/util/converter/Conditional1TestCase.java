package com.test.lyra.util.converter;

import static com.lyra.util.converter.ConditionalConverter.ifToBoolean;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;

/**
 * 单测方法：com.lyra.util.converter.ConditionalConverter.ifToBoolean(String,String
 * ...);
 * 
 * @author Lang
 * @see
 */
public class Conditional1TestCase extends AbstractTestCase implements
		ConverterConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public Conditional1TestCase() {
		super(TestClasses.CONDITIONAL);
		setMethod(M_IFTOBOOLEAN1);
		// 第二个实参为null会编译错
		// final boolean retValue = ifToBoolean(null,null); Compiler Error
		// final boolean retValue = ifToBoolean("ITEM2",null); Compiler Error
		// Message
	}

	// ~ Methods =============================================
	/**
	 * ConditionalConverter.ifToBoolean(null,String[])
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testIfToBoolean1() {
		final boolean retValue = ifToBoolean(null, new String[] { "A", "B" });
		failure(retValue);
	}

	/**
	 * ConditionalConverter.ifToBoolean("",String[])
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testIfToBoolean2() {
		final boolean retValue = ifToBoolean("", new String[] { "A", "B" });
		failure(retValue);
	}

	/**
	 * ConditionalConverter.ifToBoolean("    ",String[])
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testIfToBoolean3() {
		final boolean retValue = ifToBoolean("   ", new String[] { "A", "B" });
		failure(retValue);
	}

	/**
	 * ConditionalConverter.ifToBoolean("KEY",new String[]{"KEY","KEY1"})
	 */
	@Test
	public void testIfToBoolean6() {
		final boolean retValue = ifToBoolean("KEY1", new String[] { "KEY2",
				"KEY1" });
		assertTrue(getPosition(), retValue);
	}

	/**
	 * ConditionalConverter.ifToBoolean("KEY","KEY","KEY1")
	 */
	@Test
	public void testIfToBoolean7() {
		final boolean retValue = ifToBoolean("KEY5", "KEY2", "KEY5");
		assertTrue(getPosition(), retValue);
	}

	/**
	 * ConditionalConverter.ifToBoolean("KEY","KEY","KEY1")
	 */
	@Test
	public void testIfToBoolean8() {
		final boolean retValue = ifToBoolean("KEY3", "KEY4", "KEY5");
		assertFalse(getPosition(), retValue);
	}
}
