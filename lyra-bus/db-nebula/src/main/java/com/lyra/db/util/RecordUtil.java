package com.lyra.db.util;

import com.lyra.meta.Record;
import com.lyra.meta.Value;

import java.util.Set;

/**
 * @author Lang
 * @package com.test.lyra.db.test.util
 * @name RecordUtil
 * @class com.test.lyra.db.test.util.RecordUtil
 * @date Dec 5, 2014 2:32:14 AM
 * @see
 */
public final class RecordUtil {

	// ~ Constructors ========================================
	private RecordUtil() {
	}

	// ~ Static Methods ======================================

	/**
	 * Compare two records to see whether thay are same.
	 *
	 * @param lRecord
	 * @param rRecord
	 * @return
	 */
	public static boolean isEqual(final Record lRecord, final Record rRecord) {
		// Compare fields
		boolean ret = true;
		final Set<String> lFields = lRecord.fieldNames();
		final Set<String> rFields = rRecord.fieldNames();
		for (final String lField : lFields) {
			if (!rFields.contains(lField)) {
				ret = false;
				break;
			}
		}
		// Compare value;
		if (ret) {
			for (final String field : lFields) {
				final Value<?> lValue = lRecord.get(field);
				final Value<?> rValue = rRecord.get(field);
				if (!lValue.getValue().toString()
						.equals(rValue.getValue().toString())) {
					ret = false;
					break;
				}
			}
		}
		return ret;
	}
}
