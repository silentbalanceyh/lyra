package com.test.lyra.util.internal;

import static com.lyra.util.internal.Comparer.equal;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;

/**
 * 测试类：com.lyra.util.internal.Comparer
 * 
 * @author Lang
 * @see
 */
public class Comparer1TestCase extends AbstractTestCase implements InternalConstant{
	// ~ Constructors ========================================
	/**
	 * 构造函数
	 */
	public Comparer1TestCase() {
		super(TestClasses.COMPARER);
	}

	// ~ Methods =============================================

	/**
	 * Comparer.equal(T,T) 右值和null值匹配
	 */
	@Test
	public void testEqual1() {
		setMethod(M_EQUAL1);
		assertFalse(getPosition(), equal("Hello", null));
	}

	/**
	 * Comparer.equal(T,T) 左值null值匹配
	 */
	@Test
	public void testEqual2() {
		setMethod(M_EQUAL1);
		assertFalse(getPosition(), equal(null, "Hello"));
	}

	/**
	 * Comparer.equal(T,T) 同为null的比较
	 */
	@Test
	public void testEqual3() {
		setMethod(M_EQUAL1);
		assertTrue(getPosition(), equal(null, null));
	}

	/**
	 * Comparer.equal(T,T) 都不为null的比较
	 */
	@Test
	public void testEqual4() {
		setMethod(M_EQUAL1);
		final Integer int1 = Integer.valueOf(34);
		final Integer int2 = Integer.valueOf(36);
		assertFalse(getPosition(), equal(int1, int2));
	}

	/**
	 * Comparer.equal(T,T,Class<?>)
	 */
	@Test
	public void testEqual5() {
		setMethod(M_EQUAL2);
		assertTrue(getPosition(), equal(null, null, Integer.class));
	}

	/**
	 * Comparer.equal(T,T,Class<?>)
	 */
	@Test
	public void testEqual6() {
		setMethod(M_EQUAL2);
		assertFalse(getPosition(), equal(45, "Hello", String.class));
	}

	/**
	 * Comparer.equal(T,T,Class<?>)
	 */
	@Test
	public void testEqual7() {
		setMethod(M_EQUAL2);
		assertTrue(getPosition(), equal(45, 45, Integer.class));
	}

	/**
	 * Comparer.equal(T,T,Class<?>)
	 */
	@Test
	public void testEuqal8() {
		setMethod(M_EQUAL2);
		assertFalse(getPosition(), equal(45, 45, String.class));
	}

	/**
	 * Comparer.equal(T,T,Class<?>)
	 */
	@Test
	public void testEqual9() {
		setMethod(M_EQUAL2);
		assertFalse(getPosition(),
				equal(Long.valueOf(45L), Long.valueOf(42L), Long.class));
	}
	// ~ Private Methods =====================================
}
