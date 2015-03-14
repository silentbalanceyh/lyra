package com.lyra.mod.sys;

import java.io.Serializable;

import com.lyra.mod.sys.SystemEnum.KeyCategory;

/**
 * 对应表SYS_KEYS
 *
 * @author Lang
 * @see
 */
public class KeyModel implements Serializable{	// NOPMD
	// ~ Static Fields =======================================
	/**
	 * 
	 */
	private static final long serialVersionUID = -2090226950871844055L;
	// ~ Instance Fields =====================================
	/** K_ID: Keys表的主键 **/
	private String uniqueId;
	/** S_NAME: Keys表的系统键的名字 **/
	private String name;
	/** S_CATEGORY：键类型 **/
	private KeyCategory category;
	/** S_COLUMNS：当前键中包含的列信息 **/
	private String columns;
	/** S_IS_MULTI：是否跨字段 **/
	private boolean multi;
	
	/** R_META_ID：外键约束，关联SYS_META **/
	private String refMetaId;
	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	// ~ Get/Set =============================================

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId() {
		return uniqueId;
	}

	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(final String uniqueId) {
		this.uniqueId = uniqueId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the category
	 */
	public KeyCategory getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(final KeyCategory category) {
		this.category = category;
	}

	/**
	 * @return the columns
	 */
	public String getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(final String columns) {
		this.columns = columns;
	}

	/**
	 * @return the multi
	 */
	public boolean isMulti() {
		return multi;
	}

	/**
	 * @param multi the multi to set
	 */
	public void setMulti(final boolean multi) {
		this.multi = multi;
	}

	/**
	 * @return the refMetaId
	 */
	public String getRefMetaId() {
		return refMetaId;
	}

	/**
	 * @param refMetaId the refMetaId to set
	 */
	public void setRefMetaId(final String refMetaId) {
		this.refMetaId = refMetaId;
	}
	// ~ Methods =============================================
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
	/** **/
	@Override
	public String toString() {
		return "KeyModel [uniqueId=" + uniqueId + ", name=" + name
				+ ", category=" + category + ", columns=" + columns
				+ ", multi=" + multi + ", refMetaId=" + refMetaId + "]";
	}

	/** **/
	@Override
	public int hashCode() {
		final int prime = 31;	// NOPMD
		int result = 1;
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result + (multi ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((refMetaId == null) ? 0 : refMetaId.hashCode());
		result = prime * result
				+ ((uniqueId == null) ? 0 : uniqueId.hashCode());
		return result;
	}

	/** **/
	@Override
	public boolean equals(final Object obj) {	// NOPMD
		if (this == obj)
			return true;	// NOPMD
		if (obj == null)
			return false;	// NOPMD
		if (getClass() != obj.getClass())
			return false;	// NOPMD
		final KeyModel other = (KeyModel) obj;
		if (category != other.category)
			return false;	// NOPMD
		if (multi != other.multi)
			return false;	// NOPMD
		if (name == null) {
			if (other.name != null)
				return false;	// NOPMD
		} else if (!name.equals(other.name))
			return false;	// NOPMD
		if (refMetaId == null) {
			if (other.refMetaId != null)
				return false;	// NOPMD
		} else if (!refMetaId.equals(other.refMetaId))
			return false;	// NOPMD
		if (uniqueId == null) {
			if (other.uniqueId != null)
				return false;	// NOPMD
		} else if (!uniqueId.equals(other.uniqueId))
			return false;	// NOPMD
		return true;
	}
	
}
