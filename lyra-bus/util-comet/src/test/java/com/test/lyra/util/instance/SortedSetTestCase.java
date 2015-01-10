package com.test.lyra.util.instance;

import static com.lyra.util.instance.SortedSetInstance.linkedHashSet;
import static com.lyra.util.instance.SortedSetInstance.treeSet;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.test.base.AbstractTestCase;
import com.test.lyra.util.assist.InstanceModel;
import com.test.lyra.util.assist.ModelComparator;

/**
 * 测试类：com.lyra.util.instance.SortedInstance
 * 
 * @author Lang
 * @see
 */
public class SortedSetTestCase extends AbstractTestCase implements
		InstanceConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public SortedSetTestCase() {
		super(TestClasses.SORTEDSET);
		// final Set<String> treeSet = treeSet(null); Compile Error
	}

	// ~ Methods =============================================

	/**
	 * SortedInstance.linkedHashSet(Collection<T>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testLinkedHashSet1() {
		setMethod(M_LHASHSET1);
		final Set<String> linkedSet = linkedHashSet(null);
		failure(linkedSet);
	}
	/**
	 * SortedSetInstance.linkedHashSet()
	 */
	@Test
	public void testLinkedHashSet2(){
		setMethod(M_LHASHSET2);
		final Set<String> set = linkedHashSet();
		assertNotNull(getPosition(),set);
	}
	/**
	 * SortedSetInstance.linkedHashSet(Collection<T>)
	 */
	@Test
	public void testLinkedHashSet3(){
		setMethod(M_LHASHSET1);
		final Set<String> set = treeSet();
		final Set<String> retSet = linkedHashSet(set);
		assertNotNull(getPosition(),retSet);
	}
	/**
	 * SortedSetInstance.treeSet(Comparator<T>)
	 */
	@Test
	public void testTreeSet1(){
		setMethod(M_TREESET2);
		final Set<InstanceModel> set = treeSet(new ModelComparator());
		assertNotNull(getPosition(),set);
	}
}
