package com.lyra.db.conn.impl;

import static com.lyra.res.DatabaseConfig.pool;
import static com.lyra.util.Instance.singleton;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;
import net.sf.oval.guard.PostValidateThis;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.h2.jdbc.JdbcSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyra.db.conn.MetadataConn;
import com.lyra.db.pool.AbstractDbPool;
import com.lyra.db.pool.BoneCPPool;
import com.lyra.meta.database.Metadata;

/**
 * 
 *
 * @author Lang
 * @see
 */
@Guarded
public class MetadataConnImpl implements MetadataConn {
	// ~ Static Fields =======================================
	/** **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MetadataConnImpl.class);
	// ~ Instance Fields =====================================
	/**
	 * 获取的数据库访问连接池
	 */
	@NotNull
	private transient final AbstractDbPool dbPool = singleton(pool());
	/**
	 * 数据库H2的访问连接池
	 */
	@NotNull
	private transient final AbstractDbPool h2Pool;
	/**
	 * 获取当前数据库的元数据
	 */
	@NotNull
	private transient Metadata metadata;

	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	/**
	 * 默认构造函数
	 */
	@PostValidateThis
	public MetadataConnImpl() {
		// 元数据连接池初始化
		try (final Connection conn = dbPool.getJdbc().getDataSource()
				.getConnection()) {
			final DatabaseMetaData sqlMeta = conn.getMetaData();
			/**
			 * 因为metadata和runner在try块中，所以不能设置成final修饰，否则编译会无法通过
			 */
			metadata = new Metadata(sqlMeta, dbPool.getCategory());
		} catch (SQLException ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("[E] SQLException happen.", ex);
			}
		}
		// H2 元数据
		h2Pool = new BoneCPPool("H2");
	}

	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	/**
	 * 获取元数据信息
	 */
	@Override
	@NotNull
	public Metadata getMetadata() {
		return this.metadata;
	}

	/**
	 * 加载SQL文件
	 */
	@Override
	public boolean loadSqlFile(@NotNull final InputStream in) {
		boolean ret = false;
		try (final Connection conn = this.dbPool.getJdbc().getDataSource()
				.getConnection()) {
			this.runScript(conn, in);
			// 默认日志级别输出SQL语句是DEBUG级别，只要不是级别则不会输出
			ret = true;
		} catch (SQLException ex) {
			ret = false;
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(
						"[E] SQLException happen. loadSqlFile(String) -> ", ex);
			}
		}
		return ret;
	}

	/**
	 * 初始化元数据
	 */
	@Override
	public boolean initMeta(@NotNull final InputStream in) {
		boolean ret = false;
		try (final Connection conn = this.h2Pool.getJdbc().getDataSource()
				.getConnection()) {
			this.runScript(conn, in);
			ret = true;
		} catch (SQLException ex) {
			ret = false;
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("[E] H2 SQLException happen. initMeta() -> ", ex);
			}
		}
		return ret;
	}

	// ~ Methods =============================================
	// ~ Private Methods =====================================

	private void runScript(final Connection conn, final InputStream in)
			throws SQLException {
		final ScriptRunner runner = new ScriptRunner(conn);
		final Reader sqlReader = new InputStreamReader(in);
		runner.setSendFullScript(true);
		runner.runScript(sqlReader);
		runner.closeConnection();
	}
	// ~ hashCode,equals,toString ============================
}
