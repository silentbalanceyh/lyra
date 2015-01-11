package com.test.lyra.util.constructor;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;

/**
 * 
 *
 * @author Lang
 * @see
 */
public class ParentFailTestCase extends AbstractTestCase{
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public ParentFailTestCase(){
		super("com.lyra.util.test.AbstractTestCase");
	}
	// ~ Methods =============================================
	/**
	 * 
	 */
	@Test
	public void testFailure(){
		setMethod("failure(Object)");
		boolean ret = false;
		try{
			failure("Test Failure");
		}catch(AssertionError error){
			ret = true;
		}
		assertTrue(getPosition(),ret);
	}
	// ~ Private Methods =====================================
}
