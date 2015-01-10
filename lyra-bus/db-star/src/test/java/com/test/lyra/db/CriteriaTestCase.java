package com.test.lyra.db;

import com.lyra.db.sql.util.Criteria;
import com.lyra.db.sql.util.Restrictions;
import org.junit.Test;

import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.reflector.Factory.instance;
import static org.junit.Assert.assertEquals;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.unit
 * @name CriteriaTestCase
 * @class com.test.lyra.db.sql.unit.CriteriaTestCase
 * @date Nov 16, 2014 7:55:10 PM
 * @see
 */
public class CriteriaTestCase {

	/**
	 * Test equal ( direct ) *
	 */
	@Test
	public void testEqualDirect() {
		final Criteria expected = instance(Criteria.class);
		expected.add(Restrictions.eq("ED_AGE", 21));
		expected.add(Restrictions.eq("ED_NAME", "Lang"));
		final Criteria actValue = instance(Criteria.class);
		actValue.add(Restrictions.and(Restrictions.eq("ED_AGE", 21),
				Restrictions.eq("ED_NAME", "Lang")));
		assertEquals("<EQUAL> - 'Restrictions.eq' method. ( Direct )",
				expected.toString(), actValue.toString());
		info(getClass(), actValue.toString());
	}

	/**
	 * Test equal ( prepared ) *
	 */
	@Test
	public void testEqualPrepared() {
		final Criteria expected = instance(Criteria.class);
		expected.add(Restrictions.eq("EP_AGE"));
		expected.add(Restrictions.eq("EP_NAME"));
		final Criteria actValue = instance(Criteria.class);
		actValue.add(Restrictions.and(Restrictions.eq("EP_AGE"),
				Restrictions.eq("EP_NAME")));
		assertEquals("<EQUAL> - 'Restrictions.eq' method. ( Prepared )",
				expected.toString(), actValue.toString());
		info(getClass(), actValue.toString());
	}

	/**
	 * Test not equal ( direct ) *
	 */
	@Test
	public void testNotEqualDirect() {
		final Criteria expected = instance(Criteria.class);
		expected.add(Restrictions.ne("NE_AGE", 21));
		expected.add(Restrictions.ne("NE_NAME", "Lang"));
		final Criteria actValue = instance(Criteria.class);
		actValue.add(Restrictions.and(Restrictions.ne("NE_AGE", 21),
				Restrictions.ne("NE_NAME", "Lang")));
		assertEquals("<EQUAL> - 'Restrictions.ne' method. ( Direct )",
				expected.toString(), actValue.toString());
		info(getClass(), actValue.toString());
	}
}
