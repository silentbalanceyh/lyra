/*
 * The MIT License (MIT)
 * Copyright © 2013 Englishtown <opensource@englishtown.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the “Software”), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.lyra.vertx.inject.guice;

import com.google.inject.AbstractModule;
import org.vertx.java.core.Vertx;
import org.vertx.java.platform.Container;

/**
 * Guice {@link AbstractModule} for vertx and container injections
 */
class VertxBinder extends AbstractModule {

	private final Vertx vertx;
	private final Container container;

	public VertxBinder(Vertx vertx, Container container) {
		this.vertx = vertx;
		this.container = container;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void configure() {

		bind(Vertx.class).toInstance(vertx);
		bind(Container.class).toInstance(container);

	}
}
