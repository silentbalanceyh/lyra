package com.test.lyra.util.instance;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.test.base.AbstractTestCase;

/**
 * 测试com.lyra.util.instance.ArrayInstance方法
 * 
 * @author Lang
 * @see
 */
public class ArrayTestCase extends AbstractTestCase implements InstanceConstant {
	// ~ Constructors ========================================
	/**
     * 
     */
	public ArrayTestCase() {
		super(TestClasses.ARRRY);
		// final List<Integer> list = arrayList(null); compile error
	}

	// ~ Methods =============================================

	/**
	 * ArrayInstance.arrayList(wrong_size)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testArrayList1() {
		setMethod(M_ARRAYLIST2);
		final List<String> list = arrayList(-1);
		failure(list);
	}
	/**
	 * ArrayInstance.arrayList(right_size)
	 */
	@Test
	public void testArrayList2() {
		setMethod(M_ARRAYLIST2);
		final List<String> list = arrayList(12);
		assertNotNull(getPosition(),list);
	}
	// ~ Private Methods =====================================
}
