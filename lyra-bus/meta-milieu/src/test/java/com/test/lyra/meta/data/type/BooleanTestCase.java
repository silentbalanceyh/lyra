package com.test.lyra.meta.data.type;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.lyra.meta.DataType;
import com.lyra.meta.data.type.BooleanType;

public class BooleanTestCase {
	// ~ Static Fields =======================================
	// ~ Instance Fields =====================================
	// ~ Static Block ========================================
	// ~ Static Methods ======================================
	// ~ Constructors ========================================
	// ~ Abstract Methods ====================================
	// ~ Override Methods ====================================
	// ~ Methods =============================================
	/** **/
	@Test
	public void testBoolean1(){
		final BooleanType data = new BooleanType();
		assertEquals("[T] BooleanType -> false", false, data.getValue());
	}
	
	@Test
	public void testBoolean2(){
		final BooleanType data = new BooleanType(true);
		assertEquals("[T] BooleanType -> true", true, data.getValue());
	}
	
	@Test
	public void testBoolean3(){
		final BooleanType data = new BooleanType("true");
		assertEquals("[T] BooleanType -> true", true, data.getValue());
	}
	
	@Test
	public void testBoolean4(){
		final BooleanType data = new BooleanType();
		data.setValue(true);
		assertEquals("[T] BooleanType -> true", true, data.getValue());
	}
	
	@Test
	public void testBoolean5(){
		final BooleanType data = new BooleanType();
		assertEquals("[T] test getType", Boolean.class, data.getType());
	}
	
	@Test
	public void testBoolean6(){
		final BooleanType data = new BooleanType();
		assertEquals("[T] test getDataType", DataType.BOOLEAN, data.getDataType());
	}
	
	@Test
	public void testBoolean7(){
		final BooleanType data = new BooleanType();
		data.setValue("true");
		assertEquals("[T] test override steValue", true, data.getValue());
	}
	
	@Test
	public void testBoolean8(){
		final BooleanType data1 = new BooleanType();
		final BooleanType data2 = new BooleanType();
		assertEquals("[T] test toString", data1.toString(), data2.toString());
	}
	
	@Test
	public void testBoolean9(){
		final BooleanType data1 = new BooleanType();
		final BooleanType data2 = new BooleanType();
		assertEquals("[T] test hashCode", data1.hashCode(), data2.hashCode());
	}
	
	@SuppressWarnings("null")
	@Test
	public void testBoolean10(){
		final BooleanType data = null;
	//	assertEquals("[T] test hashCode null", );

	}
	
	@Test
	public void testBoolean11(){
		final BooleanType data1 = new BooleanType();
		final boolean ret = data1.equals(data1);
		assertEquals("[T] test equals obj", ret);
	}
	@Test
	public void testBoolean12(){
		final BooleanType data1 = new BooleanType();	
		final boolean ret = data1.equals(null);
		assertEquals("[T] test equals null", ret);
	}
	@Test
	public void testBoolean13(){
		final BooleanType data1 = new BooleanType();
		final boolean ret = data1.equals("");
		assertEquals("[T] test equals getClass", ret);
	}
	@Test
	public void testBoolean14(){
		final BooleanType data1 = new BooleanType();
		final BooleanType data2 = new BooleanType();
		final boolean ret = data1.equals(data2);
		assertEquals("[T] test equals other", ret);
	}
	@Test
	public void testBoolean15(){
	//	final BooleanType data1 = new BooleanType();
		final BooleanType data = null;
		@SuppressWarnings("null")
		final boolean ret = data.equals(null);
		assertEquals("[T] test equals other", ret);
	}
	
	
	
	
	// ~ Private Methods =====================================
	// ~ hashCode,equals,toString ============================
}
