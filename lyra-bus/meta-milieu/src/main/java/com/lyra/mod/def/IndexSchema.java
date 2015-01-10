package com.lyra.mod.def;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.stereotype.Component;

import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

/**
 * @author Lang
 * @package com.lyra.mod.def
 * @name IndexSchema
 * @class com.lyra.mod.def.IndexSchema
 * @date Nov 24, 2014 9:52:01 PM
 * @see
 */
@Component
public final class IndexSchema {

	// ~ Instance Fields =====================================
	/**
	 * Index name for current table *
	 */
	@JsonProperty("name")
	private String name;
	/**
	 * Index category for current table SQL Server: CI, NCI
	 */
	@JsonProperty("category")
	private String category;
	/**
	 * Indexed column name of database table. *
	 */
	@JsonProperty("column")
	private String column;

	// ~ Constructors ========================================

	/**
	 * Public constructor. *
	 */
	public IndexSchema() {
		// For Jackson serialization
	}

	/**
	 * @param name
	 * @param category
	 * @param column
	 */
	public IndexSchema(final String name, final String category,
			final String column) {
		this.name = name;
		this.category = category;
		this.column = column;
	}

	// ~ Methods =============================================

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(final String category) {
		this.category = category;
	}

	/**
	 * @return the column
	 */
	public String getColumn() {
		return column;
	}

	/**
	 * @param column
	 *            the column to set
	 */
	public void setColumn(final String column) {
		this.column = column;
	}

	// ~ hashCode,equals,toString ============================

	/**
     *
     */
	@Override
	public int hashCode() {
		int result = hash(1, this.name);
		result = hash(result, this.category);
		result = hash(result, this.column);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final IndexSchema that = (IndexSchema) obj;
			ret &= equal(this.name, that.name);
			ret &= equal(this.category, that.category);
			ret &= equal(this.column, that.column);
		}
		return ret;
	}
}
