package com.lyra.meta.sys;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.reflector.Factory.singleton;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Set;

import com.lyra.db.conn.MetadataContext;
import com.lyra.exp.sys.MemberInitException;
import com.lyra.res.Resources;
import com.lyra.res.Symbol;

/**
 * @author Lang
 * @package com.test.lyra.meta.sys
 * @name ModelConfig
 * @class com.test.lyra.meta.sys.ModelConfig
 * @date Oct 10, 2014 3:08:16 PM
 * @see
 */
@Deprecated
public final class OldModelConfigurator {
	
	// ~ Static Fields =======================================
	/**
	 * Configurator pool, keep every model only reference one configurator *
	 */
	private final static Map<String, OldModelConfigurator> CFG_MAP = hashMap(true);
	/**
	 * Select model record from database by model name *
	 */
	private final static String SQL_MODEL_BY_NAME = "SELECT ROOT_FOLDER,FILE_DATA,FILE_SCHEMA,FILE_MAPPING FROM SYS_MODEL WHERE NAME=''{0}''";
	/**
	 * Select all model records from database *
	 */
	private final static String SQL_SELECT_MODELS = "SELECT NAME FROM SYS_MODEL WHERE IN_USE=1 ORDER BY INIT_ORDER ASC,INIT_SUB_ORDER ASC";
	/**
	 * Static member and it's shared in all model configurators. *
	 */
	private static MetadataContext staticDbCtx = singleton(Resources
			.getPoolClass());

	// ~ Instance Fields =====================================
	/**
	 * Database environment, this context is only used in SQL database. *
	 */
	private transient final MetadataContext dbCtx;
	/**
	 * Data map of every instance *
	 */
	private transient final Map<String, String> dataMap;

	// ~ Constructors ========================================
	private OldModelConfigurator(final String modelName) {
		this.dbCtx = singleton(Resources.getPoolClass());
		this.dataMap = this.loadData(modelName);
		info(getClass(),
				"[Start] : Model Configuring ...... ( Model Name = "
						+ modelName + " )");
		info(getClass(),
				"[Read File] : Data file ==> " + this.dataMap.get("DF"));
		info(getClass(),
				"[Read File] : Mapping file ==> " + this.dataMap.get("MF"));
		info(getClass(),
				"[Read File] : Schema file ==> " + this.dataMap.get("SF"));
	}

	// ~ Static Methods ======================================

	/**
	 * Select all model name from database, this method is static. *
	 */
	public static Set<String> getModelNames() {
		final Set<String> rNames = hashSet();
		// Check static db context
		synchronized (OldModelConfigurator.class) {
			if (nullable(staticDbCtx)) {
				staticDbCtx = singleton(Resources.getPoolClass());
			}
			final Set<String> dbNames = staticDbCtx.selectColumn(
					SQL_SELECT_MODELS, "NAME");
			rNames.addAll(dbNames);
		}
		return rNames;
	}

	/**
	 * @return
	 */
	public static Map<String, OldModelConfigurator> getPool() {
		return CFG_MAP;
	}

	// ~ Methods =============================================

	/**
	 * @return
	 */
	public String getSchemaFilePath() {
		return this.dataMap.get("SF");
	}

	/**
	 * @return
	 */
	public String getDataFilePath() {
		return this.dataMap.get("DF");
	}

	/**
	 * @return
	 */
	public String getMappingFilePath() {
		return this.dataMap.get("MF");
	}

	// ~ Private Methods =====================================
	private Map<String, String> loadData(final String modelName) {
		final Map<String, String> retMap = hashMap(true);
		if (nullable(this.dbCtx)) {
			throw new MemberInitException(getClass(),
					"dbCtx[com.lyra.db.conn.MetadataContext]");
		} else {
			final Map<String, String> dbMap = this.dbCtx.selectRow(
					MessageFormat.format(SQL_MODEL_BY_NAME, modelName),
					"ROOT_FOLDER", "FILE_DATA", "FILE_SCHEMA", "FILE_MAPPING");
			final StringBuilder rootFolder = builder(
					dbMap.get("ROOT_FOLDER"));
			if (rootFolder.charAt(rootFolder.length() - 1) != Symbol.C_SLASH) {
				rootFolder.append('/');
			}
			final String dataPath = rootFolder + dbMap.get("FILE_DATA");
			final String schemaPath = rootFolder + dbMap.get("FILE_SCHEMA");
			final String mappingPath = rootFolder + dbMap.get("FILE_MAPPING");
			retMap.put("DF", dataPath);
			retMap.put("SF", schemaPath);
			retMap.put("MF", mappingPath);
		}
		return retMap;
	}
}
