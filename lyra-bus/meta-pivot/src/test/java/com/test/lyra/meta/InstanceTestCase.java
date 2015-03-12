package com.test.lyra.meta;

import static com.lyra.util.logger.Logger.trace;
import static com.lyra.util.reflector.Factory.instance;

import org.junit.Test;

import com.lyra.meta.Context;
import com.lyra.res.Resources;

/**
 * @author Lang
 * @package com.test.lyra.meta.test
 * @name DEFInstanceTestCase
 * @class com.test.lyra.meta.test.InstanceTestCase
 * @date Dec 1, 2014 10:14:21 AM
 * @see
 */
public class InstanceTestCase {
	@Test
	public void testCreateInstance() {
		final Context ctx = instance(Resources.getContextClass(), "testauto");
		trace(getClass(), ctx == null);
		//final MetaDirector director = instance(MetaDirector.class, ctx);
		//assertNotNull("Return reference should not be null.", director);
	}
}
