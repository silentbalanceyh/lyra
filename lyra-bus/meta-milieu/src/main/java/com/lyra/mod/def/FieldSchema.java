package com.lyra.mod.def;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.stereotype.Component;

import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

/**
 * For an entity, this schema is defined for fields
 *
 * @author Lang
 */
@Component
public final class FieldSchema {

	// ~ Instance Fields =====================================
	/**
	 * Field name of current record. *
	 */
	@JsonProperty("name")
	private String name;
	/**
	 * Field data type of current record. *
	 */
	@JsonProperty("type")
	private String type;
	/**
	 * Mapped database column name. *
	 */
	@JsonProperty("columnName")
	private String columnName;
	/**
	 * Mapped database column data type. *
	 */
	@JsonProperty("columnType")
	private String columnType;
	/**
	 * Determine if current field should be NULL or NOT NULL. *
	 */
	@JsonProperty("nullable")
	private boolean nullable = true;
	/**
	 * Determine if current field should be unique. *
	 */
	@JsonProperty("unique")
	private boolean unique;
	/**
	 * ID Policy: {RANDOM,AUTO,GUID,MULTI} *
	 */
	@JsonProperty("policy")
	private String policy;
	/**
	 * Only for AUTO policy. *
	 */
	@JsonProperty("seq-init")
	private int seqInit;
	/**
	 * Only for AUTO policy. *
	 */
	@JsonProperty("seq-step")
	private int seqStep;
	/**
	 * Only for Oracle/PostgreSQL *
	 */
	@JsonProperty("seq-name")
	private String seqName;
	/**
	 * Max length of current field *
	 */
	@JsonProperty("length")
	private int length;
	/**
	 * This attribute is for foreign key, related table. *
	 */
	@JsonProperty("ref")
	private String refTable;
	/**
	 * This attribute is for foreign key, related column. *
	 */
	@JsonProperty("refId")
	private String refId;

	// ~ Constructors ========================================

	/**
	 * Constructor for Jackson *
	 */
	public FieldSchema() {
		// For Jackson serialization
	}

	/**
	 * Construct field by name only *
	 */
	public FieldSchema(final String name) {
		this.name = name;
	}

	// ~ Methods =============================================

	/**
	 * Get name of current field.
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of current field.
	 *
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Get data type of current field.
	 *
	 * @return
	 */
	public String getType() {
		return type;
	}

	/**
	 * Set data type of current field.
	 *
	 * @param type
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * Get column name of current field.
	 *
	 * @return
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * Set column name of current field.
	 *
	 * @param columnName
	 */
	public void setColumnName(final String columnName) {
		this.columnName = columnName;
	}

	/**
	 * Get column type of current field.
	 *
	 * @return
	 */
	public String getColumnType() {
		return columnType;
	}

	/**
	 * Set column type of current field.
	 *
	 * @param columnType
	 */
	public void setColumnType(final String columnType) {
		this.columnType = columnType;
	}

	/**
	 * Get nullable attribute which determine if current field is NULL or NOT.
	 *
	 * @return
	 */
	public boolean isNullable() {
		return nullable;
	}

	/**
	 * Set nullable attribute
	 *
	 * @param nullable
	 */
	public void setNullable(final boolean nullable) {
		this.nullable = nullable;
	}

	/**
	 * Get unique attribute which determine if current field is UNIQUE
	 *
	 * @return
	 */
	public boolean isUnique() {
		return unique;
	}

	/**
	 * Set unique attribute
	 *
	 * @param unique
	 */
	public void setUnique(final boolean unique) {
		this.unique = unique;
	}

	/**
	 * @return the policy
	 */
	public String getPolicy() {
		return policy;
	}

	/**
	 * @param policy
	 *            the policy to set
	 */
	public void setPolicy(final String policy) {
		this.policy = policy;
	}

	/**
	 * @return the seqInit
	 */
	public int getSeqInit() {
		return seqInit;
	}

	/**
	 * @param seqInit
	 *            the seqInit to set
	 */
	public void setSeqInit(final int seqInit) {
		this.seqInit = seqInit;
	}

	/**
	 * @return the seqStep
	 */
	public int getSeqStep() {
		return seqStep;
	}

	/**
	 * @param seqStep
	 *            the seqStep to set
	 */
	public void setSeqStep(final int seqStep) {
		this.seqStep = seqStep;
	}

	/**
	 * @return the seqName
	 */
	public String getSeqName() {
		return seqName;
	}

	/**
	 * @param seqName
	 *            the seqName to set
	 */
	public void setSeqName(final String seqName) {
		this.seqName = seqName;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length
	 *            the length to set
	 */
	public void setLength(final int length) {
		this.length = length;
	}

	/**
	 * @return the refTable
	 */
	public String getRefTable() {
		return refTable;
	}

	/**
	 * @param refTable
	 *            the refTable to set
	 */
	public void setRefTable(final String refTable) {
		this.refTable = refTable;
	}

	/**
	 * @return the refId
	 */
	public String getRefId() {
		return refId;
	}

	/**
	 * @param refId
	 *            the refId to set
	 */
	public void setRefId(final String refId) {
		this.refId = refId;
	}

	// ~ hashCode,equals,toString ============================

	/**
     *
     */
	@Override
	public String toString() {
		return "FieldSchema [name=" + name + ", type=" + type + ", columnName="
				+ columnName + ", columnType=" + columnType + ", nullable="
				+ nullable + ", unique=" + unique + ", policy=" + policy
				+ ", seqInit=" + seqInit + ", seqStep=" + seqStep
				+ ", seqName=" + seqName + ", length=" + length + "]";
	}

	/**
     *
     */
	@Override
	public int hashCode() {
		int result = hash(1, this.name);
		result = hash(result, this.type);
		result = hash(result, this.columnName);
		result = hash(result, this.columnType);
		result = hash(result, this.nullable);
		result = hash(result, this.unique);
		result = hash(result, this.policy);
		result = hash(result, this.seqInit);
		result = hash(result, this.seqStep);
		result = hash(result, this.seqName);
		result = hash(result, this.length);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final FieldSchema that = (FieldSchema) obj;
			ret &= equal(this.name, that.name);
			ret &= equal(this.type, that.type);
			ret &= equal(this.columnName, that.columnName);
			ret &= equal(this.columnType, that.columnType);
			ret &= equal(this.nullable, that.nullable);
			ret &= equal(this.unique, that.unique);
			ret &= equal(this.policy, that.policy);
			ret &= equal(this.seqInit, that.seqInit);
			ret &= equal(this.seqStep, that.seqStep);
			ret &= equal(this.seqName, that.seqName);
			ret &= equal(this.length, that.length);
		}
		return ret;
	}
}
