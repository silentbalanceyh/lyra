package com.test.lyra.meta;

import com.lyra.exp.AbstractSchemaException;
import com.lyra.meta.Context;
import com.lyra.meta.builder.MetaDirector;
import com.lyra.meta.sys.OldModelConfigurator;
import com.lyra.res.Constants;
import com.lyra.res.Resources;
import org.junit.Test;

import java.util.Set;

import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.reflector.Factory.instance;
import static org.junit.Assert.assertTrue;

/**
 * @author Lang
 * @package com.test.lyra.meta.test
 * @name BuilderTestCase
 * @class com.test.lyra.meta.test.BuilderTestCase
 * @date Nov 3, 2014 6:55:55 AM
 * @see
 */
public class BuilderTestCase {
	/**
	 * Test sync operation on Microsoft SQL Server
	 */
	@Test
	public void testMsSQL() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Set<String> modelNames = OldModelConfigurator.getModelNames();
			for (final String modelName : modelNames) {
				info(getClass(),
						"[TEST] Model meta-data deploying ...... ( Model Name = "
								+ modelName + " )");
				final Context ctx = instance(Resources.getContextClass(),
						modelName);
				info(getClass(),ctx == null);
				final MetaDirector director = instance(MetaDirector.class, ctx);
				final boolean ret = director.syncDatabase();
				assertTrue("[T] The sync result must be true!", ret);
			}
		}
	}
}
