package com.test.db.mybatis;

import java.util.Locale;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lyra.db.mybatis.KeyMapper;
import com.lyra.db.mybatis.SessionManager;
import com.lyra.mod.sys.KeyModel;
import com.lyra.mod.sys.SystemEnum.KeyCategory;

public class KeyMapperTestCase {
	// ~ Static Fields =======================================
	/** **/
	private static final Logger LOGGER = LoggerFactory
			.getLogger(KeyMapperTestCase.class);
	// ~ Instance Fields =====================================
	/** **/
	private transient final SqlSession session;

	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================

	public KeyMapperTestCase() {
		session = SessionManager.getSession();
	}

	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	// ~ Get/Set =============================================
	// ~ Methods =============================================
	/** **/
	@Test
	public void testInsert() {
		/**
		 * 插入数据的代码
		 */
		if (null != this.session) {
			final KeyMapper mapper = this.session.getMapper(KeyMapper.class);
			final KeyModel key = new KeyModel();
			key.setName("KEY_NAME");
			key.setColumns("[NAME,COLUMN1.COLUMN2]");
			key.setCategory(KeyCategory.ForeignKey);
			key.setMulti(true);
			key.setUniqueId(UUID.randomUUID().toString()
					.toUpperCase(Locale.getDefault()));
			mapper.insertKey(key);
			this.session.commit();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("[TD] Inserted record successfully : "
						+ key.toString());
			}
			this.deleteById(key.getUniqueId());
		}
	}
	// ~ Private Methods =====================================
	
	private void deleteById(final String uniqueId){
		if(null != this.session){
			final KeyMapper mapper = this.session.getMapper(KeyMapper.class);
			mapper.deleteById(uniqueId);
			this.session.commit();
			if( LOGGER.isDebugEnabled()){
				LOGGER.debug("[TD] Deleted record by K_ID : " + uniqueId);
			}
		}
	}
	// ~ hashCode,equals,toString ============================
}
