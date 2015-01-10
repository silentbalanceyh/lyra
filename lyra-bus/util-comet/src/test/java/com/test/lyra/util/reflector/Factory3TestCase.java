package com.test.lyra.util.reflector;

import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.logger.Logger.trace;
import static com.lyra.util.reflector.Factory.singleton;
import static org.junit.Assert.assertEquals;

import java.util.Map;

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
public class Factory3TestCase extends AbstractTestCase implements
		ReflectorConstant {
	// ~ Static Fields =======================================
	/** **/
	private transient final Map<String,IInstanceModel> POOL = hashMap(true);
	
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public Factory3TestCase(){
		super(TestClasses.FACTORY);
		setMethod(M_SINGLETON3);
	}
	// ~ Methods =============================================
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testSingleton9() {
		final IInstanceModel model = singleton(T_TARGET, null, "name");
		failure(model);
	}
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	@Test
	public void testSingleton10() {
		POOL.clear();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET,POOL);
			trace(getClass(),model);
		}
		assertEquals(getPosition(), 1, POOL.size());
	}
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	@Test
	public void testSingleton11() {
		POOL.clear();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET,POOL,"LANG11");
			trace(getClass(),model);
		}
		assertEquals(getPosition(), 1, POOL.size());
	}
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	@Test
	public void testSingleton12() {
		POOL.clear();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET,POOL,"LANG12","Email12" + i);
			trace(getClass(),model);
		}
		assertEquals(getPosition(), 1, POOL.size());
	}
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	@Test
	public void testSingleton13() {
		POOL.clear();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET,POOL,"LANG13" + i,"Email13" + i);
			trace(getClass(),model);
		}
		assertEquals(getPosition(), 20, POOL.size());
	}
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	@Test
	public void testSingleton14() {
		POOL.clear();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET,POOL,"LANG14" + i,"Email14" + i);
			trace(getClass(),model);
		}
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(T_TARGET,POOL,"LANG14" + i,"Email14" + i);
			trace(getClass(),model);
		}
		assertEquals(getPosition(), 20, POOL.size());
	}
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	@Test
	public void testSingleton15() {
		POOL.clear();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(InstanceModel.class,POOL);
			trace(getClass(),model);
		}
		assertEquals(getPosition(), 1, POOL.size());
	}
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	@Test
	public void testSingleton16() {
		POOL.clear();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(InstanceModel.class,POOL,"LANG16");
			trace(getClass(),model);
		}
		assertEquals(getPosition(), 1, POOL.size());
	}
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	@Test
	public void testSingleton17() {
		POOL.clear();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(InstanceModel.class,POOL,"LANG17","Email17" + i);
			trace(getClass(),model);
		}
		assertEquals(getPosition(), 1, POOL.size());
	}
	/**
	 * Factory.singleton(String,Map<String,T>,Object...)
	 */
	@Test
	public void testSingleton18() {
		POOL.clear();
		for (int i = 0; i < 20; i++) {
			final IInstanceModel model = singleton(InstanceModel.class,POOL,"LANG18" + i,"Email18" + i);
			trace(getClass(),model);
		}
		assertEquals(getPosition(), 20, POOL.size());
	}
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
