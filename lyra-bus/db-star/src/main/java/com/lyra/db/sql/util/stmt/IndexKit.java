package com.lyra.db.sql.util.stmt;

import com.lyra.meta.Context;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Constants;

import java.util.Set;
import java.util.Map;

import static com.lyra.util.instance.MapInstance.hashMap;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.util.stmt
 * @name IndexKit
 * @class com.test.lyra.db.sql.util.stmt.IndexKit
 * @date Dec 2, 2014 4:05:41 PM
 * @see
 */
public final class IndexKit {
	// ~ Constructors ========================================
	private IndexKit() {
	}

	// ~ Static Methods ======================================

	/**
	 * Column Index => Field
	 *
	 * @return
	 */
	public static Map<Integer, String> prepPkIndexes(final Context context) {
		final String policy = context.getPolicy();
		final Map<Integer, String> retMap = hashMap(true);
		if (policy.trim().equals(Constants.KP_MULTI)) {
			final Set<FieldSchema> schemata = context.getPKs();
			int index = 1;
			for (final FieldSchema schema : schemata) {
				retMap.put(index, schema.getName());
				index++;
			}
		}
		return retMap;
	}
}
