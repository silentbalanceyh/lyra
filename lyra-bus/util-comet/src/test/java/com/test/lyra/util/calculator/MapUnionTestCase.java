package com.test.lyra.util.calculator;

import static com.lyra.util.calculator.MapCalculator.union;
import static com.lyra.util.instance.MapInstance.hashMap;
import static org.junit.Assert.assertEquals;

import java.util.Map;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

/**
 * 测试方法：com.lyra.util.calculator.MapCalculator.union(Map<K,V>,Map<K,V>)
 *
 * @author Lang
 * @see
 */
public class MapUnionTestCase extends AbstractMapTestCase implements
		CalculatorConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public MapUnionTestCase() {
		super(TestClasses.MAP_CALCULATOR);
		setMethod(M_MAP_UNION);
	}

	// ~ Methods =============================================
	/**
	 * MapCalculator.union(null,Map<K,V>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testUnion1() {
		final Map<String, Integer> map1 = union(null, getMap1());
		failure(map1);
	}

	/**
	 * MapCalculator.union(Map<K,V>,null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testUnion2() {
		final Map<String, Integer> map2 = union(getMap2(), null);
		failure(map2);
	}

	/**
	 * MapCalculator.union(Map<K,V>,Map<K,V>)
	 */
	@Test
	public void testUnion3() {
		final Map<String, Integer> map3 = union(getMap1(), getMap2());
		assertEquals(getPosition(), 8, map3.size());
	}

	/**
	 * MapCalculator.union(Map<K,V>,Map<K,V>)
	 */
	@Test
	public void testUnion4() {
		final Map<String, Integer> map4 = union(getMap1(), getMap3());
		assertEquals(getPosition(), 10, map4.size());
	}

	/**
	 * MapCalculator.union(Map<K,V>,Map<K,V>)
	 */
	@Test
	public void testUnion5() {
		final Map<String, Integer> map5 = getMap1();
		final Map<String, Integer> emptyMap = hashMap(true);
		final Map<String, Integer> expected = hashMap(true, map5);
		final Map<String, Integer> actual = union(map5, emptyMap);
		assertEquals(getPosition(), expected, actual);
	}
	// ~ Private Methods =====================================
}
