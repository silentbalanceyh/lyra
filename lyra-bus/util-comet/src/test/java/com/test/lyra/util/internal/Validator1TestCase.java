package com.test.lyra.util.internal;

import static com.lyra.util.internal.Validator.empty;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.internal.Validator.zero;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.test.base.AbstractTestCase;

/**
 * 测试类：com.lyra.util.internal.Validator
 * 
 * @author Lang
 * @see
 */
public class Validator1TestCase extends AbstractTestCase implements InternalConstant{
	// ~ Constructors ========================================
	/**
	 * Set class name. *
	 */
	public Validator1TestCase() {
		super(TestClasses.VALILDATOR);
	}

	// ~ Methods =============================================
	/**
	 * Validator.nullable: true
	 */
	@Test
	public void testNullable1() {
		setMethod(M_NULLABLE);
		assertTrue(getPosition(), nullable(null));
	}

	/**
	 * Validator.nullable: false
	 */
	@Test
	public void testNullable2() {
		setMethod(M_NULLABLE);
		assertFalse(getPosition(), nullable("False"));
	}

	/**
	 * Validator.zero: true
	 */
	@Test
	public void testZero1() {
		setMethod(M_ZREO);
		assertTrue(getPosition(), zero(new Integer[] {}));
	}

	/**
	 * Validator.zero: false
	 */
	@Test
	public void testZero2() {
		setMethod(M_ZREO);
		assertFalse(getPosition(), zero(new Integer[] { 1, 2, 3 }));
	}
	/**
	 * Validator.zero: false
	 */
	@Test
	public void testZero3() {
		setMethod(M_ZREO);
		assertFalse(getPosition(), zero(1,null,3));
	}

	/**
	 * Validator.empty: true
	 */
	@Test
	public void testEmpty1() {
		setMethod(M_EMPTY);
		assertTrue(getPosition(), empty(null));
	}

	/**
	 * Validator.empty: true
	 */
	@Test
	public void testEmpty2() {
		setMethod(M_EMPTY);
		assertTrue(getPosition(), empty(""));
	}

	/**
	 * Validator.empty: true
	 */
	@Test
	public void testEmpty3() {
		setMethod(M_EMPTY);
		assertTrue(getPosition(), empty("    "));
	}

	/**
	 * Validator.empty: false
	 */
	@Test
	public void testEmpty4() {
		setMethod(M_EMPTY);
		assertFalse(getPosition(), empty("Not Empty"));
	}
}
