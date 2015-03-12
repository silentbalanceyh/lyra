package com.test.lyra.util.instance;

/**
 * 存放所有com.lyra.util.instance包下的Global Method
 * 
 * @author Lang
 * @see
 */
interface InstanceConstant {
	/**
	 * ArrayInstance -> arrayList(Set)
	 */
	String M_ARRAYLIST1 = "arrayList(Set)";
	/**
	 * ArrayInstance -> arrayList(int)
	 */
	String M_ARRAYLIST2 = "arrayList(int)";
	/**
	 * BufferInstance -> builder(int)
	 */
	String M_BUILDER1 = "builder(int)";
	/**
	 * BufferInstance -> builder(String)
	 */
	String M_BUILDER2 = "builder(String)";
	/**
	 * BufferInstance -> builder()
	 */
	String M_BUILDER3 = "builder()";
	/**
	 * BufferInstance -> buffer(int)
	 */
	String M_BUFFER1 = "buffer(int)";
	/**
	 * BufferInstance -> buffer(String)
	 */
	String M_BUFFER2 = "buffer(String)";
	/**
	 * BufferInstance -> buffer()
	 */
	String M_BUFFER3 = "buffer()";
	/**
	 * NullInstance -> nullObj()
	 */
	String M_NULLOBJ = "nullObj()";
	/**
	 * SetInstance -> hashSet(int)
	 */
	String M_HASHSET1 = "hashSet(int)";
	/**
	 * SetInstance -> hashSet(Collection<T>);
	 */
	String M_HASHSET2 = "hashSet(Collection<T>)";
	/**
	 * SetInstance -> hashSet(T[]);
	 */
	String M_HASHSET3 = "hashSet(T[])";
	/**
	 * SortedInstance -> treeSet(Collection<T>)
	 */
	String M_TREESET1 = "treeSet(Collection<T>)";
	/**
	 * SortedInstance -> treeSet(Comparator<T>)
	 */
	String M_TREESET2 = "treeSet(Comparator<T>)";
	/**
	 * SortedInstance -> linkedHashSet(Collection<T>)
	 */
	String M_LHASHSET1 = "linkedHashSet(Collection<T>)";
	/**
	 * SortedInstance -> linkedHashSet()
	 */
	String M_LHASHSET2 = "linkedHashSet()";
	/**
	 * MapInstance -> hashMap(boolean)
	 */
	String M_HASHMAP1 = "hashMap(boolean)";
	/**
	 * MapInstance -> hashMap(boolean, Map<K,V>)
	 */
	String M_HASHMAP2 = "hashMap(boolean, Map<K,V>)";
	/**
	 * MapInstance -> treeMap(Map<K,V>)
	 */
	String M_TREEMAP1 = "treeMap(Map<K,V>)";
	/**
	 * MapInstance -> treeMap()
	 */
	String M_TREEMAP2 = "treeMap()";
	/**
	 * MapInstance -> linkedHashMap(Map<K,V>)
	 */
	String M_LHASHMAP1 = "linkedHashMap(Map<K,V>)";
	/**
	 * MapInstance -> linkedHashMap()
	 */
	String M_LHASHMAP2 = "linkedHashMap()";
	/**
	 * 被测试类清单
	 * 
	 * @author Lang
	 * @see
	 */
	interface TestClasses { // NOPMD
		/** **/
		String MAP = "com.lyra.util.instance.MapInstance";
		/** **/
		String NULL = "com.lyra.util.instance.NullInstance";
		/** **/
		String SET = "com.lyra.util.instance.SetInstance";
		/** **/
		String ARRRY = "com.lyra.util.instance.ArrayInstance";
		/** **/
		String SORTEDSET = "com.lyra.util.instance.SortedSetInstance";
		/** **/
		String BUFFER = "com.lyra.util.instance.BufferInstance";
	}

	/**
	 * 设置测试方法名
	 * 
	 * @param method
	 */
	void setMethod(String method);
}
