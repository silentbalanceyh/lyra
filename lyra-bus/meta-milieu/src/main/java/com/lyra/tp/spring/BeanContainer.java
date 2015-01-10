package com.lyra.tp.spring;

import static com.lyra.util.internal.Validator.nullable;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyra.res.Resources;

/**
 * Bean Container to create new bean which has been defined.
 *
 * @author Lang
 */
@SuppressWarnings({ "unchecked"})
public final class BeanContainer {

	// ~ Static Fields =======================================
	/**
	 * Singleton instance *
	 */
	private static BeanContainer instance;

	// ~ Instance Fields =====================================
	/**
	 * Spring application context *
	 */
	private transient final ApplicationContext ctx;

	// ~ Constructors ========================================

	/**
	 * Private constructor *
	 */
	private BeanContainer() {
		this.ctx = new ClassPathXmlApplicationContext(Resources.SPRING_CONFIG);
	}

	// ~ Static Methods ======================================

	/**
	 * Generation singleton instance. *
	 */
	public static BeanContainer singleton() {
		synchronized (BeanContainer.class) {
			if (nullable(instance)) {
				instance = new BeanContainer();
			}
			return instance;
		}
	}

	// ~ Methods =============================================

	/**
	 * Automatically conversion *
	 */
	public <T extends Object> T getBean(final String beanName) {
		return (T) ctx.getBean(beanName);
	}
}
