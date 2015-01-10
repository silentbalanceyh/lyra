package com.lyra.mod.def;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.stereotype.Component;

import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;

/**
 * @author Lang
 * @package com.lyra.mod.def
 * @name OrderSchema
 * @class com.lyra.mod.def.OrderSchema
 * @date 2014年11月20日 上午3:23:27
 * @see
 */
@Component
public final class OrderSchema {

	// ~ Instance Fields =====================================
	/**
	 * Column order sequence. *
	 */
	@JsonProperty("order")
	private int order;
	/**
	 * Order Mode: DESC & ASC *
	 */
	@JsonProperty("mode")
	private String mode;
	/**
	 * Order mapping column name. *
	 */
	@JsonProperty("column")
	private String column;

	// ~ Constructors ========================================

	/**
	 * For Jackson
	 */
	public OrderSchema() {
		// For Jackson serialization
	}

	/**
	 * @param order
	 * @param mode
	 * @param column
	 */
	public OrderSchema(final int order, final String mode, final String column) {
		this.order = order;
		this.mode = mode;
		this.column = column;
	}

	// ~ Methods =============================================

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(final int order) {
		this.order = order;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(final String mode) {
		this.mode = mode;
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
		int result = hash(1, this.column);
		result = hash(result, this.mode);
		result = hash(result, this.order);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final OrderSchema that = (OrderSchema) obj;
			ret &= equal(this.column, that.column);
			ret &= equal(this.mode, that.mode);
			ret &= equal(this.order, that.order);
		}
		return ret;
	}
}
