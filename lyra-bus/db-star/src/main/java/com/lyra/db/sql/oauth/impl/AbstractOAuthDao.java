package com.lyra.db.sql.oauth.impl;

import com.lyra.db.conn.JdbcContext;
import com.lyra.db.sql.OAuthReader;
import com.lyra.db.sql.OAuthWriter;
import com.lyra.mod.oauth.AuthenticationCode;
import com.lyra.res.Resources;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.reflector.Factory.instance;
import static com.lyra.util.reflector.Factory.singleton;
import static com.lyra.util.logger.Logger.trace;

/**
 * @author Lang
 * @package com.test.lyra.db.sql.oauth.impl
 * @name AbstractOAuthDao
 * @class com.test.lyra.db.sql.oauth.impl.AbstractOAuthDao
 * @date Dec 8, 2014 9:42:39 PM
 * @see
 */
public abstract class AbstractOAuthDao implements OAuthReader, OAuthWriter {

	// ~ Static Fields =======================================
	/**
	 * Insert sql for oauth code table *
	 */
	protected static final String INSERT_CODE_SQL = "INSERT INTO OAU_CODE (CODE,AUTHENTICATION) VALUES (?,?)";
	/**
	 * Select sql for oauth code table *
	 */
	protected static final String SELECT_CODE_SQL = "SELECT CODE,AUTHENTICATION FROM OAU_CODE WHERE CODE=?";
	/**
	 * Delete sql for oauth code table *
	 */
	protected static final String DELETE_CODE_SQL = "DELETE FROM OAU_CODE WHERE CODE=?";

	// ~ Instance Fields =====================================
	/**
	 * Data context which could provide JDBC environment. *
	 */
	private transient final JdbcContext dbCtx;

	// ~ Constructors ========================================

	/**
	 * Protected constructor and it's used in sub-class only. *
	 */
	protected AbstractOAuthDao() {
		this.dbCtx = singleton(Resources.getConnectionPool());
	}

	// ~ Override Methods ====================================

	/**
	 * Create new authentication code and insert into database.
	 */
	@Override
	public AuthenticationCode add(AuthenticationCode codeObj) {
		Assert.notNull(codeObj,
				"[ASSERT] Input authentication code should not be null.");
		final int ret = this.getJdbc().update(
				INSERT_CODE_SQL,
				new Object[] { codeObj.getCode(),
						new SqlLobValue(codeObj.getAuthentication()) },
				new int[] { Types.VARCHAR, Types.BLOB });
		if (ret <= 0) {
			codeObj = nullObj();
		}
		return codeObj;
	}

	/**
     *
     */
	@Override
	public AuthenticationCode remove(final String code) {
		Assert.notNull(code, "[ASSERT] Input code value should not be null.");
		AuthenticationCode retCode;
		try {
			retCode = this.getJdbc().select(SELECT_CODE_SQL,
					new RowMapper<AuthenticationCode>() {
						@Override
						public AuthenticationCode mapRow(
								final ResultSet retSet, final int rowNum)
								throws SQLException {
							final AuthenticationCode tempCode = new AuthenticationCode();
							tempCode.setCode(retSet.getString("CODE"));
							tempCode.setAuthentication(retSet
									.getBytes("AUTHENTICATION"));
							return tempCode;
						}
					}, code);
		} catch (EmptyResultDataAccessException ex) {
			retCode = nullObj();
			trace(getClass(), ex);
		}
		if (!nullable(retCode)) {
			this.getJdbc().update(DELETE_CODE_SQL, code);
		}
		return retCode;
	}

	// ~ Methods =============================================

	/**
	 * Public method to init oauth dao *
	 */
	public static AbstractOAuthDao init() {
		final String clazz = Resources.getODaoClass(Resources.DB_CATEGORY);
		return instance(clazz);
	}

	/**
	 * @return
	 */
	protected JdbcContext getJdbc() {
		return this.dbCtx;
	}
}
