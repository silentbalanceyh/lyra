package com.test.lyra.tp.spring;

import static com.lyra.util.reflector.Factory.singleton;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lyra.tp.spring.BeanContainer;
import com.lyra.util.test.AbstractTestCase;
/**
 * Spring容器测试
 *
 * @author Lang
 * @see
 */
public class BeanContainerTestCase extends AbstractTestCase {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public BeanContainerTestCase(){
		super("com.lyra.tp.spring.BeanContainer");
	}
	// ~ Methods =============================================
	/**
	 * 测试构造函数
	 */
	@Test
	public void testConstructor(){
		setMethod("Constructor");
		final BeanContainer container = singleton(BeanContainer.class);
		assertNotNull(getPosition(),container);
	}
	// ~ Private Methods =====================================
}
