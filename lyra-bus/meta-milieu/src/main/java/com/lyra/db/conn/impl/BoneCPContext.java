package com.lyra.db.conn.impl;

import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.logger.Console.console;
import static com.lyra.util.reflector.Factory.singleton;

import java.util.concurrent.TimeUnit;

import com.jolbox.bonecp.BoneCPDataSource;
import com.lyra.exp.sys.MemberInitException;
import com.lyra.res.Symbol;

/**
 * Implementation class for BoneCP connection pool
 *
 * @author Lang
 */
final class BoneCPContext extends AbstractDbContext {

	// ~ Constructors ========================================
	/**
	 * Default constrcutor *
	 */
	BoneCPContext() {
		super();
	}

	/**
	 * Switch to another database connection pool dynamicly. *
	 */
	BoneCPContext(final String database) {
		super(database);
	}

	// ~ Override Methods ====================================
	/**
	 * 
	 */
	@Override
	public BoneCPDataSource getDataSource() {
		if (nullable(dataSource)) {
			dataSource = singleton(BoneCPDataSource.class);
		}
		return (BoneCPDataSource) dataSource;
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
			this.getDataSource().setDriverClass(
					this.loader.getString(this.databaseCategory
							+ ".jdbc.driver"));
			this.getDataSource().setJdbcUrl(
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
		this.getDataSource().setAcquireIncrement(
				this.loader.getInt("bonecp.acquire.increment"));
		this.getDataSource().setAcquireRetryDelay(
				this.loader.getLong("bonecp.acquire.retry.delay"),
				TimeUnit.MILLISECONDS);
		this.getDataSource().setIdleMaxAgeInSeconds(
				this.loader.getLong("bonecp.idle.max.age"));
		this.getDataSource().setIdleConnectionTestPeriodInSeconds(
				this.loader.getLong("bonecp.idle.connection.test.period"));
		this.getDataSource().setPartitionCount(
				this.loader.getInt("bonecp.partition.count"));
		this.getDataSource().setStatementsCacheSize(
				this.loader.getInt("bonecp.statements.cache.size"));
		this.getDataSource().setMinConnectionsPerPartition(
				this.loader.getInt("bonecp.min.conn.per.partition"));
		this.getDataSource().setMaxConnectionsPerPartition(
				this.loader.getInt("bonecp.max.conn.per.partition"));
		console(getClass(),this.loader.getProp());
	}
}
