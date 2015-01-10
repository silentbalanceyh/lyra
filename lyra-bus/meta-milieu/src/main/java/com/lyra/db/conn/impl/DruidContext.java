package com.lyra.db.conn.impl;

import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.reflector.Factory.singleton;

import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;
import com.lyra.exp.sys.MemberInitException;
import com.lyra.res.Symbol;

/**
 * 
 *
 * @author Lang
 * @see
 */
final class DruidContext extends AbstractDbContext {
	// ~ Constructors ========================================
	/**
	 * Default constrcutor *
	 */
	DruidContext() {
		super();
	}

	/**
	 * Switch to another database connection pool dynamicly. *
	 */
	DruidContext(final String database) {
		super(database);
	}

	// ~ Override Methods ====================================
	/**
	 * 
	 */
	@Override
	public DruidDataSource getDataSource() {
		if (nullable(dataSource)) {
			dataSource = singleton(DruidDataSource.class);
		}
		return (DruidDataSource) dataSource;
	}

	/**
	 * Initialize JDBC properties of current context
	 */
	@Override
	protected void initJdbc() {
		if (Symbol.STR_NULL == this.databaseCategory) {
			throw new MemberInitException(getClass(),
					"databaseCategory[java.lang.String]");
		} else {
			this.getDataSource().setDriverClassName(
					this.loader.getString(this.databaseCategory
							+ ".jdbc.driver"));
			this.getDataSource().setUrl(
					this.loader.getString(this.databaseCategory + ".jdbc.url"));
			this.getDataSource().setUsername(
					this.loader.getString(this.databaseCategory
							+ ".jdbc.username"));
			this.getDataSource().setPassword(
					this.loader.getString(this.databaseCategory
							+ ".jdbc.password"));
		}
	}

	/**
	 * Initialize addtional properties of current context's connection pool.
	 */
	@Override
	protected void initAddtional() {
		this.getDataSource().setInitialSize(
				this.loader.getInt("druid.initial.size"));
		this.getDataSource().setMinIdle(this.loader.getInt("druid.min.idle"));
		this.getDataSource().setMaxActive(
				this.loader.getInt("druid.max.active"));
		this.getDataSource().setMaxWait(this.loader.getLong("druid.max.wait"));
		this.getDataSource().setTimeBetweenEvictionRunsMillis(
				this.loader.getLong("druid.time.between.eviction.runs"));
		this.getDataSource().setMinEvictableIdleTimeMillis(
				this.loader.getLong("druid.min.evictable.idle.time"));

		this.getDataSource().setValidationQuery(
				this.loader.getString("druid.validation.query"));
		this.getDataSource().setTestWhileIdle(
				this.loader.getBoolean("druid.test.while.idle"));
		this.getDataSource().setTestOnBorrow(
				this.loader.getBoolean("druid.test.on.borrow"));
		this.getDataSource().setTestOnReturn(
				this.loader.getBoolean("druid.test.on.return"));

		this.getDataSource().setPoolPreparedStatements(
				this.loader.getBoolean("druid.ps.cache.enabled"));
		this.getDataSource().setMaxPoolPreparedStatementPerConnectionSize(
				this.loader.getInt("druid.perpared.statement"));

		this.getDataSource().setRemoveAbandoned(
				this.loader.getBoolean("druid.abandoned"));
		this.getDataSource().setRemoveAbandonedTimeoutMillis(
				this.loader.getLong("druid.abandoned.timeout"));
		this.getDataSource().setLogAbandoned(
				this.loader.getBoolean("druid.abandoned.log"));
		try {
			this.getDataSource().setFilters(
					this.loader.getString("druid.filters"));
		} catch (SQLException ex) {
			ex.printStackTrace(); // NOPMD
		}
	}
}
