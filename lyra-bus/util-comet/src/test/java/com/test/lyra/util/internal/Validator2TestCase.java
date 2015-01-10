package com.test.lyra.util.internal;

import static com.lyra.util.internal.Validator.numeric;
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
public class Validator2TestCase extends AbstractTestCase implements InternalConstant{
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public Validator2TestCase(){
		super(TestClasses.VALILDATOR);
	}
	// ~ Methods =============================================
	/**
	 * Validator.numeric: false
	 */
	@Test
	public void testNumeric1() {
		setMethod(M_NUMERIC);
		assertFalse(getPosition(), numeric(null));
	}
	
	/**
	 * Validator.numeric: false
	 */
	@Test
	public void testNumeric2() {
		setMethod(M_NUMERIC);
		assertFalse(getPosition(), numeric(""));
	}
	
	/**
	 * Validator.numeric: false
	 */
	@Test
	public void testNumeric3() {
		setMethod(M_NUMERIC);
		assertFalse(getPosition(), numeric("Hello"));
	}
	/**
	 * Validator.numeric: true
	 */
	@Test
	public void testNumeric4() {
		setMethod(M_NUMERIC);
		assertTrue(getPosition(), numeric("3.14"));
	}
	/**
	 * Validator.numeric: true
	 */
	@Test
	public void testNumeric5() {
		setMethod(M_NUMERIC);
		assertTrue(getPosition(), numeric("+1359"));
	}
	/**
	 * Validator.numeric: true
	 */
	@Test
	public void testNumeric6() {
		setMethod(M_NUMERIC);
		assertTrue(getPosition(), numeric("-1000"));
	}
	/**
	 * Validator.numeric: true
	 */
	@Test
	public void testNumeric7() {
		setMethod(M_NUMERIC);
		assertTrue(getPosition(), numeric("-13.23"));
	}
	
	/**
	 * Validator.numeric: true
	 */
	@Test
	public void testNumeric8() {
		setMethod(M_NUMERIC);
		assertTrue(getPosition(), numeric("3"));
	}
	// ~ Private Methods =====================================
}
