package com.test.lyra.db;

import com.lyra.meta.sys.OldModelConfigurator;
import com.lyra.meta.sys.ModelDataDeployer;
import com.lyra.res.Resources;

import java.util.Set;

import static com.lyra.util.logger.Logger.info;

public class DeployerTestCase {
	/**
	 * @throws Exception
	 */
	// TODO:
	public void testDeploy() throws Exception {
		if ("SQL".equals(Resources.DB_MODE)) {
			final Set<String> modelNames = OldModelConfigurator.getModelNames();
			for (final String model : modelNames) {
				info(getClass(),
						"[TEST] Model business-data deploying ...... ( Model Name = "
								+ model + " )");
				final ModelDataDeployer roleDeployer = ModelDataDeployer
						.singlton(model);
				roleDeployer.deployModelData();
			}
		}
	}
}
