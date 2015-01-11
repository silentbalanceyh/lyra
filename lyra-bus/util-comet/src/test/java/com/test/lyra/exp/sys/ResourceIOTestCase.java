package com.test.lyra.exp.sys;

import static com.lyra.util.reflector.Factory.instance;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lyra.exp.sys.ResourceIOException;
import com.lyra.util.test.AbstractTestCase;
/**
 * 
 *
 * @author Lang
 * @see
 */
public class ResourceIOTestCase extends AbstractTestCase {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public ResourceIOTestCase() {
		super(ResourceIOException.class.getName());
	}

	// ~ Methods =============================================
	/**
	 * Throw MemberInitException
	 */
	@Test(expected = ResourceIOException.class)
	public void testResourceIOException1() {
		setMethod("Constructor");
		final ResourceIOException exp = instance(ResourceIOException.class,
				getClass(), "testResourceIOException1");
		assertNotNull(getPosition(),exp);
		throw exp;
	}
	
	/**
	 * Throw MemberInitException
	 */
	@Test
	public void testMemberInitException2() {
		setMethod("getErrorCode");
		final ResourceIOException exp = instance(ResourceIOException.class,
				getClass(), "testResourceIOException2");
		assertEquals(getPosition(),-20002,exp.getErrorCode());
	}
	// ~ Private Methods =====================================
}
