package com.lyra.mod.def;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.internal.Comparer.equal;
import static com.lyra.util.internal.Comparer.hash;
import static com.lyra.util.internal.Validator.nullable;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lyra.res.Symbol;

/**
 * For an entity, this schema is defined for all key names: 1. Primary Keys (
 * pKeys ) 2. Foreign Keys ( fKeys ) 3. Unique Keys ( uKeys ) 4. Not Null Keys (
 * nKeys )
 *
 * @author Lang
 */
@Component
public final class KeyNames {

	// ~ Instance Fields =====================================
	/**
	 * Primary Key names *
	 */
	@JsonProperty("pKeys")
	private transient List<String> pKeys;
	/**
	 * Unique Key names *
	 */
	@JsonProperty("uKeys")
	private transient List<String> uKeys;
	/**
	 * Foreign Key names *
	 */
	@JsonProperty("fKeys")
	private transient List<String> fKeys;
	/**
	 * Not Null Key names *
	 */
	@JsonProperty("nKeys")
	private transient List<String> nKeys;

	// ~ Constructors ========================================

	/**
	 * For Jackson
	 */
	public KeyNames() {
		// For Jackson serialization
	}

	/**
	 * Variable arguments constructor
	 *
	 * @param keyLists
	 */
	@SafeVarargs
	public KeyNames(final List<String>... keyLists) {
		if (keyLists.length > Symbol.I_RANGE[3]) {
			this.nKeys = keyLists[3];
		}
		if (keyLists.length > Symbol.I_RANGE[2]) {
			this.fKeys = keyLists[2];
		}
		if (keyLists.length > Symbol.I_RANGE[1]) {
			this.uKeys = keyLists[1];
		}
		if (keyLists.length > Symbol.I_RANGE[0]) {
			this.pKeys = keyLists[0];
		}
	}

	// ~ Methods =============================================

	/**
	 * Get primary key list
	 *
	 * @return
	 */
	public List<String> getpKeys() {
		return nullable(pKeys) ? newList() : pKeys;
	}

	/**
	 * Set primary key list
	 *
	 * @param pKeys
	 */
	public void setpKeys(final List<String> pKeys) {
		this.pKeys = pKeys;
	}

	/**
	 * Get unique key list
	 *
	 * @return
	 */
	public List<String> getuKeys() {
		return nullable(uKeys) ? newList() : uKeys;
	}

	/**
	 * Set unique key list
	 *
	 * @param uKeys
	 */
	public void setuKeys(final List<String> uKeys) {
		this.uKeys = uKeys;
	}

	/**
	 * Get foreign key list
	 *
	 * @return
	 */
	public List<String> getfKeys() {
		return nullable(fKeys) ? newList() : fKeys;
	}

	/**
	 * Set foreign key list
	 *
	 * @param fKeys
	 */
	public void setfKeys(final List<String> fKeys) {
		this.fKeys = fKeys;
	}

	/**
	 * Get not Null key list
	 *
	 * @return
	 */
	public List<String> getnKeys() {
		return nullable(nKeys) ? newList() : nKeys;
	}

	/**
	 * Set not Null key list.
	 *
	 * @param nKeys
	 */
	public void setnKeys(final List<String> nKeys) {
		this.nKeys = nKeys;
	}

	// ~ Private Methods =====================================

	private List<String> newList() {
		final List<String> retList = arrayList();
		return retList;
	}

	// ~ hashCode,equals,toString ============================

	/**
     *
     */
	@Override
	public int hashCode() {
		int result = hash(1, this.fKeys);
		result = hash(result, this.nKeys);
		result = hash(result, this.uKeys);
		result = hash(result, this.pKeys);
		return result;
	}

	/**
     *
     */
	@Override
	public boolean equals(final Object obj) {
		boolean ret = true & equal(this, obj, getClass(), true);
		if (ret) {
			final KeyNames that = (KeyNames) obj;
			ret &= equal(this.fKeys, that.fKeys);
			ret &= equal(this.nKeys, that.nKeys);
			ret &= equal(this.uKeys, that.uKeys);
			ret &= equal(this.pKeys, that.pKeys);
		}
		return ret;
	}
}
