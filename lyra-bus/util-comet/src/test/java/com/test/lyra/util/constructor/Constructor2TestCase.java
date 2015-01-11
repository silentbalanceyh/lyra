package com.test.lyra.util.constructor;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.lyra.util.converter.ConditionalConverter;
import com.lyra.util.converter.StringConverter;
import com.lyra.util.instance.ArrayInstance;
import com.lyra.util.instance.BufferInstance;
import com.lyra.util.instance.MapInstance;
import com.lyra.util.instance.NullInstance;
import com.lyra.util.instance.SetInstance;
import com.lyra.util.instance.SortedSetInstance;
import com.lyra.util.reflector.Factory;
import com.lyra.util.reflector.Types;
import com.lyra.util.test.AbstractTestCase;

/**
 * 测试构造函数
 *
 * @author Lang
 * @see
 */
public class Constructor2TestCase extends AbstractTestCase{
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public Constructor2TestCase(){
		super("Constructor");
	}
	// ~ Methods =============================================
	/**
	 * com.lyra.util.converter.ConditionalConverter
	 */
	@Test
	public void testConstructor11(){
		final ConditionalConverter instance = newInstance(ConditionalConverter.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.converter.StringConverter
	 */
	@Test
	public void testConstructor12(){
		final StringConverter instance = newInstance(StringConverter.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.instance.ArrayInstance
	 */
	@Test
	public void testConstructor13(){
		final ArrayInstance instance = newInstance(ArrayInstance.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.instance.BufferInstance
	 */
	@Test
	public void testConstructor14(){
		final BufferInstance instance = newInstance(BufferInstance.class.getName());
		assertNotNull(getPosition(),instance);
	}
	
	/**
	 * com.lyra.util.instance.MapInstance
	 */
	@Test
	public void testConstructor15(){
		final MapInstance instance = newInstance(MapInstance.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.instance.SetInstance
	 */
	@Test
	public void testConstructor16(){
		final SetInstance instance = newInstance(SetInstance.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.instance.SortedSetInstance
	 */
	@Test
	public void testConstructor17(){
		final SortedSetInstance instance = newInstance(SortedSetInstance.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.instance.NullInstance
	 */
	@Test
	public void testConstructor18(){
		final NullInstance instance = newInstance(NullInstance.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.reflector.Factory
	 */
	@Test
	public void testConstructor19(){
		final Factory instance = newInstance(Factory.class.getName());
		assertNotNull(getPosition(),instance);
	}
	/**
	 * com.lyra.util.reflector.Types
	 */
	@Test
	public void testConstructor20(){
		final Types instance = newInstance(Types.class.getName());
		assertNotNull(getPosition(),instance);
	}
	// ~ Private Methods =====================================
}
