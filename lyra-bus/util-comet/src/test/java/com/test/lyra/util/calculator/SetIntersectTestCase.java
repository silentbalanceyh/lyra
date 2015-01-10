package com.test.lyra.util.calculator;

import static com.lyra.util.calculator.SetCalculator.intersect;
import static com.lyra.util.instance.SetInstance.hashSet;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

/**
 * 单测方法：com.lyra.util.calculator.SetCalculator.intersect(Collection<T>,
 * Collection<T>);
 *
 * @author Lang
 * @see
 */
public class SetIntersectTestCase extends AbstractCollectionTestCase implements
		CalculatorConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public SetIntersectTestCase() {
		super(TestClasses.SET_CALCULATOR);
		setMethod(M_SET_INTERSECT);
	}

	// ~ Methods =============================================
	/**
	 * SetCalculator.intersect(null,Collection<T>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testIntersect1() {
		final Set<String> set1 = intersect(null, getList1());
		failure(set1);
	}

	/**
	 * SetCalculator.intersect(Collection<T>,null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testIntersect2() {
		final Set<String> set2 = intersect(getList2(), null);
		failure(set2);
	}

	/**
	 * SetCalculator.intersect(Collection<T>,Collection<T>) -> Not Empty
	 */
	@Test
	public void testIntersect3() {
		final Set<String> set3 = intersect(getList1(), getList2());
		assertEquals(getPosition(), 2, set3.size());
	}

	/**
	 * SetCalculator.intersect(Collection<T>,Collection<T>) -> Empty
	 */
	@Test
	public void testIntersect4() {
		final Set<String> set4 = intersect(getList1(), getList3());
		assertEquals(getPosition(), 0, set4.size());
	}

	/**
	 * SetCalculator.intersect(Collection<T>,Collection<T>) -> Not Empty
	 */
	@Test
	public void testIntersect5() {
		final Set<String> set5 = intersect(getSet1(), getSet2());
		assertEquals(getPosition(), 2, set5.size());
	}

	/**
	 * SetCalculator.intersect(Collection<T>,Collection<T>) -> Empty
	 */
	@Test
	public void testIntersect6() {
		final Set<String> set6 = intersect(getSet1(), getSet3());
		assertEquals(getPosition(), 0, set6.size());
	}

	/**
	 * SetCalculator.intersect(Collection<T>,Collection<T>) -> Not Empty
	 */
	@Test
	public void testIntersect7() {
		final Set<String> set7 = intersect(getSet1(), getList2());
		assertEquals(getPosition(), 2, set7.size());
	}

	/**
	 * SetCalculator.intersect(Collection<T>,Collection<T>) -> Empty
	 */
	@Test
	public void testIntersect8() {
		final Set<String> set8 = intersect(getList1(), getSet3());
		assertEquals(getPosition(), 0, set8.size());
	}

	/**
	 * 空集测试 SetCalculator.intersect(Collection<T>,Collection<T>) -> Empty
	 */
	@Test
	public void testIntersect9() {
		final Set<String> retSet = hashSet();
		final Set<String> emptySet = hashSet();
		retSet.addAll(intersect(getList1(), emptySet));
		retSet.addAll(intersect(getList2(), emptySet));
		retSet.addAll(intersect(getList3(), emptySet));
		retSet.addAll(intersect(getSet1(), emptySet));
		retSet.addAll(intersect(getSet2(), emptySet));
		retSet.addAll(intersect(getSet3(), emptySet));
		assertEquals(getPosition(), 0, retSet.size());
	}
	// ~ Private Methods =====================================
}
