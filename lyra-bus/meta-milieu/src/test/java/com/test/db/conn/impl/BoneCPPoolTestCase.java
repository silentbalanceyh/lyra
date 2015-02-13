package com.test.db.conn.impl;

import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.reflector.Factory.instance;
import static org.junit.Assert.assertNotNull;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.db.conn.impl.AbstractDbPool;
import com.lyra.util.test.AbstractTestCase;

/**
 * 
 *
 * @author Lang
 * @see
 */
public class BoneCPPoolTestCase extends AbstractTestCase implements DbPoolConstant{
	// ~ Constructors ========================================
	/** **/
	public BoneCPPoolTestCase(){
		super(TestClasses.ADB_POOL);
		// instance("xxx",null); Compiler High Level Warning
	}
	// ~ Methods =============================================
	/** **/
	@Test(expected = ConstraintsViolatedException.class)
	public void testCon1(){
		setMethod("Constructor.");
		final AbstractDbPool pool = instance(TestClasses.BONE_POOL,"  ");
		failure(pool);
	}
	/** **/
	@Test(expected = ConstraintsViolatedException.class)
	public void testCon2(){
		setMethod("Constructor.");
		final AbstractDbPool pool = instance(TestClasses.BONE_POOL,"");
		failure(pool);
	}
	/** **/
	@Test
	public void testCon3(){
		setMethod("Constructor.");
		final AbstractDbPool pool = instance(TestClasses.BONE_POOL);
		assertNotNull(getPosition(),pool);
	}
	/** **/
	@Test
	public void testGetJdbc(){
		setMethod("getJdbc()");
		final AbstractDbPool pool = instance(TestClasses.BONE_POOL);
		if(!nullable(pool)){
			assertNotNull(getPosition(),pool.getJdbc());
		}
	}
	
	// ~ hashCode,equals,toString ============================
}
