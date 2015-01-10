package com.lyra.vertx.guice;

import com.google.inject.AbstractModule;

/**
 * Guice bootstrap jersey binder
 *
 * @author Lang
 * @package com.lyra.vertx.guice
 * @name BootstrapBinder
 * @class com.lyra.vertx.guice.BootstrapBinder
 * @date Dec 23, 2014 3:37:01 AM
 * @see
 */
public class BootstrapBinder extends AbstractModule {
	/**
	 * Configures a {@link com.google.inject.Binder} via the exposed methods.
	 */
	@Override
	protected void configure() {
		install(new GuiceJerseyBinder());
	}
}
