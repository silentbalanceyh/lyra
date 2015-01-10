package com.test.lyra.util.instance;

import static com.lyra.util.instance.SetInstance.hashSet;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.test.base.AbstractTestCase;

/**
 * 测试com.lyra.util.instance.SetInstance方法
 * 
 * @author Lang
 * @see
 */
public class SetTestCase extends AbstractTestCase implements InstanceConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public SetTestCase() {
		super(TestClasses.SET);
		// final Set<String> set = hashSet(null); Compile Error
	}

	// ~ Methods =============================================
	/**
	 * SetInstance.hashSet(int)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testHashSet1() {
		setMethod(M_HASHSET1);
		final Set<Integer> set = hashSet(-1);
		failure(set);
	}
	/**
	 * SetInstance.hashSet(int)
	 */
	@Test
	public void testHashSet2(){
		setMethod(M_HASHSET1);
		final Set<Integer> set = hashSet(12);
		assertNotNull(getPosition(),set);
	}
}
