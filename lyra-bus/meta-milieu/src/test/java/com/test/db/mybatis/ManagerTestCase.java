package com.test.db.mybatis;

import static org.junit.Assert.assertNotNull;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyra.db.mybatis.SessionManager;
import com.test.db.conn.impl.MetadataTestCase;
/**
 * 
 *
 * @author Lang
 * @see
 */
public class ManagerTestCase {
	// ~ Static Fields =======================================
	/** **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MetadataTestCase.class);
	// ~ Instance Fields =====================================
	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	// ~ Get/Set =============================================
	// ~ Methods =============================================
	/** **/
	@Test
	public void testSessionManager(){
		final SqlSession session = SessionManager.getSession();
		assertNotNull("[T] Created session is null,",session);
	}
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
