package com.test.lyra.util.reflector;

import static com.lyra.util.logger.Logger.trace;
import static com.lyra.util.reflector.Factory.instance;
import static org.junit.Assert.assertNotNull;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.test.base.AbstractTestCase;
import com.test.lyra.util.assist.IInstanceModel;
import com.test.lyra.util.assist.InstanceModel;

/**
 * 测试方法：com.lyra.util.reflector.Factory.instance
 *
 * @author Lang
 * @see
 */
public class Factory1TestCase extends AbstractTestCase implements
		ReflectorConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public Factory1TestCase() {
		super(TestClasses.FACTORY);
		setMethod(M_INSTANCE1);
		// final IInstanceModel model = instance(null,"name"); Compile Error
	}

	// ~ Methods =============================================
	/**
	 * Factory.instance(String,Object...)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testInstance1() {
		final IInstanceModel model = instance("  ", "name");
		failure(model);
	}

	/**
	 * Factory.instance(String,Object...)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testInstance2() {
		final IInstanceModel model = instance("", "name");
		failure(model);
	}

	/**
	 * Factory.instance(String,Object...)
	 */
	@Test
	public void testInstance3() {
		final IInstanceModel model = instance(T_TARGET);
		trace(getClass(), model);
		assertNotNull(getPosition(), model);
	}

	/**
	 * Factory.instance(String,Object...)
	 */
	@Test
	public void testInstance4() {
		final IInstanceModel model = instance(T_TARGET, "Lang1");
		trace(getClass(), model);
		assertNotNull(getPosition(), model);
	}

	/**
	 * Factory.instance(String,Object...)
	 */
	@Test
	public void testInstance5() {
		final IInstanceModel model = instance(T_TARGET, "Lang1",
				"silentbalanceyh@126.com");
		trace(getClass(), model);
		assertNotNull(getPosition(), model);
	}

	/**
	 * Factory.instance(String,Object...)
	 */
	@Test
	public void testInstance6() {
		setMethod(M_INSTANCE2);
		final IInstanceModel model = instance(InstanceModel.class);
		trace(getClass(), model);
		assertNotNull(getPosition(), model);
	}

	/**
	 * Factory.instance(String,Object...)
	 */
	@Test
	public void testInstance7() {
		setMethod(M_INSTANCE2);
		final IInstanceModel model = instance(InstanceModel.class, "Lang2");
		trace(getClass(), model);
		assertNotNull(getPosition(), model);
	}

	/**
	 * Factory.instance(String,Object...)
	 */
	@Test
	public void testInstance8() {
		setMethod(M_INSTANCE2);
		final IInstanceModel model = instance(InstanceModel.class, "Lang2",
				"silentbalanceyh@126.com");
		trace(getClass(), model);
		assertNotNull(getPosition(), model);
	}
	// ~ Private Methods =====================================
}
