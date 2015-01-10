package com.test.lyra.util.internal;

import static com.lyra.util.internal.Comparer.hash;
import static com.lyra.util.internal.Comparer.notEqual;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lyra.res.Symbol;
import com.test.base.AbstractTestCase;

/**
 * 测试类：com.lyra.util.internal.Comparer
 * 
 * @author Lang
 * @see
 */
public class Comparer2TestCase extends AbstractTestCase implements InternalConstant{
	// ~ Constructors ========================================

	/**
     *
     */
	public Comparer2TestCase() {
		super(TestClasses.COMPARER);
	}

	// ~ Methods =============================================

	/**
	 * Comparer.notEqual(T,T)
	 */
	@Test
	public void testNotEqual1() {
		setMethod(M_NOTEQUAL);
		assertFalse(getPosition(),
				notEqual(Long.valueOf(54L), Long.valueOf(54L)));
	}

	/**
	 * Comparer.notEqual(T,T)
	 */
	@Test
	public void testNotEqual2() {
		setMethod(M_NOTEQUAL);
		assertTrue(getPosition(), notEqual("Hello", Long.valueOf(54L)));
	}

	/**
	 * Comparer.notEqual(T,T)
	 */
	@Test
	public void testNotEqual3() {
		setMethod(M_NOTEQUAL);
		assertTrue(getPosition(), notEqual(null, "Hello World"));
	}

	/**
	 * Comparer.notEqual(T,T)
	 */
	@Test
	public void testNotEqual4() {
		setMethod(M_NOTEQUAL);
		assertFalse(getPosition(), notEqual(null, null));
	}

	/**
	 * Comparer.hash(int,T)
	 */
	@Test
	public void testHash1() {
		setMethod(M_HASH);
		final int seed = randomInt(100);
		final int expected = Symbol.I_HASH_CODE * seed + Symbol.I_ZERO;
		final int actual = hash(seed, null);
		assertEquals(getPosition(), expected, actual);
	}

	/**
	 * Comparer.hash(int,T)
	 */
	@Test
	public void testHash2() {
		setMethod(M_HASH);
		final int seed = randomInt(100);
		final Integer intField = Integer.valueOf(randomInt(1000));
		final int expected = Symbol.I_HASH_CODE * seed + intField.hashCode();// NOPMD
		final int actual = hash(seed, intField);
		assertEquals(getPosition(), expected, actual);
	}
}
