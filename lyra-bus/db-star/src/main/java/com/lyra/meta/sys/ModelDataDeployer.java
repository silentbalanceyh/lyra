package com.lyra.meta.sys;

import com.lyra.db.sql.RecordWriter;
import com.lyra.db.sql.impl.BridgeDao;
import com.lyra.meta.Context;
import com.lyra.meta.Record;
import com.lyra.res.Resources;

import java.util.List;
import java.util.Map;

import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.reflector.Factory.instance;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.sys
 * @name ModelDataDeployer
 * @class com.test.lyra.db.sql.sys.ModelDataDeployer
 * @date Oct 14, 2014 11:18:09 PM
 * @see
 */
public final class ModelDataDeployer {
	// ~ Static Fields =======================================
	/**
	 * Instance pool for deployer and every model should has only one deployer *
	 */
	private static final Map<String, ModelDataDeployer> DEPLOYER_MAP = hashMap(true);

	// ~ Instance Fields =====================================
	/**
	 * Context environment for every model, read schema definition information.
	 * *
	 */
	private transient final Context context;
	/**
	 * Record writer and it access database and focus on writing
	 * Add,Delete,Update *
	 */
	private transient final RecordWriter writer;

	// ~ Constructors ========================================

	/**
	 * Private constructor *
	 */
	private ModelDataDeployer(final String modelName) {
		this.context = instance(Resources.getContextClass(), modelName);
		this.writer = instance(BridgeDao.class, modelName);
	}

	// ~ Static Methods ======================================

	/**
	 * Get instance of deployer *
	 */
	public static ModelDataDeployer singlton(final String modelName) {
		synchronized (ModelDataDeployer.class) {
			ModelDataDeployer deployer = DEPLOYER_MAP.get(modelName);
			if (nullable(deployer)) {
				deployer = new ModelDataDeployer(modelName);
				DEPLOYER_MAP.put(modelName, deployer);
			}
			return deployer;
		}
	}

	// ~ Methods =============================================

	/**
	 * Major method to deploye model data here *
	 */
	public boolean deployModelData() {
		final List<Record> records = this.context.readRecords();
		final boolean delRet = this.writer.deleteAll();
		return this.writer.add(records) && delRet;
	}
}
