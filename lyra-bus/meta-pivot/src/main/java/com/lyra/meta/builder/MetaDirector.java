package com.lyra.meta.builder;

import com.lyra.exception.AbstractSchemaException;
import com.lyra.meta.Context;
import com.lyra.res.Resources;

import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.reflector.Factory.instance;

/**
 * Director for database to build database metadata.
 *
 * @author Lang
 */
public final class MetaDirector {

	// ~ Instance Fields =====================================
	/**
	 * String inject strategy builder object. *
	 */
	private transient MetaBuilder builder;
	/**
	 * Transfer exception *
	 */
	private transient AbstractSchemaException error;

	// ~ Constructors ========================================

	/**
	 * @param context
	 */
	MetaDirector(final Context context) {
		final String clazz = Resources.getBuilderClass(Resources.DB_CATEGORY);
		// No Error read
		if (nullable(context.getError())) {
			this.builder = instance(clazz, context);
			// Post validation
			if (!this.builder.postValidate()) {
				// context.setError(this.builder.getError());
				info(getClass(),
						"[VE] Schema post validation: " + context.getError());
				// Clean Up builder because post validation failure.
				this.builder = nullObj();
			}
		} else {
			info(getClass(), context.getError());
		}
		if (!nullable(context.getError())) {
			this.error = context.getError();
		}
	}

	// ~ Methods =============================================

	/**
	 * Get context environment for processing model
	 *
	 * @return
	 */
	public Context getContext() {
		return this.builder.getContext();
	}

	/**
	 * Throw exception from meta director;
	 *
	 * @return
	 */
	public AbstractSchemaException getError() {
		return this.error;
	}

	/**
	 * Sync database structure: 1. Table sync 2. Field sync ( Add/Remove ) 3.
	 * Field constraints sync 4. Sync between schema json file and database
	 * structure
	 *
	 * @return
	 */
	public boolean syncDatabase() {
		boolean ret = false;
		if (this.builder.existTable()) {
			ret = this.builder.updateTable();
		} else {
			ret = this.builder.createTable();
		}
		return ret;
	}
}
