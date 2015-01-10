package com.test.lyra.util.calculator;

import static com.lyra.util.calculator.SetCalculator.diff;
import static com.lyra.util.instance.SortedSetInstance.treeSet;
import static com.lyra.util.instance.SetInstance.hashSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

import java.util.Set;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.res.Symbol;

/**
 * 单测方法：com.lyra.util.calculator.SetCalculator.diff(Collection<T>,Collection<T>)
 * ;
 *
 * @author Lang
 * @see
 */
public class SetDiffTestCase extends AbstractCollectionTestCase implements
		CalculatorConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public SetDiffTestCase() {
		super(TestClasses.SET_CALCULATOR);
		setMethod(M_SET_DIFF);
	}

	// ~ Methods =============================================
	/**
	 * SetCalculator.diff(null,Collection<T>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testDiff1() {
		final Set<String> set1 = diff(null, getList1());
		failure(set1);
	}

	/**
	 * SetCalculator.diff(Collection<T>,null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testDiff2() {
		final Set<String> set2 = diff(getList2(), null);
		failure(set2);
	}

	/**
	 * SetCalculator.diff(Collection<T>,Collection<T>)
	 */
	@Test
	public void testDiff3() {
		final Set<String> set3 = diff(getSet1(), getSet2());
		assertEquals(getPosition(), 3, set3.size());
	}

	/**
	 * SetCalculator.diff(Collection<T>,Collection<T>): Set1 - Set2
	 */
	@Test
	public void testDiff4() {
		final Set<String> set4 = diff(getSet1(), getSet2());
		final Set<String> expected = treeSet(getUnSet1());
		final Set<String> actual = treeSet(set4);
		assertArrayEquals(getPosition(), expected.toArray(Symbol.T_STR_ARR),
				actual.toArray(Symbol.T_STR_ARR));
	}

	/**
	 * SetCalculator.diff(Collection<T>,Collection<T>): Set2 - Set1
	 */
	@Test
	public void testDiff5() {
		final Set<String> set5 = diff(getSet2(), getSet1());
		final Set<String> expected = treeSet(getUnSet2());
		final Set<String> actual = treeSet(set5);
		assertArrayEquals(getPosition(), expected.toArray(Symbol.T_STR_ARR),
				actual.toArray(Symbol.T_STR_ARR));
	}

	/**
	 * SetCalculator.diff(Collection<T>,Collection<T>): Set1 - Empty
	 */
	@Test
	public void testDiff6() {
		final Set<String> setTarget = hashSet();
		final Set<String> set5 = diff(getSet1(), setTarget);
		final Set<String> expected = treeSet(getSet1());
		final Set<String> actual = treeSet(set5);
		assertArrayEquals(getPosition(), expected.toArray(Symbol.T_STR_ARR),
				actual.toArray(Symbol.T_STR_ARR));
	}

	/**
	 * SetCalculator.diff(Collection<T>,Collection<T>): Set2 - Empty
	 */
	@Test
	public void testDiff7() {
		final Set<String> setTarget = hashSet();
		final Set<String> set6 = diff(getSet2(), setTarget);
		final Set<String> expected = treeSet(getSet2());
		final Set<String> actual = treeSet(set6);
		assertArrayEquals(getPosition(), expected.toArray(Symbol.T_STR_ARR),
				actual.toArray(Symbol.T_STR_ARR));
	}
	// ~ Private Methods =====================================
}
