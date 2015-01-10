package com.test.lyra.util.instance;

import static com.lyra.util.instance.NullInstance.nullObj;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.test.base.AbstractTestCase;

/**
 * 测试类：com.lyra.util.instance.NullInstance
 * 
 * @author Lang
 * @see
 */
public class NullTestCase extends AbstractTestCase implements InstanceConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public NullTestCase() {
		super(TestClasses.NULL);
	}

	// ~ Methods =============================================
	/**
	 * NullInstance.nullObj();
	 */
	@Test
	public void testNull() {
		setMethod(M_NULLOBJ);
		final String nullStr = nullObj();
		assertNull(getPosition(), nullStr);
	}
	// ~ Private Methods =====================================
}
