package com.test.lyra.util.calculator;

import static com.lyra.util.calculator.DescartCalculator.descart;
import static com.lyra.util.instance.ArrayInstance.arrayList;
import static org.junit.Assert.assertEquals;

import java.util.List;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.test.base.AbstractTestCase;

/**
 * Descart Calculator专用基类
 *
 * @author Lang
 * @see
 */
public class DescartTestCase extends AbstractTestCase implements
		CalculatorConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public DescartTestCase() {
		super(TestClasses.DES_CALCULATOR);
		setMethod(M_LIST_DESCART);
	}

	// ~ Methods =============================================
	/**
	 * DescartCalculator.descart(null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testDescart1() {
		final List<List<Integer>> descart1 = descart(null);
		failure(descart1);
	}

	/**
	 * DescartCalculator.descart(dimList(2)): dim size = 2
	 */
	@Test
	public void testDescart2() {
		final List<List<Integer>> dimList = getDimByLayer(2);
		int result = 1;
		for (final List<Integer> list : dimList) {
			result = result * list.size();
		}
		final List<List<Integer>> actual = descart(dimList);
		assertEquals(getPosition(), result, actual.size());
	}
	
	/**
	 * DescartCalculator.descart(dimList(3)): dim size = 3
	 */
	@Test
	public void testDescart3() {
		final List<List<Integer>> dimList = getDimByLayer(3);
		int result = 1;
		for (final List<Integer> list : dimList) {
			result = result * list.size();
		}
		final List<List<Integer>> actual = descart(dimList);
		assertEquals(getPosition(), result, actual.size());
	}
	/**
	 * DescartCalculator.descart(dimList(4)): dim size = 4
	 */
	@Test
	public void testDescart4() {
		final List<List<Integer>> dimList = getDimByLayer(4);
		int result = 1;
		for (final List<Integer> list : dimList) {
			result = result * list.size();
		}
		final List<List<Integer>> actual = descart(dimList);
		assertEquals(getPosition(), result, actual.size());
	}
	// ~ Private Methods =====================================

	private List<List<Integer>> getDimByLayer(final int layer) {
		final List<List<Integer>> retList = arrayList();
		for (int i = 0; i < layer; i++) {
			final int elements = randomInt(7);
			final List<Integer> layer0List = arrayList();
			for (int j = 0; j < elements; j++) {
				final int element = randomInt(100);
				if(!layer0List.contains(element)){
					layer0List.add(element);
				}
			}
			retList.add(layer0List);
		}
		return retList;
	}
}
