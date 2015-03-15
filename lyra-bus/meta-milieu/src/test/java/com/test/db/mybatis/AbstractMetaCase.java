package com.test.db.mybatis;

import static com.lyra.util.Generator.uuid;

import org.apache.ibatis.session.SqlSession;

import com.lyra.db.mybatis.FieldMapper;
import com.lyra.db.mybatis.KeyMapper;
import com.lyra.db.mybatis.MetaMapper;
import com.lyra.db.mybatis.SessionManager;
import com.lyra.mod.sys.KeyModel;
import com.lyra.mod.sys.SystemEnum.KeyCategory;

/**
 * 
 *
 * @author Lang
 * @see
 */
public class AbstractMetaCase {
	// ~ Static Fields =======================================
	/** **/
	public static final KeyCategory[] KEY_CATEGORIES = new KeyCategory[] {
			KeyCategory.ForeignKey, KeyCategory.PrimaryKey,
			KeyCategory.UniqueKey };
	
	// ~ Instance Fields =====================================
	/** **/
	private transient final SqlSession _session;
	/** **/
	private transient final KeyMapper keyMapper;
	/** **/
	private transient final MetaMapper metaMapper;
	/** **/
	private transient final FieldMapper fieldMapper;

	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	/** **/
	public AbstractMetaCase() {
		_session = SessionManager.getSession();
		if (null == this._session) {
			keyMapper = null;
			metaMapper = null;
			fieldMapper = null;
		} else {
			keyMapper = _session.getMapper(KeyMapper.class);
			metaMapper = _session.getMapper(MetaMapper.class);
			fieldMapper = _session.getMapper(FieldMapper.class);

		}
	}

	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	// ~ Get/Set =============================================
	// ~ Methods =============================================
	/** **/
	public SqlSession session() {
		return this._session;
	}

	/**
	 * 
	 * @param uniqueId
	 * @return
	 */
	public KeyModel getKey(final String uniqueId) {
		KeyModel key;
		if (null == uniqueId) {
			key = new KeyModel();
		} else {
			key = this.keyMapper.selectById(uniqueId);
		}
		key.setName("NAME-" + uuid());
		key.setColumns("COLUMNS-" + uuid());

		return key;
	}
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
