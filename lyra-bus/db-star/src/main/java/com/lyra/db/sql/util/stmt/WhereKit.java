package com.lyra.db.sql.util.stmt;

import com.lyra.db.sql.util.Criteria;
import com.lyra.db.sql.util.Restrictions;
import com.lyra.meta.Context;
import com.lyra.meta.Record;
import com.lyra.mod.def.FieldSchema;
import com.lyra.res.Constants;

import java.util.Set;
import java.util.Map;

import static com.lyra.util.instance.BufferInstance.builder;
import static com.lyra.util.reflector.Factory.instance;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.util.stmt
 * @name WhereKit
 * @class com.test.lyra.db.sql.util.stmt.WhereKit
 * @date Dec 2, 2014 3:00:21 PM
 * @see
 */
public final class WhereKit {
	// ~ Constructors ========================================
	private WhereKit() {
	}

	// ~ Static Methods ======================================

	/**
	 * Prepare for primary key where clause ( Prepared ) *
	 */
	public static String prepPkWhere(final Context context) {
		final String policy = context.getPolicy();
		final StringBuilder whereClause = builder();
		final Criteria criteria = instance(Criteria.class); // Criteria.instance();
		if (policy.trim().equals(Constants.KP_MULTI)) {
			final Set<FieldSchema> schemata = context.getPKs();
			for (final FieldSchema schema : schemata) {
				criteria.add(Restrictions.eq(schema.getColumnName()));
			}
		} else {
			final FieldSchema schema = context.getPK();
			criteria.add(Restrictions.eq(schema.getColumnName()));
		}
		whereClause.append(criteria);
		return whereClause.toString();
	}

	/**
	 * @param context
	 * @param indexMap
	 * @return
	 */
	public static String prepPkWhere(final Context context,
			final Map<String, Integer> indexMap) {
		final String policy = context.getPolicy();
		final StringBuilder whereClause = builder();
		final Criteria criteria = instance(Criteria.class);
		if (policy.trim().equals(Constants.KP_MULTI)) {
			final Set<FieldSchema> schemata = context.getPKs();
			for (final FieldSchema schema : schemata) {
				criteria.add(Restrictions.eq(schema.getColumnName()));
				indexMap.put(schema.getColumnName(), indexMap.size() + 1);
			}
		} else {
			final FieldSchema schema = context.getPK();
			criteria.add(Restrictions.eq(schema.getColumnName()));
			indexMap.put(schema.getColumnName(), indexMap.size() + 1);
		}
		whereClause.append(criteria);
		return whereClause.toString();
	}

	/**
	 * Prepare for primary key where clause ( Direct ) *
	 */
	public static String prepPkWhere(final Context context, final Record record) {
		final String policy = context.getPolicy();
		final StringBuilder whereClause = builder();
		final Criteria criteria = instance(Criteria.class);
		if (policy.trim().equals(Constants.KP_MULTI)) {
			final Set<FieldSchema> schemata = context.getPKs();
			for (final FieldSchema schema : schemata) {
				criteria.add(Restrictions.eq(schema.getColumnName(), record
						.get(schema.getName()).getValue()));
			}
		} else {
			final FieldSchema schema = context.getPK();
			criteria.add(Restrictions.eq(schema.getColumnName(),
					record.get(schema.getName()).getValue()));
		}
		whereClause.append(criteria);
		return whereClause.toString();
	}

	// Where

	/**
	 * Prepare for one field of (_EQ) where clause *
	 */
	public static String prepWhere(final FieldSchema schema) {
		final StringBuilder whereClause = builder();
		final Criteria criteria = instance(Criteria.class);
		whereClause
				.append(criteria.add(Restrictions.eq(schema.getColumnName())));
		return whereClause.toString();
	}
}
