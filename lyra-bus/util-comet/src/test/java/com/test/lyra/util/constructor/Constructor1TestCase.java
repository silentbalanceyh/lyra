package com.test.lyra.util.constructor;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lyra.res.Regex;
import com.lyra.res.Symbol;
import com.lyra.util.calculator.DescartCalculator;
import com.lyra.util.calculator.IndexCalculator;
import com.lyra.util.calculator.MapCalculator;
import com.lyra.util.calculator.SetCalculator;
import com.lyra.util.internal.Comparer;
import com.lyra.util.internal.Validator;
import com.lyra.util.logger.Console;
import com.lyra.util.logger.Logger;
import com.lyra.util.test.AbstractTestCase;

/**
 * 测试构造函数
 *
 * @author Lang
 * @see
 */
public class Constructor1TestCase extends AbstractTestCase{
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public Constructor1TestCase(){
		super("Constructor");
	}
	// ~ Methods =============================================
	/**
	 * com.lyra.util.internal.Validator
	 */
	@Test
	public void testConstructor1(){
		final Validator instance = newInstance(Validator.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.internal.Comparer
	 */
	@Test
	public void testConstructor2(){
		final Comparer instance = newInstance(Comparer.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.logger.Logger
	 */
	@Test
	public void testConstructor3(){
		final Logger instance = newInstance(Logger.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.logger.Console
	 */
	@Test
	public void testConstructor4(){
		final Console instance = newInstance(Console.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.res.Regex
	 */
	@Test
	public void testConstructor5(){
		final Regex instance = newInstance(Regex.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.res.Symbol
	 */
	@Test
	public void testConstructor6(){
		final Symbol instance = newInstance(Symbol.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.calculator.DescartCalculator
	 */
	@Test
	public void testConstructor7(){
		final DescartCalculator instance = newInstance(DescartCalculator.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.calculator.IndexCalculator
	 */
	@Test
	public void testConstructor8(){
		final IndexCalculator instance = newInstance(IndexCalculator.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.calculator.MapCalculator
	 */
	@Test
	public void testConstructor9(){
		final MapCalculator instance = newInstance(MapCalculator.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.calculator.SetCalculator
	 */
	@Test
	public void testConstructor10(){
		final SetCalculator instance = newInstance(SetCalculator.class.getName());
		assertNotNull(getPosition(),instance);
	}
	// ~ Private Methods =====================================
}
