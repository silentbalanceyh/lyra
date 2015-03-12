package com.lyra.meta;

import java.util.Set;

/**
 * Record interface
 *
 * @author Lang
 */
public interface Record {
	/**
	 * Set field value ( Value<?> ) of current record
	 *
	 * @param name
	 * @param value
	 */
	void set(final String name, final Value<?> value);

	/**
	 * Get field value of current record
	 *
	 * @param name
	 * @return
	 */
	Value<?> get(final String name);

	/**
	 * Get name of current record, the name should be model name;
	 *
	 * @return
	 */
	String name();

	/**
	 * @return
	 */
	Set<String> fieldNames();

	/**
	 * Set field value ( String ) of current record
	 *
	 * @param name
	 * @param value
	 */
	void set(final String name, final String value);

	/**
	 * Set field value ( Boolean ) of current record
	 *
	 * @param name
	 * @param value
	 */
	void set(final String name, final boolean value);

	/**
	 * Set field value ( Interger ) of current record
	 *
	 * @param name
	 * @param value
	 */
	void set(final String name, final int value);
}
