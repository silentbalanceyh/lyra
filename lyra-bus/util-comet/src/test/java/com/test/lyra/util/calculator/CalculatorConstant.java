package com.test.lyra.util.calculator;

/**
 * 存放所有com.lyra.util.calculator包下的Global Method
 *
 * @author Lang
 * @see
 */
interface CalculatorConstant {
	/**
	 * SetCalculator -> intersect(Collection<T>,Collection<T>)
	 */
	String M_SET_INTERSECT = "intersect(Collection<T>,Collection<T>)";
	/**
	 * SetCalculator -> union(Collection<T>,Collection<T>)
	 */
	String M_SET_UNION = "union(Collection<T>,Collection<T>)";
	/**
	 * SetCalculator -> diff(Collection<T>,Collection<T>)
	 */
	String M_SET_DIFF = "diff(Collection<T>,Collection<T>)";
	/**
	 * MapCalculator -> intersect(Map<K,V>,Map<K,V>)
	 */
	String M_MAP_INTERSECT = "intersect(Map<K,V>,Map<K,V>";
	/**
	 * MapCalculator -> union(Map<K,V>,Map<K,V>)
	 */
	String M_MAP_UNION = "union(Map<K,V>,Map<K,V>)";
	/**
	 * MapCalculator -> diff(Map<K,V>,Map<K,V>)
	 */
	String M_MAP_DIFF = "diff(Map<K,V>,Map<K,V>)";
	/**
	 * DescartCalculator -> descart(List<List<T>>)
	 */
	String M_LIST_DESCART = "descart(List<List<T>>)";
	/**
	 * IndexCalculator -> index(Set<T>,T)
	 */
	String M_INDEX1 = "index(Set<T>,T)";
	/**
	 * IndexCalculator -> index(T[],T)
	 */
	String M_INDEX2 = "index(T[],T)";
	/**
	 * IndexCalculator -> index(List<T>,T)
	 */
	String M_INDEX3 = "index(List<T>,T)";
	/**
	 * 被测试类清单
	 *
	 * @author Lang
	 * @see
	 */
	interface TestClasses { // NOPMD
		/**
		 * 
		 */
		String SET_CALCULATOR = "com.lyra.util.calculator.SetCalculator";
		/**
		 * 
		 */
		String MAP_CALCULATOR = "com.lyra.util.calculator.MapCalculator";
		/**
		 * 
		 */
		String DES_CALCULATOR = "com.lyra.util.calculator.DescartCalculator";
		/**
		 * 
		 */
		String INDEX_CALCULATOR = "com.lyra.util.calculator.IndexCalculator";
	}

	/**
	 * 设置测试方法名
	 * 
	 * @param method
	 */
	void setMethod(String method);
}
