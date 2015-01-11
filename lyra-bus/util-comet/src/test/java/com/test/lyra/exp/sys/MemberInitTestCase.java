package com.test.lyra.exp.sys;

import static com.lyra.util.reflector.Factory.instance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lyra.exp.sys.MemberInitException;
import com.lyra.util.test.AbstractTestCase;

/**
 * 测试异常类：com.lyra.exp.sys.MemberInitException
 *
 * @author Lang
 * @see
 */
public class MemberInitTestCase extends AbstractTestCase {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public MemberInitTestCase() {
		super(MemberInitException.class.getName());
	}

	// ~ Methods =============================================
	/**
	 * Throw MemberInitException
	 */
	@Test(expected = MemberInitException.class)
	public void testMemberInitException1() {
		setMethod("Constructor");
		final MemberInitException exp = instance(MemberInitException.class,
				MemberInitTestCase.class, "testMemberInitException1");
		assertNotNull(getPosition(),exp);
		throw exp;
	}
	
	/**
	 * Throw MemberInitException
	 */
	@Test
	public void testMemberInitException2() {
		setMethod("getErrorCode");
		final MemberInitException exp = instance(MemberInitException.class,
				MemberInitTestCase.class, "testMemberInitException2");
		assertEquals(getPosition(),-20001,exp.getErrorCode());
	}
	// ~ Private Methods =====================================
}
