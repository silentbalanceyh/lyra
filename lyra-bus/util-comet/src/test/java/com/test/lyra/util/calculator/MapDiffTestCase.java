package com.test.lyra.util.calculator;

import static com.lyra.util.calculator.MapCalculator.diff;
import static com.lyra.util.instance.MapInstance.hashMap;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

/**
 * 单测方法：com.lyra.util.calculator.MapCalculator.diff(Map<K,V>,Map<K,V>)
 *
 * @author Lang
 * @see
 */
public class MapDiffTestCase extends AbstractMapTestCase implements
		CalculatorConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public MapDiffTestCase() {
		super(TestClasses.MAP_CALCULATOR);
		setMethod(M_MAP_DIFF);
	}

	// ~ Methods =============================================

	/**
	 * MapCalculator.diff(null,Map<K,V>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testDiff1() {
		final Map<String, Integer> map1 = diff(null, getMap1());
		failure(map1);
	}

	/**
	 * MapCalculator.diff(Map<K,V>,null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testDiff2() {
		final Map<String, Integer> map2 = diff(getMap2(), null);
		failure(map2);
	}

	/**
	 * MapCalculator.diff(Map<K,V>,Map<K,V>)
	 */
	@Test
	public void testDiff3() {
		final Map<String, Integer> map3 = diff(getMap1(), getMap2());
		assertEquals(getPosition(), 3, map3.size());
	}
	/**
	 * MapCalculator.diff(Map<K,V>,Map<K,V>)
	 */
	@Test
	public void testDiff4(){
		final Map<String, Integer> map4 = diff(getMap1(), getMap2());
		assertEquals(getPosition(), map4, getUnMap1());
	}
	
	/**
	 * MapCalculator.diff(Map<K,V>,Map<K,V>)
	 */
	@Test
	public void testDiff5(){
		final Map<String, Integer> map5 = diff(getMap2(), getMap1());
		assertEquals(getPosition(), map5, getUnMap2());
	}
	/**
	 * MapCalculator.diff(Map<K,V>,Map<K,V>)
	 */
	@Test
	public void testDiff6(){
		final Map<String, Integer> map6 = getMap1();
		final Map<String, Integer> emptyMap = hashMap(true);
		final Map<String, Integer> actual = diff(map6,emptyMap);
		assertEquals(getPosition(), map6, actual);
	}
	// ~ Private Methods =====================================
}
