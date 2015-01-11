package com.test.lyra.util.converter;

import static com.lyra.util.converter.ConditionalConverter.ifToBoolean;
import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.SetInstance.hashSet;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;

/**
 * 单测方法：com.lyra.util.converter.ConditionalConverter.ifToBoolean(T,Collection<T>
 * );
 * 
 * @author Lang
 * @see
 */
public class Conditional2TestCase extends AbstractTestCase implements
		ConverterConstant {
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public Conditional2TestCase() {
		super(TestClasses.CONDITIONAL);
		setMethod(M_IFTOBOOLEAN2);
		// 第二个实参为null会编译错
		// final boolean retValue = ifToBoolean(null,null); Compiler Error
		// final boolean retValue = ifToBoolean("ITEM2",null); Compiler Error
	}

	// ~ Methods =============================================
	/**
	 * ConditionalConverter.ifToBoolean(null,Collection<String>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testIfToBoolean1() {
		final boolean retValue = ifToBoolean(null, getList());
		failure(retValue);
	}

	/**
	 * ConditionalConverter.ifToBoolean("ITEM3",getList())
	 */
	@Test
	public void testIfToBoolean3() {
		final boolean retValue = ifToBoolean("ITEM3", getList());
		assertTrue(getPosition(), retValue);
	}

	/**
	 * ConditionalConverter.ifToBoolean("ITEM32",getList())
	 */
	@Test
	public void testIfToBoolean4() {
		final boolean retValue = ifToBoolean("ITEM32", getList());
		assertFalse(getPosition(), retValue);
	}

	/**
	 * ConditionalConverter.ifToBoolean(null,Collection<String>)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testIfToBoolean5() {
		final boolean retValue = ifToBoolean(null, getSet());
		failure(retValue);
	}

	/**
	 * ConditionalConverter.ifToBoolean("ITEM3",getSet())
	 */
	@Test
	public void testIfToBoolean7() {
		final boolean retValue = ifToBoolean("ITEM8", getSet());
		assertTrue(getPosition(), retValue);
	}

	/**
	 * ConditionalConverter.ifToBoolean("ITEM37",getSet())
	 */
	@Test
	public void testIfToBoolean8() {
		final boolean retValue = ifToBoolean("ITEM37", getSet());
		assertFalse(getPosition(), retValue);
	}

	// ~ Private Methods =====================================
	/**
	 * 获取一个HashSet
	 * 
	 * @return
	 */
	private Set<String> getSet() {
		final List<String> inList = getList();
		return hashSet(inList);
	}

	/**
	 * 获取一个List
	 * 
	 * @return
	 */
	private List<String> getList() {
		final List<String> retList = arrayList();
		retList.add("ITEM1");
		retList.add("ITEM2");
		retList.add("ITEM3");
		retList.add("ITEM4");
		retList.add("ITEM6");
		retList.add("ITEM6");
		retList.add("ITEM7");
		retList.add("ITEM8");
		retList.add("ITEM1");
		retList.add("ITEM0");
		retList.add("ITEM3");
		retList.add("ITEM10");
		return retList;
	}
}
