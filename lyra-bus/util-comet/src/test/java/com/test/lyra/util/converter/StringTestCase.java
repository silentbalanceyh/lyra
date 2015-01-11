package com.test.lyra.util.converter;

import static com.lyra.util.converter.StringConverter.lower;
import static com.lyra.util.converter.StringConverter.upper;
import static com.lyra.util.converter.StringConverter.uuid;
import static org.junit.Assert.assertEquals;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;

/**
 * 测试com.lyra.util.converter.StringConverter
 * 
 * @author Lang
 * @see
 */
public class StringTestCase extends AbstractTestCase implements
		ConverterConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public StringTestCase() {
		super(TestClasses.STRING);
	}

	// ~ Methods =============================================
	/**
	 * StringConverter.upper(null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testUpper1() {
		setMethod(M_UPPER);
		final String retStr = upper(null);
		failure(retStr);
	}

	/**
	 * StringConverter.upper("")
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testUpper2() {
		setMethod(M_UPPER);
		final String retStr = upper("");
		failure(retStr);
	}

	/**
	 * StringConverter.upper("    ");
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testUpper3() {
		setMethod(M_UPPER);
		final String retStr = upper("     ");
		failure(retStr);
	}

	/**
	 * StringConverter.lower(null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testLower1() {
		setMethod(M_LOWER);
		final String retStr = lower(null);
		failure(retStr);
	}

	/**
	 * StringConverter.lower("")
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testLower2() {
		setMethod(M_LOWER);
		final String retStr = lower("");
		failure(retStr);
	}

	/**
	 * StringConverter.lower("    ")
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testLower3() {
		setMethod(M_LOWER);
		final String retStr = lower("    ");
		failure(retStr);
	}

	/**
	 * StringConverter.uuid(boolean)
	 */
	@Test
	public void testUuid() {
		setMethod(M_UUID);
		final String retStr1 = uuid(true);
		final String retStr2 = uuid(false);
		final int mixLength = retStr1.length() + retStr2.length();
		assertEquals(getPosition(), 72, mixLength);
	}
}
