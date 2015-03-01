package com.lyra.db.impl;

import com.lyra.db.MetaDao;
import com.lyra.exception.AbstractSchemaException;
import com.lyra.exp.sys.MemberInitException;
import com.lyra.meta.Context;
import com.lyra.meta.builder.MetaDirector;
import com.lyra.meta.sys.OldModelConfigurator;
import com.lyra.res.Resources;

import java.util.Set;
import java.util.Map;

import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.reflector.Factory.instance;

/**
 * @author Lang
 * @package com.test.lyra.db.test.impl
 * @name SqlMetaDao
 * @class com.test.lyra.db.test.impl.SqlMetaDao
 * @date 2014年11月22日 上午5:41:24
 * @see
 */
class SqlMetaDao implements MetaDao {
	// ~ Static Fields =======================================
	/**
	 * MetaDirector pool to avoid re-create director *
	 */
	private static final Map<String, MetaDirector> DIRECTOR_POOL = hashMap(true);

	// ~ Instance Fields =====================================
	/**
	 * Context environment for model *
	 */
	private transient Context context;
	/**
	 * MetaDirector *
	 */
	private transient MetaDirector director;

	// ~ Constructors ========================================

	/**
	 * Public constructor
	 *
	 * @param modelName
	 */
	public SqlMetaDao(final String modelName) {
		if (nullable(modelName)) {
			throw new MemberInitException(getClass(),
					"modelName[com.test.lyra.db.test.impl.SqlMetaDao]");
		}
		this.context = instance(Resources.getContextClass(), modelName);
		this.director = DIRECTOR_POOL.get(modelName);
	}

	// ~ Override Methods ====================================

	/**
	 * Get all model's names
	 */
	@Override
	public Set<String> getModelNames() {
		return OldModelConfigurator.getModelNames();
	}

	/** **/
	@Override
	public void validateSchema() throws AbstractSchemaException {
		if (nullable(this.director)) {
			this.director = instance(MetaDirector.class, this.context);
		} else {
			this.context = this.director.getContext();
		}
		if (!nullable(this.context.getError())) {
			throw this.context.getError();
		}
	}

	/**
	 * Sync method between database and schema *
	 */
	@Override
	public boolean syncMetadata() {
		boolean syncRet = true;
		if (nullable(this.director)) {
			syncRet = false;
		} else {
			syncRet = this.director.syncDatabase();
		}
		return syncRet;
	}
}
