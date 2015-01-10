package com.test.lyra.util.calculator;

import static com.lyra.util.calculator.SetCalculator.union;
import static com.lyra.util.instance.SetInstance.hashSet;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

/**
 * 单测方法：com.lyra.util.calculator.SetCalculator.union(Collection<T>,Collection<T>
 * )
 * 
 * @author Lang
 * @see
 */
public class SetUnionTestCase extends AbstractCollectionTestCase implements // NOPMD
		CalculatorConstant {
	// ~ Constructors ========================================
	/**
		 * 
		 */
	public SetUnionTestCase() {
		super(TestClasses.SET_CALCULATOR);
		setMethod(M_SET_UNION);
	}

	// ~ Methods =============================================
	/**
	 * SetCalculator.union(null,Collection<T>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testUnion1() {
		final Set<String> set1 = union(null, getList1());
		failure(set1);
	}

	/**
	 * SetCalculator.union(Collection<T>,null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testUnion2() {
		final Set<String> set2 = union(getList2(), null);
		failure(set2);
	}

	/**
	 * SetCalculator.union(Collection<T>,Collection<T>) -> 8 elements
	 */
	@Test
	public void testUnion3() {
		final Set<String> set3 = union(getList1(), getList2());
		assertEquals(getPosition(), 8, set3.size());
	}

	/**
	 * SetCalculator.union(Collection<T>,Collection<T>) -> 11 elements
	 */
	@Test
	public void testUnion4() {
		final Set<String> set4 = union(getList1(), getList3());
		assertEquals(getPosition(), 11, set4.size());
	}

	/**
	 * SetCalculator.union(Collection<T>,Collection<T>) -> 8 elements
	 */
	@Test
	public void testUnion5() {
		final Set<String> set5 = union(getSet1(), getSet2());
		assertEquals(getPosition(), 8, set5.size());
	}

	/**
	 * SetCalculator.union(Collection<T>,Collection<T>) -> 10 elements
	 */
	@Test
	public void testUnion6() {
		final Set<String> set6 = union(getSet1(), getSet3());
		assertEquals(getPosition(), 10, set6.size());
	}

	/**
	 * SetCalculator.union(Collection<T>,Collection<T>) -> 8 elements
	 */
	@Test
	public void testUnion7() {
		final Set<String> set7 = union(getSet1(), getList2());
		assertEquals(getPosition(), 8, set7.size());
	}

	/**
	 * SetCalculator.union(Collection<T>,Collection<T>) -> 10 elements
	 */
	@Test
	public void testUnion8() {
		final Set<String> set8 = union(getList1(), getSet3());
		assertEquals(getPosition(), 10, set8.size());
	}

	/**
	 * 空集测试 SetCalculator.union(Collection<T>,Collection<T>) -> Empty
	 */
	@Test
	public void testUnion9() {
		final Set<String> retSet = hashSet();
		final Set<String> emptySet = hashSet();
		retSet.addAll(union(getList1(), emptySet));
		assertEquals(getPosition(), 5, retSet.size());
	}
}
