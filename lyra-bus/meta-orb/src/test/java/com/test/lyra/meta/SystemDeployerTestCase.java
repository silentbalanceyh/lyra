package com.test.lyra.meta;

import com.lyra.meta.sys.SystemDataDeployer;
import org.junit.Test;

import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.reflector.Factory.singleton;
import static org.junit.Assert.assertTrue;

/**
 * @author Lang
 * @package com.test.lyra.meta
 * @name SystemDeployerTestCase
 * @class com.test.lyra.meta.SystemDeployerTestCase
 * @date Nov 24, 2014 4:01:34 PM
 * @see
 */
public class SystemDeployerTestCase {
	@Test
	public void testSystemDeployer() {
		final SystemDataDeployer deployer = singleton(SystemDataDeployer.class);
		final boolean ret = deployer.deploySystemData();
		info(getClass(), "[TEST] Deploy result: " + ret);
		assertTrue("[T] Deployment failure, please check.", ret);
	}
}
