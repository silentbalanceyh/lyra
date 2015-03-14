package com.lyra.db.mybatis;

import java.io.InputStream;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.lyra.util.FileExplorer;

/**
 * Mybatis框架中的SQL Session管理器
 *
 * @author Lang
 * @see
 */
@Guarded
public final class SessionManager {
	// ~ Static Fields =======================================
	/** Session Factory 的单例 **/
	private static SqlSessionFactory sessionFactory;
	// ~ Instance Fields =====================================
	// ~ Static Block ========================================
	/** 静态初始化 **/
	static {
		/** 简单的单例实现 **/
		if (null == sessionFactory) {
			final InputStream inStream = FileExplorer
					.getFile(com.lyra.res.Resources.T_CFG_MYBATIS);
			if (null != inStream) {
				sessionFactory = new SqlSessionFactoryBuilder().build(inStream,
						com.lyra.res.Resources.T_CFG_MB_ENV);
			}
		}
	}

	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	// ~ Get/Set =============================================
	// ~ Methods =============================================
	/** 获取Session实例 **/
	@NotNull
	public static SqlSession getSession() {
		SqlSession session = null;
		if (null != sessionFactory) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	// ~ Private Methods =====================================
	private SessionManager() {
	}
	// ~ hashCode,equals,toString ============================
}
