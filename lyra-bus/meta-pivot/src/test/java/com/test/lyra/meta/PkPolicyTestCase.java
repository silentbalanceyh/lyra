package com.test.lyra.meta;

import com.lyra.exception.AbstractSchemaException;
import com.lyra.meta.Context;
import com.lyra.meta.builder.MetaDirector;
import com.lyra.res.Constants;
import com.lyra.res.Resources;

import org.junit.Test;

import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.reflector.Factory.instance;
import static org.junit.Assert.assertTrue;

/**
 * @author Lang
 * @package com.test.lyra.meta.test
 * @name PkPolicyTestCase
 * @class com.test.lyra.meta.test.PkPolicyTestCase
 * @date Nov 3, 2014 6:55:48 AM
 * @see
 */
public class PkPolicyTestCase {
	/**
	 * Policy database sync result *
	 */
	private static final String TEST_RESULT = "[T] The sync result must be true!";

	/**
	 * Test AUTO policy *
	 */
	@Test
	public void testAuto() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			info(getClass(),
					"[TEST] Model meta-data deploying ...... ( Model Name = testauto )");
			final Context ctx = instance(Resources.getContextClass(),
					"testauto");
			final MetaDirector director = instance(MetaDirector.class, ctx);
			final boolean ret = director.syncDatabase();
			assertTrue(TEST_RESULT, ret);
		}
	}

	/**
	 * Test GUID policy *
	 */
	@Test
	public void testGuid() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			info(getClass(),
					"[TEST] Model meta-data deploying ...... ( Model Name = testguid )");
			final Context ctx = instance(Resources.getContextClass(),
					"testguid");
			final MetaDirector director = instance(MetaDirector.class, ctx);
			final boolean ret = director.syncDatabase();
			assertTrue(TEST_RESULT, ret);
		}
	}

	/**
	 * Test MULTI policy *
	 */
	@Test
	public void testMulti() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			info(getClass(),
					"[TEST] Model meta-data deploying ...... ( Model Name = testmulti )");
			final Context ctx = instance(Resources.getContextClass(),
					"testmulti");
			final MetaDirector director = instance(MetaDirector.class, ctx);
			final boolean ret = director.syncDatabase();
			assertTrue(TEST_RESULT, ret);
		}
	}

	/**
	 * Test RANDOM policy *
	 */
	@Test
	public void testRandom() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			info(getClass(),
					"[TEST] Model meta-data deploying ...... ( Model Name = testrandom )");
			final Context ctx = instance(Resources.getContextClass(),
					"testrandom");
			final MetaDirector director = instance(MetaDirector.class, ctx);
			final boolean ret = director.syncDatabase();
			assertTrue(TEST_RESULT, ret);
		}
	}
}
