package com.lyra.meta.json;

import com.lyra.exception.AbstractSchemaException;
import com.lyra.exp.sys.MemberInitException;
import com.lyra.meta.json.exp.*;
import com.lyra.mod.def.EntitySchema;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Constants;
import com.lyra.res.Symbol;

import java.util.List;
import java.util.Set;
import java.util.Map;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.internal.Validator.nullable;

/**
 * Schema validator.
 *
 * @author Lang
 */
final class GenericValidator {

	// ~ Instance Fields =====================================
	/**
	 * EntitySchema object which current validator verified. *
	 */
	private transient final EntitySchema schema;
	/**
	 * Exception of schema validation *
	 */
	private transient AbstractSchemaException error;
	/**
	 * Flag to break the validation work flow *
	 */
	private transient boolean isContinue;

	// ~ Constructors ========================================

	/**
	 * Public constractor *
	 */
	GenericValidator(final EntitySchema schema) {
		this.schema = schema;
	}

	// ~ Methods =============================================

	/**
	 * @return
	 */
	public AbstractSchemaException getError() {
		return this.error;
	}

	/**
	 * @param error
	 */
	public void setError(final AbstractSchemaException error) {
		if (nullable(this.error)) {
			this.isContinue = false;
			this.error = error;
		}
	}

	/**
	 * Public interface to validate schema information
	 */
	public void validateSchema() {
		if (nullable(this.schema)) {
			throw new MemberInitException(getClass(),
					"schema[com.lyra.mod.def.EntitySchema]");
		} else {
			// 1.Checking missed definition information
			// __key__, __meta__, __fields__
			if (nullable(this.error)) {
				isContinue = true;
			}
			// __key__ -> pKeys
			if (isContinue) {
				this.validate10004();
			}
			// __meta__ -> table,name,package,fullname
			if (isContinue) {
				this.validate10005();
			}
			// policy of all fields missing
			if (isContinue) {
				this.validate10007();
			}
			// Pirmary key policy missing
			if (isContinue) {
				this.validate10008();
			}
			// More than two policy existing
			if (isContinue) {
				this.validate10009();
			}
			// MULTI, AUTO, GUID, RANDOM policy
			if (isContinue) {
				this.validate10010And10011();
			}
			return;
		}
	}

	// ~ Private Methods =====================================

	/**
	 * Missing Primary Key
	 */
	private void validate10004() {
		final Set<String> keys = this.schema.getPrimaryKeys();
		if (keys.isEmpty()) {
			this.setError(new MissingPrimaryKeyDefException());
		}
	}

	/**
	 * Missing Attributes of Meta
	 */
	private void validate10005() {
		if (Symbol.STR_NULL == this.schema.getTable()) {
			this.setError(new MissingMetaAttrException("table"));
		} else if (Symbol.STR_NULL == this.schema.getModel()) {
			this.setError(new MissingMetaAttrException("name"));
		} else if (Symbol.STR_NULL == this.schema.getPackage()) {
			this.setError(new MissingMetaAttrException("package"));
		} else if (Symbol.STR_NULL == this.schema.getFullName()) {
			this.setError(new MissingMetaAttrException("fullName"));
		}
	}

	/**
	 * @param schema
	 * @return
	 */
	public int validate10006(final FieldSchema schema) {
		int retValue = 0;
		if (Symbol.STR_NULL == schema.getColumnType()
				|| Symbol.STR_NULL == schema.getColumnType()
				|| Symbol.STR_NULL == schema.getName()
				|| Symbol.STR_NULL == schema.getType()) {
			retValue = 1;
		}
		return retValue;
	}

	/**
	 * Missing policy
	 */
	private void validate10007() {
		final List<String> policies = this.getPolicies();
		if (policies.isEmpty()) {
			this.setError(new MissingPolicyDefException());
		}
	}

	/**
	 * Primary key missed "policy"
	 */
	private void validate10008() {
		final Set<String> primarySet = this.schema.getPrimaryKeys();
		final Map<String, FieldSchema> schemaMap = this.schema.getFields();
		String invalidPKey = Symbol.STR_NULL;
		for (final String primaryKey : primarySet) {
			final FieldSchema schema = schemaMap.get(primaryKey);
			if (Symbol.STR_NULL == schema.getPolicy()) {
				invalidPKey = schema.getColumnName();
			}
		}
		if (Symbol.STR_NULL != invalidPKey) {
			this.setError(new MissingPrimaryKeyPolicyException(invalidPKey));
		}
	}

	/**
	 * Non-Multi primary key policy validation
	 */
	private void validate10009() {
		final List<String> policies = this.getPolicies();
		final Set<String> policySet = hashSet(policies);
		if (Symbol.I_ONE < policySet.size()) {
			final StringBuilder policiesStr = builder();
			for (final String policy : policySet) {
				policiesStr.append(policy).append(Symbol.C_COMMA);
			}
			this.setError(new ConflictPolicyMoreThanTwoException(policiesStr
					.toString()));
		}
	}

	private void validate10010And10011() {
		final List<String> policies = this.getPolicies();
		if (Symbol.I_ONE <= policies.size()) {
			final String currentPolicy = policies.get(0);
			if (currentPolicy.equals(Constants.KP_MULTI)) {
				if (Symbol.I_ONE == policies.size()) {
					this.setError(new ConflictPolicyMMatchingException());
				}
			} else {
				if (Symbol.I_ONE < policies.size()) {
					this.setError(new ConflictPolicyNMMatchingException(
							currentPolicy));
				}
			}
		}
	}

	/**
	 * Get policies
	 *
	 * @return
	 */
	private List<String> getPolicies() {
		final List<String> policies = arrayList();
		for (final FieldSchema field : this.schema.getFields().values()) {
			if (!nullable(field) && Symbol.STR_NULL != field.getPolicy()) {
				policies.add(field.getPolicy());
			}
		}
		return policies;
	}
}
