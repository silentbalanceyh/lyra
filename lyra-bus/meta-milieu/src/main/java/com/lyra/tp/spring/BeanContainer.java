package com.lyra.tp.spring;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import net.sf.oval.guard.PostValidateThis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lyra.res.Resources;

/**
 * Bean Container to create new bean which has been defined.
 *
 * @author Lang
 */
@SuppressWarnings({ "unchecked"})
@Guarded
public final class BeanContainer {
	// ~ Instance Fields =====================================
	/**
	 * Spring application context *
	 */
	@NotNull
	private transient final ApplicationContext ctx;

	// ~ Constructors ========================================

	/**
	 * Private constructor *
	 */
	@PostValidateThis
	private BeanContainer() {
		this.ctx = new ClassPathXmlApplicationContext(Resources.SPRING_CONFIG);
	}

	// ~ Methods =============================================

	/**
	 * Automatically conversion *
	 */
	public <T> T getBean(
			@NotNull @NotEmpty @NotBlank final String beanName) {
		return (T)ctx.getBean(beanName);
	}
}
