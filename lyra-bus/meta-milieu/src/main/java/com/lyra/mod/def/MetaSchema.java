package com.lyra.mod.def;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.stereotype.Component;

import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

/**
 * For an entity, this schema is defined for an instance
 *
 * @author Lang
 */
@Component
public final class MetaSchema {

	// ~ Instance Fields =====================================
	/**
	 * Entity name of current instance. *
	 */
	@JsonProperty("name")
	private String name;
	/**
	 * Entity database table name of current instance. *
	 */
	@JsonProperty("table")
	private String table;
	/**
	 * Entity package name of current instance. *
	 */
	@JsonProperty("package")
	private String pkg;
	/**
	 * Entity fullName (package + name） of current instance. *
	 */
	@JsonProperty("fullname")
	private String fullName;

	// ~ Constructors ========================================

	/**
	 * For Jackson
	 */
	public MetaSchema() {
		// For Jackson serialization
	}

	/**
	 * Public constrcutor to create the schema
	 *
	 * @param table
	 * @param pkg
	 * @param name
	 */
	public MetaSchema(final String table, final String pkg, final String name) {
		this.table = table;
		this.name = name;
		this.pkg = pkg;
		this.fullName = this.pkg + "." + this.name;
	}

	// ~ Methods =============================================

	/**
	 * Get name of current instance
	 *
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of current instance
	 *
	 * @param name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * Get database table name of current instance.
	 *
	 * @return
	 */
	public String getTable() {
		return table;
	}

	/**
	 * Set database table name of current instance.
	 *
	 * @param table
	 */
	public void setTable(final String table) {
		this.table = table;
	}

	/**
	 * Get package info of current instance.
	 *
	 * @return
	 */
	public String getPkg() {
		return pkg;
	}

	/**
	 * Set package info of current instance.
	 *
	 * @param pkg
	 */
	public void setPkg(final String pkg) {
		this.pkg = pkg;
	}

	/**
	 * Get fullName of current instance.
	 *
	 * @return
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Set fullName of current instance.
	 *
	 * @param fullName
	 */
	public void setFullName(final String fullName) {
		this.fullName = fullName;
	}

	// ~ hashCode,equals,toString ============================

	/**
     *
     */
	@Override
	public int hashCode() {
		int result = hash(1, this.fullName);
		result = hash(result, this.name);
		result = hash(result, this.pkg);
		result = hash(result, this.table);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final MetaSchema that = (MetaSchema) obj;
			ret &= equal(this.fullName, that.fullName);
			ret &= equal(this.name, that.name);
			ret &= equal(this.pkg, that.pkg);
			ret &= equal(this.table, that.table);
		}
		return ret;
	}
}
