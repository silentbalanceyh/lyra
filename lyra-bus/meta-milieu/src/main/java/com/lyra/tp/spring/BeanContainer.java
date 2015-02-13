package com.lyra.tp.spring;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import net.sf.oval.guard.PostValidateThis;
import net.sf.oval.guard.PreValidateThis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.lyra.res.Resources;

/**
 * Bean Container to create new bean which has been defined.
 *
 * @author Lang
 */
@SuppressWarnings({ "unchecked" })
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
	 * Default constructor
	 */
	@PostValidateThis
	private BeanContainer() {
		this.ctx = new ClassPathXmlApplicationContext(Resources.SPRING_CONFIG);
	}

	/**
	 * 
	 * @param path
	 */
	@PostValidateThis
	private BeanContainer(@NotNull @NotEmpty @NotBlank final String path) {
		this.ctx = new FileSystemXmlApplicationContext(path);
	}

	// ~ Methods =============================================

	/**
	 * 在调用这个函数之前，必须保证ApplicationContext不为null
	 */
	@PreValidateThis
	public <T> T getBean(@NotNull @NotEmpty @NotBlank final String beanName) {
		return (T) ctx.getBean(beanName);
	}
}
