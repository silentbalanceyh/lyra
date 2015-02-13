package com.test.db.conn.impl;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.db.conn.impl.AbstractDbPool;
import com.lyra.db.conn.impl.BoneCPPool;
import com.lyra.util.test.AbstractTestCase;

/**
 * 
 *
 * @author Lang
 * @see
 */
public class BoneCPTestCase extends AbstractTestCase implements DbPoolConstant{
	// ~ Constructors ========================================
	/** **/
	public BoneCPTestCase(){
		super(TestClasses.ADB_POOL);
	}
	// ~ Methods =============================================
	@Test(expected = ConstraintsViolatedException.class)
	public void testCon1(){
		final AbstractDbPool pool = new BoneCPPool(null);
	}
	// ~ hashCode,equals,toString ============================
}
