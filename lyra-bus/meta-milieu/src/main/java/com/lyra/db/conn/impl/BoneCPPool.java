package com.lyra.db.conn.impl;

import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.reflector.Factory.singleton;

import java.util.concurrent.TimeUnit;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import net.sf.oval.guard.Pre;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * Bone CP连接池
 *
 * @author Lang
 * @see
 */
@Guarded
final class BoneCPPool extends AbstractDbPool {
	// ~ Constructors ========================================
	/**
	 * 默认构造函数
	 */
	BoneCPPool() {
		super();
	}

	/**
	 * 传入数据库种类的构造函数
	 * 
	 * @param category
	 */
	BoneCPPool(@NotNull @NotEmpty @NotBlank final String category) {
		super(category);
	}

	// ~ Override Methods ====================================
	/**
	 * 获取数据源引用
	 */
	@Override
	public BoneCPDataSource getDataSource() {
		if (null == dataSource) {
			dataSource = singleton(BoneCPDataSource.class);
		}
		return (BoneCPDataSource) dataSource;
	}

	/**
	 * JDBC基本属性
	 */
	@Override
	@Pre(expr = "_this.category != null", lang = "groovy")
	protected void initJdbc() {
		this.getDataSource().setDriverClass(
				this.getLoader().getString(this.category + ".jdbc.driver"));
		this.getDataSource().setJdbcUrl(
				this.getLoader().getString(this.category + ".jdbc.url"));
		this.getDataSource().setUsername(
				this.getLoader().getString(this.category + ".jdbc.username"));
		this.getDataSource().setPassword(
				this.getLoader().getString(this.category + ".jdbc.password"));
	}

	/**
	 * 连接池属性
	 */
	@Override
	protected void initPool() {
		this.getDataSource().setAcquireIncrement(
				this.getLoader().getInt("bonecp.acquire.increment"));
		this.getDataSource().setAcquireRetryDelay(
				this.getLoader().getLong("bonecp.acquire.retry.delay"),
				TimeUnit.MILLISECONDS);
		this.getDataSource().setIdleMaxAgeInSeconds(
				this.getLoader().getLong("bonecp.idle.max.age"));
		this.getDataSource().setIdleConnectionTestPeriodInSeconds(
				this.getLoader().getLong("bonecp.idle.connection.test.period"));
		this.getDataSource().setPartitionCount(
				this.getLoader().getInt("bonecp.partition.count"));
		this.getDataSource().setStatementsCacheSize(
				this.getLoader().getInt("bonecp.statements.cache.size"));
		this.getDataSource().setMinConnectionsPerPartition(
				this.getLoader().getInt("bonecp.min.conn.per.partition"));
		this.getDataSource().setMaxConnectionsPerPartition(
				this.getLoader().getInt("bonecp.max.conn.per.partition"));
	}
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
