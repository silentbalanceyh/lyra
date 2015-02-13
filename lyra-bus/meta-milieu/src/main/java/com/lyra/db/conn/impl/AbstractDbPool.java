package com.lyra.db.conn.impl;

import static com.lyra.util.reflector.Factory.instance;
import static com.lyra.util.reflector.Factory.singleton;

import javax.sql.DataSource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import net.sf.oval.guard.PostValidateThis;
import net.sf.oval.guard.PreValidateThis;

import org.springframework.jdbc.core.JdbcTemplate;

import com.lyra.res.Resources;
import com.lyra.util.prop.PropLoader;

/**
 * Jdbc连接池类
 *
 * @author Lang
 * @see
 */
@Guarded
public abstract class AbstractDbPool {
	// ~ Static Fields =======================================
	/**
	 * SQL数据源实例，维持一个实例
	 */
	@NotNull
	protected static DataSource dataSource;
	// ~ Instance Fields =====================================
	/**
	 * Spring JDBC Template实例
	 */
	@NotNull
	protected transient JdbcTemplate jdbc;
	/**
	 * 数据库的种类：
	 * MSSQL,PGSQL,ORACLE,MYSQL,MONGODB
	 */
	@NotNull
	@NotEmpty
	@NotBlank
	protected transient String category;
	/**
	 * 资源加载器
	 */
	@NotNull
	protected transient PropLoader loader = singleton(PropLoader.class,
			getClass(), Resources.DB_CFG_FILE);
	// ~ Constructors ========================================
	/**
	 * 默认构造函数
	 */
	@PostValidateThis
	protected AbstractDbPool(){
		this(Resources.DB_CATEGORY);
	}
	/**
	 * 构造函数
	 * @param category
	 */
	@PostValidateThis
	protected AbstractDbPool(@NotNull @NotEmpty @NotBlank final String category) {
		synchronized(getClass()){
			System.out.println(category);
			this.category = category;
			// 初始化属性
			this.initJdbc();
			this.initPool();
			// 初始化Template
			this.jdbc = instance(JdbcTemplate.class,dataSource);
			System.out.print(dataSource);
		}
	}
	// ~ Abstract Methods ====================================
	/**
	 * JDBC属性设置
	 */
	protected abstract void initJdbc();
	/**
	 * 连接池属性设置
	 */
	protected abstract void initPool();
	/**
	 * 返回数据源DataSource的引用
	 * @return
	 */
	protected abstract DataSource getDataSource();
	// ~ Methods =============================================
	/**
	 * 返回JdbcTemplate引用
	 * @return
	 */
	@PreValidateThis
	public JdbcTemplate getJdbc(){
		return this.jdbc;
	}
	/**
	 * 返回资源加载器，子类使用
	 * @return
	 */
	@PreValidateThis
	protected PropLoader getLoader(){
		return this.loader;
	}
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
