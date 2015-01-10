package com.test.lyra.util.reflector;

import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.reflector.Factory.singleton;
import static org.junit.Assert.assertEquals;

import java.util.Set;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.test.base.AbstractTestCase;
import com.test.lyra.util.assist.IInstanceModel;
import com.test.lyra.util.assist.InstanceModel;

/**
 * 测试方法：com.lyra.util.reflector.Factory.singleton
 *
 * @author Lang
 * @see
 */
public class Factory2TestCase extends AbstractTestCase implements
		ReflectorConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public Factory2TestCase() {
		super(TestClasses.FACTORY);
		setMethod(M_SINGLETON1);
		// final IInstanceModel model = singleton(null,"name"); Compile Error
	}

	// ~ Methods =============================================
	/**
	 * Factory.singleton(String,Object...)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testSingleton1() {
		final IInstanceModel model = singleton("  ", "name");
		failure(model);
	}

	/**
	 * Factory.singleton(String,Object...)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testSingleton2() {
		final IInstanceModel model = singleton("", "name");
		failure(model);
	}

	/**
	 * Factory.singleton(String,Object...)
	 */
	@Test
	public void testSingleton3() {
		final Set<IInstanceModel> modelSet = hashSet();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET);
			modelSet.add(model);
		}
		assertEquals(getPosition(), 1, modelSet.size());
	}

	/**
	 * Factory.singleton(String,Object...)
	 */
	@Test
	public void testSingleton4() {
		final Set<IInstanceModel> modelSet = hashSet();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET, "Lang4" + i);
			modelSet.add(model);
		}
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET, "Lang4" + i);
			modelSet.add(model);
		}
		assertEquals(getPosition(), 20, modelSet.size());
	}
	
	
	/**
	 * Factory.singleton(String,Object...)
	 */
	@Test
	public void testSingleton5() {
		final Set<IInstanceModel> modelSet = hashSet();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET, "Lang5" + i, "lang.yu@hp.com");
			modelSet.add(model);
		}
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET, "Lang5" + i, "lang.yu@hp.com");
			modelSet.add(model);
		}
		assertEquals(getPosition(), 20, modelSet.size());
	}
	
	/**
	 * Factory.singleton(String,Object...)
	 */
	@Test
	public void testSingleton6() {
		setMethod(M_SINGLETON2);
		final Set<IInstanceModel> modelSet = hashSet();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(InstanceModel.class);
			modelSet.add(model);
		}
		assertEquals(getPosition(), 1, modelSet.size());
	}

	/**
	 * Factory.singleton(String,Object...)
	 */
	@Test
	public void testSingleton7() {
		setMethod(M_SINGLETON2);
		final Set<IInstanceModel> modelSet = hashSet();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(InstanceModel.class, "Lang7" + i);
			modelSet.add(model);
		}
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(InstanceModel.class, "Lang7" + i);
			modelSet.add(model);
		}
		assertEquals(getPosition(), 20, modelSet.size());
	}
	
	
	/**
	 * Factory.singleton(String,Object...)
	 */
	@Test
	public void testSingleton8() {
		setMethod(M_SINGLETON2);
		final Set<IInstanceModel> modelSet = hashSet();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(InstanceModel.class, "Lang8" + i, "lang1.yu@hp.com");
			modelSet.add(model);
		}
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(InstanceModel.class, "Lang8" + i, "lang1.yu@hp.com");
			modelSet.add(model);
		}
		assertEquals(getPosition(), 20, modelSet.size());
	}

	// ~ Private Methods =====================================
}
