package com.test.lyra.meta;

import com.lyra.exception.AbstractSchemaException;
import com.lyra.meta.Context;
import com.lyra.meta.builder.MetaDirector;
import com.lyra.meta.json.exp.*;
import com.lyra.res.Constants;
import com.lyra.res.Resources;

import org.junit.Test;

import static com.lyra.util.logger.Logger.info;
import static com.lyra.util.reflector.Factory.instance;

/**
 * Schema validation testing
 *
 * @author Lang
 * @package com.test.lyra.meta.test
 * @name SchemaExpTestCase
 * @class com.test.lyra.meta.test.SchemaExpTestCase
 * @date Nov 25, 2014 1:22:00 AM
 * @see
 */
public class SchemaExpTestCase {

	private Context getContext(final String modelName) {
		info(getClass(),
				"[TEST] Model meta-data deploying ...... ( Model Name = "
						+ modelName + " )");
		return instance(Resources.getContextClass(), modelName);
	}

	/**
	 * Error 10001
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingKeyDefException.class)
	public void test10001() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test10001");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingKeyDefException();
		}
	}

	/**
	 * Error 10002
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingMetaDefException.class)
	public void test10002() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test10002");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingMetaDefException();
		}
	}

	/**
	 * Error 10003
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingFieldDefException.class)
	public void test10003() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test10003");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingFieldDefException();
		}
	}

	/**
	 * Error 10004
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingPrimaryKeyDefException.class)
	public void test10004() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test10004");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingPrimaryKeyDefException();
		}
	}

	/**
	 * Error 10005
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingMetaAttrException.class)
	public void test100051() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100051");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingMetaAttrException("NoSQL");
		}
	}

	/**
	 * Error 10005
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingMetaAttrException.class)
	public void test100052() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100052");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingMetaAttrException("NoSQL");
		}
	}

	/**
	 * Error 10005
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingMetaAttrException.class)
	public void test100053() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100053");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingMetaAttrException("NoSQL");
		}
	}

	/**
	 * Error 10005
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingMetaAttrException.class)
	public void test100054() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100054");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingMetaAttrException("NoSQL");
		}
	}

	/**
	 * Error 10005
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingFieldAttrException.class)
	public void test100061() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100061");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingFieldAttrException(0);
		}
	}

	/**
	 * Error 10005
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingFieldAttrException.class)
	public void test100062() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100062");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingFieldAttrException(0);
		}
	}

	/**
	 * Error 10007
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingPolicyDefException.class)
	public void test10007() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test10007");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingPolicyDefException();
		}
	}

	/**
	 * Error 10008
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = MissingPrimaryKeyPolicyException.class)
	public void test10008() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test10008");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new MissingPrimaryKeyPolicyException("NoSQL-PKey");
		}
	}

	// ConflictPolicyMoreThanTwoException

	/**
	 * Error 10009
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = ConflictPolicyMoreThanTwoException.class)
	public void test10009() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test10009");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new ConflictPolicyMoreThanTwoException("NoSQL-10009");
		}
	}

	/**
	 * Error 10010
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = ConflictPolicyNMMatchingException.class)
	public void test100101() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100101");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new ConflictPolicyNMMatchingException("NoSQL-100101");
		}
	}

	/**
	 * Error 10010
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = ConflictPolicyNMMatchingException.class)
	public void test100102() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100102");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new ConflictPolicyNMMatchingException("NoSQL-100102");
		}
	}

	/**
	 * Error 10010
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = ConflictPolicyNMMatchingException.class)
	public void test100103() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100103");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new ConflictPolicyNMMatchingException("NoSQL-100103");
		}
	}

	/**
	 * Error 10011
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = ConflictPolicyMMatchingException.class)
	public void test10011() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test10011");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new ConflictPolicyMMatchingException();
		}
	}

	// ConflictPolicyPkTypeMatchingException

	/**
	 * Error 10012
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = ConflictPolicyPkTypeMatchingException.class)
	public void test100121() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100121");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new ConflictPolicyPkTypeMatchingException();
		}
	}

	/**
	 * Error 10012
	 *
	 * @throws AbstractSchemaException
	 */
	@Test(expected = ConflictPolicyPkTypeMatchingException.class)
	public void test100122() throws AbstractSchemaException {
		if (Constants.DM_SQL.equals(Resources.DB_MODE)) {
			final Context ctx = this.getContext("test100122");
			// Avoid to get return value
			throw ((MetaDirector) instance(MetaDirector.class, ctx)).getError();
		} else {
			throw new ConflictPolicyPkTypeMatchingException();
		}
	}
}
