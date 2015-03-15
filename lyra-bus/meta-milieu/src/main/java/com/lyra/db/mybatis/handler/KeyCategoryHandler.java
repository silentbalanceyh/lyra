package com.lyra.db.mybatis.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.lyra.mod.sys.SystemEnum.KeyCategory;

/**
 * KeyModel中的category对应的Enum Handler
 *
 * @author Lang
 * @see
 */
public class KeyCategoryHandler extends BaseTypeHandler<KeyCategory>{	// NOPMD
	// ~ Static Fields =======================================
	// ~ Instance Fields =====================================
	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	/** **/
	@Override
	public void setNonNullParameter(
			final PreparedStatement pstmt, 
			final int colIndex,
			final KeyCategory parameter, 
			final JdbcType jdbcType) throws SQLException {
		pstmt.setString(colIndex, parameter.toString());
	}
	/** **/
	@Override
	public KeyCategory getNullableResult(
			final ResultSet retSet, 
			final String columnName)
			throws SQLException {
		final String value = retSet.getString(columnName);
		KeyCategory retValue;
		if(retSet.wasNull()){
			retValue = null;	// NOPMD
		}else{
			retValue = KeyCategory.valueOf(value);
		}
		return retValue;
	}
	/** **/
	@Override
	public KeyCategory getNullableResult(
			final ResultSet retSet, 
			final int columnIndex)
			throws SQLException {
		final String value = retSet.getString(columnIndex);
		KeyCategory retValue;
		if(retSet.wasNull()){
			retValue = null; 	// NOPMD
		}else{
			retValue = KeyCategory.valueOf(value);
		}
		return retValue;
	}
	/** **/
	@Override
	public KeyCategory getNullableResult(
			final CallableStatement cstmt, 
			final int columnIndex)
			throws SQLException {
		final String value = cstmt.getString(columnIndex);
		KeyCategory retValue;
		if(cstmt.wasNull()){
			retValue = null;	// NOPMD
		}else{
			retValue = KeyCategory.valueOf(value);
		}
		return retValue;
	}
	// ~ Get/Set =============================================
	// ~ Methods =============================================
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
