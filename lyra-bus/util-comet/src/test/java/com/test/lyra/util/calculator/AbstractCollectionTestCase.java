package com.test.lyra.util.calculator;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.SetInstance.hashSet;

import java.util.List;
import java.util.Set;

import com.lyra.util.test.AbstractTestCase;

/**
 * Set Calculator专用基类
 *
 * @author Lang
 * @see
 */
public abstract class AbstractCollectionTestCase extends AbstractTestCase {
	// ~ Constructors ========================================
	/**
	 * 
	 * @param className
	 */
	public AbstractCollectionTestCase(final String className) {
		super(className);
	}

	// ~ Methods =============================================
	/**
	 * 
	 * @return
	 */
	protected Set<String> getUnSet1() {
		final Set<String> retSet = hashSet();
		retSet.add("ITEM1");
		retSet.add("ITEM2");
		retSet.add("ITEM3");
		return retSet;
	}

	/**
	 * 
	 * @return
	 */
	protected Set<String> getUnSet2() {
		final Set<String> retSet = hashSet();
		retSet.add("ITEM6");
		retSet.add("ITEM7");
		retSet.add("ITEM8");
		return retSet;
	}

	/**
	 * 
	 * @return
	 */
	protected Set<String> getSet1() {
		final Set<String> retSet = hashSet();
		retSet.add("ITEM1");
		retSet.add("ITEM2");
		retSet.add("ITEM3");
		retSet.add("ITEM4");
		retSet.add("ITEM5");
		return retSet;
	}

	/**
	 * 
	 * @return
	 */
	protected Set<String> getSet2() {
		final Set<String> retSet = hashSet();
		retSet.add("ITEM4");
		retSet.add("ITEM5");
		retSet.add("ITEM6");
		retSet.add("ITEM7");
		retSet.add("ITEM8");
		return retSet;
	}

	/**
	 * 
	 * @return
	 */
	protected Set<String> getSet3() {
		final Set<String> retSet = hashSet();
		retSet.add("ITEM9");
		retSet.add("ITEM10");
		retSet.add("ITEM11");
		retSet.add("ITEM12");
		retSet.add("ITEM13");
		return retSet;
	}

	/**
	 * 
	 * @return
	 */
	protected List<String> getList1() {
		final List<String> retSet = arrayList(getSet1());
		retSet.add("ITEM1");
		retSet.add("ITEM2");
		retSet.add("ITEM3");
		return retSet;
	}

	/**
	 * 
	 * @return
	 */
	protected List<String> getList2() {
		final List<String> retSet = arrayList(getSet2());
		retSet.add("ITEM5");
		retSet.add("ITEM6");
		retSet.add("ITEM7");
		return retSet;
	}

	/**
	 * 
	 * @return
	 */
	protected List<String> getList3() {
		final List<String> retSet = arrayList(getSet3());
		retSet.add("ITEM11");
		retSet.add("ITEM12");
		retSet.add("ITEM14");
		return retSet;
	}
}
