package com.lyra.meta.builder.impl;

import static com.lyra.util.calculator.MapCalculator.diff;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.reflector.Factory.singleton;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.lyra.db.conn.MetadataContext;
import com.lyra.exp.AbstractSchemaException;
import com.lyra.exp.sys.MemberInitException;
import com.lyra.meta.Context;
import com.lyra.meta.builder.MetaBuilder;
import com.lyra.meta.json.exp.ConflictPolicyPkTypeMatchingException;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Constants;
import com.lyra.res.Resources;
import com.lyra.res.Symbol;

/**
 * Abstract Meta-data builder
 *
 * @author Lang
 */
public abstract class AbstractMetaBuilder implements MetaBuilder {

	// ~ Instance Fields =====================================
	/**
	 * Database environment, this context is only used in SQL database *
	 */
	private transient final MetadataContext dbCtx;
	/**
	 * Context of Schema information *
	 */
	private transient final Context context;

	// ~ Constructors ========================================

	/**
	 * Default constructor with one parameter *
	 */
	protected AbstractMetaBuilder(final Context context) {
		this.context = context;
		this.dbCtx = singleton(Resources.getPoolClass());
	}

	// ~ Abstract Methods ====================================

	/**
	 * Abstract method which should be implemented by sub-class
	 *
	 * @param name
	 * @param schema
	 */
	protected abstract void buildField(final StringBuilder builder,
			final FieldSchema schema);

	/**
	 * @return
	 */
	protected abstract Set<String> getAutoTypes();

	/**
	 * @return
	 */
	protected abstract Set<String> getGuidTypes();

	// ~ Override Methods ====================================

	/**
	 * Get modeling context
	 *
	 * @return
	 */
	@Override
	public Context getContext() {
		return this.context;
	}

	/**
	 * @return
	 */
	@Override
	public boolean postValidate() {
		this.validatePkDataType();
		boolean ret = false;
		if (nullable(this.context)) {
			throw new MemberInitException(getClass(),
					"context[com.test.lyra.meta.test.Context]");
		} else {
			if (nullable(this.context.getError())) {
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * Get exception information.
	 *
	 * @return
	 */
	@Override
	public AbstractSchemaException getError() {
		return this.getContext().getError();
	}

	// ~ Methods =============================================

	/**
	 * Get context of metadata ( database )
	 *
	 * @return
	 */
	protected MetadataContext getJdbc() {
		if (nullable(this.dbCtx)) {
			throw new MemberInitException(getClass(),
					"dbCtx[com.lyra.db.conn.MetadataContext]");
		}
		return this.dbCtx;
	}

	/**
	 * @return
	 */
	protected Map<String, FieldSchema> getNonKeyFields() {
		return diff(this.getContext().getFields(), this.getContext().getKeys());
	}

	/**
	 * @param pKeys
	 */
	protected void buildPrimaryKeys(final StringBuilder builder,
			final Set<String> pKeys) {
		if (!nullable(builder) && builder.length() > 0) {
			// Change default index for SQL server primary key from CLUSTERED to
			// NONCLUSTERED
			builder.append("PRIMARY KEY");
			if (Resources.DB_CATEGORY.equals(Constants.DF_MSSQL)) {
				builder.append(Symbol.C_WHITESPACE).append("NONCLUSTERED");

			}
			builder.append(Symbol.C_WHITESPACE).append(
					Symbol.C_L_BRACKET);
			final Iterator<String> pIt = pKeys.iterator();
			while (pIt.hasNext()) {
				builder.append(pIt.next()).append(Symbol.C_COMMA);
			}
			builder.delete(builder.length() - 1, builder.length());
			builder.append(Symbol.C_R_BRACKET);
			// builder.append(Resources.SY_WHITESPACE).append("NONCLUSTERED");
		}
	}

	/**
     *
     *
     */
	protected void validatePkDataType() {
		String policy = Symbol.STR_NULL;
		String dataType = Symbol.STR_NULL;
		for (final FieldSchema field : this.context.getKeys().values()) {
			if (!nullable(field) && Symbol.STR_NULL != field.getPolicy()) {
				policy = field.getPolicy();
				dataType = field.getColumnType();
			}
		}
		this.validateRule(policy, dataType);
	}

	// ~ Private Methods =====================================

	/**
	 * Rule checking
	 *
	 * @param policy
	 * @param dataType
	 */
	private void validateRule(final String policy, final String dataType) {
		if (!nullable(policy) && !nullable(dataType)) {
			boolean error = false;
			if (policy.equals(Constants.KP_AUTO)
					&& !this.getAutoTypes().contains(dataType)) {
				error = true;
			} else if (policy.equals(Constants.KP_GUID)
					&& !this.getGuidTypes().contains(dataType)) {
				error = true;
			}
			if (error) {
				this.context
						.setError(new ConflictPolicyPkTypeMatchingException());
			}
		}
	}
}
