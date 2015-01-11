package com.test.lyra.util.calculator;
import static com.lyra.util.calculator.IndexCalculator.index;
import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.instance.SetInstance.hashSet;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

import com.lyra.util.test.AbstractTestCase;

/**
 * 测试类：com.lyra.util.calculator.IndexCalculator
 *
 * @author Lang
 * @see
 */
public class IndexTestCase extends AbstractTestCase implements CalculatorConstant{
	// ~ Constructors ========================================
	/**
	 *  
	 */
	public IndexTestCase(){
		super(TestClasses.INDEX_CALCULATOR);
		// final int retIndex = index(null,null); Compile Error
	}
	// ~ Methods =============================================
	/**
	 * IndexCalculator.index(List<T>/Set<T>/T[], null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testIndex1(){
		setMethod(M_INDEX1);
		final int retIndex = index(getSet(),null);
		failure(retIndex);
	}
	
	/**
	 * IndexCalculator.index(List<T>/Set<T>/T[], T)
	 */
	@Test
	public void testIndex2(){
		setMethod(M_INDEX3);
		final int retIndex = index(getList(),"B");
		assertEquals(getPosition(),1,retIndex);
	}
	
	/**
	 * IndexCalculator.index(List<T>/Set<T>/T[], T)
	 */
	@Test
	public void testIndex3(){
		setMethod(M_INDEX3);
		final int retIndex = index(getList(),"G");
		assertEquals(getPosition(),-1,retIndex);
	}
	
	/**
	 * IndexCalculator.index(List<T>/Set<T>/T[], T)
	 */
	@Test
	public void testIndex4(){
		setMethod(M_INDEX2);
		final int retIndex = index(getArray(),"D");
		assertEquals(getPosition(),3,retIndex);
	}
	
	/**
	 * IndexCalculator.index(List<T>/Set<T>/T[], T)
	 */
	@Test
	public void testIndex5(){
		setMethod(M_INDEX2);
		final int retIndex = index(getArray(),"H");
		assertEquals(getPosition(),-1,retIndex);
	}
	/**
	 * IndexCalculator.index(List<T>/Set<T>/T[], T)
	 */
	@Test
	public void testIndex6(){
		setMethod(M_INDEX1);
		final Set<String> set = getSet();
		int expected = -1;
		final Iterator<String> setIt = set.iterator();
		int index = 0;
		while(setIt.hasNext()){
			if("E".equals(setIt.next())){
				expected = index;
				break;
			}
			index++;
		}
		final int actual = index(set,"E");
		assertEquals(getPosition(),expected,actual);
	}
	/**
	 * IndexCalculator.index(List<T>/Set<T>/T[], T)
	 */
	@Test
	public void testIndex7(){
		setMethod(M_INDEX1);
		final int retIndex = index(getSet(),"H");
		assertEquals(getPosition(),-1,retIndex);
	}
	
	// ~ Private Methods =====================================
	private Set<String> getSet(){
		return hashSet(getArray());
	}
	
	private List<String> getList(){
		return arrayList(getArray());
	}
	
	private String[] getArray(){
		return new String[]{"A","B","C","D","E","F"};
	}
}
