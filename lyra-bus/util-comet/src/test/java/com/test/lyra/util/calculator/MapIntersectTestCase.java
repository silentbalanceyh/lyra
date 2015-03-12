package com.test.lyra.util.calculator;

import static com.lyra.util.calculator.MapCalculator.intersect;
import static com.lyra.util.instance.MapInstance.hashMap;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

/**
 * 单测方法：com.lyra.util.calculator.MapCalculator.intersect(Map<K,V>,Map<K,V>)
 *
 * @author Lang
 * @see
 */
public class MapIntersectTestCase extends AbstractMapTestCase implements
		CalculatorConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public MapIntersectTestCase() {
		super(TestClasses.MAP_CALCULATOR);
		setMethod(M_MAP_INTERSECT);
	}

	// ~ Methods =============================================
	/**
	 * MapCalculator.intersect(null,Map<K,V>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testIntersect1() {
		final Map<String, Integer> map1 = intersect(null, getMap1());
		failure(map1);
	}

	/**
	 * MapCalculator.intersect(Map<K,V>,null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testIntersect2() {
		final Map<String, Integer> map2 = intersect(getMap2(), null);
		failure(map2);
	}

	/**
	 * MapCalculator.intersect(Map<K,V>,Map<K,V>) - Not Empty
	 */
	@Test
	public void testIntersect3() {
		final Map<String, Integer> map3 = intersect(getMap1(), getMap2());
		assertEquals(getPosition(), 2, map3.size());
	}

	/**
	 * MapCalculator.intersect(Map<K,V>,Map<K,V>) - Empty
	 */
	@Test
	public void testIntersect4() {
		final Map<String, Integer> map4 = intersect(getMap1(), getMap3());
		assertEquals(getPosition(), 0, map4.size());
	}

	/**
	 * MapCalculator.intersect(Map<K,V>,Map<K,V>) - Empty
	 */
	@Test
	public void testIntersect5() {
		final Map<String, Integer> retMap = hashMap(true);
		final Map<String, Integer> emptyMap = hashMap(true);
		retMap.putAll(intersect(getMap1(), emptyMap));
		retMap.putAll(intersect(getMap2(), emptyMap));
		retMap.putAll(intersect(getMap3(), emptyMap));
		assertEquals(getPosition(), 0, retMap.size());
	}
	// ~ Private Methods =====================================
}
