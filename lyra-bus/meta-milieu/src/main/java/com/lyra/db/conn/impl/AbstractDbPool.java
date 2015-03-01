package com.lyra.db.conn.impl;

import static com.lyra.util.Instance.instance;
import static com.lyra.util.Instance.singleton;

import javax.sql.DataSource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import net.sf.oval.guard.PostValidateThis;
import net.sf.oval.guard.Pre;

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
	 * SQL数据源实例，维持一个实例：OVal不支持静态变量field的约束
	 */
	protected static DataSource dataSource;
	// ~ Instance Fields =====================================
	/**
	 * Spring JDBC Template实例：jdbc这个变量没有执行Pre的@NotNull约束，因为构造函数调用了子类中的两个方法，
	 * 而这个变量使用了@NotNull过后会导致PreValidateThis的构造失败
	 */
	protected transient JdbcTemplate jdbc;
	/**
	 * 数据库的种类： MSSQL,PGSQL,ORACLE,MYSQL,MONGODB
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
			getClass(),Resources.DB_CFG_FILE);

	// ~ Constructors ========================================
	/**
	 * 默认构造函数
	 */
	@PostValidateThis
	protected AbstractDbPool() {
		this(Resources.DB_CATEGORY);
	}

	/**
	 * 构造函数
	 * 
	 * @param category
	 */
	@PostValidateThis
	protected AbstractDbPool(@NotNull @NotEmpty @NotBlank final String category) {
		synchronized (getClass()) {
			this.category = category;
			// 初始化jdbc基础属性
			this.initJdbc();

			this.initPool();
			// 初始化Template
			this.jdbc = instance(JdbcTemplate.class, dataSource);
		}
	}

	// ~ Static Block ========================================
	/**
	 * 如果初始化失败会导致子类 initJdbc() initPool() 无法成功调用
	 */
	static {
		dataSource = singleton(Resources.DB_DATA_SOURCE);
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
	 * 
	 * @return
	 */
	protected abstract DataSource getDataSource();

	// ~ Methods =============================================
	/**
	 * 返回JdbcTemplate引用
	 * 
	 * @return
	 */
	@Pre(expr = "_this.jdbc != null", lang = "groovy")
	public JdbcTemplate getJdbc() {
		return this.jdbc;
	}

	/**
	 * 返回资源加载器，子类使用
	 * 
	 * @return
	 */
	@Pre(expr = "_this.loader != null", lang = "groovy")
	protected PropLoader getLoader() {
		return this.loader;
	}
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
