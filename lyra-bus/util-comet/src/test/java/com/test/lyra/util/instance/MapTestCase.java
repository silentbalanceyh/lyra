package com.test.lyra.util.instance;

import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.MapInstance.linkedHashMap;
import static com.lyra.util.instance.MapInstance.treeMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;

/**
 * 测试类：com.lyra.util.instance.MapInstance
 * 
 * @author Lang
 * @see
 */
public class MapTestCase extends AbstractTestCase implements InstanceConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public MapTestCase() {
		super(TestClasses.MAP);
	}

	// ~ Methods =============================================
	/**
	 * MapInstance.hashMap(boolean)
	 */
	@Test
	public void testHashMap1() {
		setMethod(M_HASHMAP1);
		final Map<String, Integer> map = hashMap(false);
		final boolean ret = map instanceof HashMap;
		assertTrue(getPosition(), ret);
	}

	/**
	 * MapInstance.hashMap(boolean,Map<K,V>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testHashMap3() {
		setMethod(M_HASHMAP2);
		final Map<String, Integer> map = hashMap(true, null);
		failure(map);
	}

	/**
	 * MapInstance.treeMap(Map<K,V>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testTreeMap1() {
		setMethod(M_TREEMAP1);
		final Map<String, Integer> map = treeMap(null);
		failure(map);
	}
	/**
	 * MapInstance.treeMap()
	 */
	@Test
	public void testTreeMap2(){
		setMethod(M_TREEMAP2);
		final Map<String, Integer> map = treeMap();
		assertNotNull(getPosition(),map);
	}
	/**
	 * MapInstance.treeMap(Map<K,V>)
	 */
	@Test
	public void testTreeMap3(){
		setMethod(M_TREEMAP1);
		final Map<String,Integer> map = hashMap(true);
		map.put("TREEITEM1", 1);
		map.put("TREEITEM2", 2);
		final Map<String,Integer> retMap = treeMap(map);
		assertEquals(getPosition(),2,retMap.size());
	}
	/**
	 * MapInstance.linkedHashMap(Map<K,V>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testLinkedMap1() {
		setMethod(M_LHASHMAP1);
		final Map<String, Integer> map = linkedHashMap(null); // NOPMD
		failure(map);
	}
	/**
	 * MapInstance.linkedHashMap()
	 */
	@Test
	public void testLinkedMap2(){
		setMethod(M_LHASHMAP2);
		final Map<String, Integer> map = linkedHashMap();
		assertNotNull(getPosition(),map);
	}
	/**
	 * MapInstance.linkedHashMap(Map<K,V>)
	 */
	@Test
	public void testLinkedMap3(){
		setMethod(M_LHASHMAP1);
		final Map<String,Integer> map = hashMap(true);
		map.put("ITEM1", 1);
		map.put("ITEM2", 2);
		final Map<String,Integer> retMap = linkedHashMap(map);
		assertEquals(getPosition(),2,retMap.size());
	}
}
