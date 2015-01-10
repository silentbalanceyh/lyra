package com.test.lyra.util.calculator;

import static com.lyra.util.instance.MapInstance.hashMap;

import java.util.Map;

import com.test.base.AbstractTestCase;

/**
 * Map Calculator专用基类
 *
 * @author Lang
 * @see
 */
public abstract class AbstractMapTestCase extends AbstractTestCase {
	// ~ Constructors ========================================
	/**
	 * 
	 * @param className
	 */
	public AbstractMapTestCase(final String className) {
		super(className);
	}

	// ~ Methods =============================================
	/**
	 * 
	 * @return
	 */
	protected Map<String, Integer> getUnMap1() {
		final Map<String, Integer> retMap = hashMap(true);
		retMap.put("ITEM1", 1);
		retMap.put("ITEM2", 2);
		retMap.put("ITEM3", 3);
		return retMap;
	}

	/**
	 * 
	 * @return
	 */
	protected Map<String, Integer> getUnMap2() {
		final Map<String, Integer> retMap = hashMap(true);
		retMap.put("ITEM6", 6);
		retMap.put("ITEM7", 7);
		retMap.put("ITEM8", 8);
		return retMap;
	}

	/**
	 * 
	 * @return
	 */
	protected Map<String, Integer> getMap1() {
		final Map<String, Integer> retMap = hashMap(true);
		retMap.put("ITEM1", 1);
		retMap.put("ITEM2", 2);
		retMap.put("ITEM3", 3);
		retMap.put("ITEM4", 4);
		retMap.put("ITEM5", 5);
		return retMap;
	}

	/**
	 * 
	 * @return
	 */
	protected Map<String, Integer> getMap2() {
		final Map<String, Integer> retMap = hashMap(true);
		retMap.put("ITEM4", 4);
		retMap.put("ITEM5", 5);
		retMap.put("ITEM6", 6);
		retMap.put("ITEM7", 7);
		retMap.put("ITEM8", 8);
		return retMap;
	}

	/**
	 * 
	 * @return
	 */
	protected Map<String, Integer> getMap3() {
		final Map<String, Integer> retMap = hashMap(true);
		retMap.put("ITEM9", 9);
		retMap.put("ITEM10", 10);
		retMap.put("ITEM11", 11);
		retMap.put("ITEM12", 12);
		retMap.put("ITEM13", 13);
		return retMap;
	}
}
