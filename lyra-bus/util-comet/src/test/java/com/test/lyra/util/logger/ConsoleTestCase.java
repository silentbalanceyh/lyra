package com.test.lyra.util.logger;
import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.MapInstance.hashMap;
import static com.lyra.util.instance.SetInstance.hashSet;
import static com.lyra.util.logger.Console.console;
import static com.lyra.util.logger.Logger.trace;
import static org.junit.Assert.assertTrue;
import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.res.Symbol;
import com.lyra.util.test.AbstractTestCase;

/**
 * 测试类：com.lyra.util.logger.Console
 *
 * @author Lang
 * @see
 */
public class ConsoleTestCase extends AbstractTestCase implements LoggerConstant{
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public ConsoleTestCase(){
		super(TestClasses.CONSOLE);
		// console(null,null); Compile Error
	}
	// ~ Methods =============================================
	/**
	 * Console.console(null,Collection<?>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testConsole1(){
		setMethod(M_CONSOLE1);
		console(null,hashSet());
		failure(null);
	}

	/**
	 * Console.console(Class<?>,Collection<?>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testConsole2(){
		setMethod(M_CONSOLE1);
		console(null,arrayList());
		failure(null);
	}

	/**
	 * Console.console(Class<?>,T[])
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testConsole3(){
		setMethod(M_CONSOLE2);
		console(null, Symbol.T_STR_ARR);
		failure(null);
	}
	/**
	 * Console.console(Class<?>,Map<K,V>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testConsole4(){
		setMethod(M_CONSOLE3);
		console(null, hashMap(true));
		failure(null);
	}
	/**
	 * 
	 */
	@Test
	public void testConsole5(){
		setMethod(M_CONSOLE1);
		boolean ret = false;
		try{
			console(getClass(),hashSet());
			ret = true;
		}catch(ConstraintsViolatedException ex){
			trace(getClass(),ex);
			ret = false;
		}
		assertTrue(getPosition(),ret);
	}
	/**
	 * 
	 */
	@Test
	public void testConsole6(){
		setMethod(M_CONSOLE2);
		boolean ret = false;
		try{
			console(getClass(),hashMap(true));
			ret = true;
		}catch(ConstraintsViolatedException ex){
			trace(getClass(),ex);
			ret = false;
		}
		assertTrue(getPosition(),ret);
	}
	/**
	 * 
	 */
	@Test
	public void testConsole7(){
		setMethod(M_CONSOLE3);
		boolean ret = false;
		try{
			console(getClass(),arrayList().toArray());
			ret = true;
		}catch(ConstraintsViolatedException ex){
			trace(getClass(),ex);
			ret = false;
		}
		assertTrue(getPosition(),ret);
	}
	// ~ Private Methods =====================================
}
