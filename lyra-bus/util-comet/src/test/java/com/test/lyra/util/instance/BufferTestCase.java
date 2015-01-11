package com.test.lyra.util.instance;

import static com.lyra.util.instance.BufferInstance.buffer;
import static com.lyra.util.instance.BufferInstance.builder;
import static org.junit.Assert.assertNotNull;

import com.lyra.util.test.AbstractTestCase;

import net.sf.oval.exception.ConstraintsViolatedException;

import org.junit.Test;

/**
 * 测试类：com.lyra.util.instance.BufferInstance
 *
 * @author Lang
 * @see
 */
public class BufferTestCase extends AbstractTestCase implements
		InstanceConstant {
	// ~ Constructors ========================================

	/**
     * 
     */
	public BufferTestCase() {
		super(TestClasses.BUFFER);
	}

	// ~ Methods =============================================

	/**
	 * BufferInstance.builder(wrong_size)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testBuilder1() {
		setMethod(M_BUILDER1);
		final StringBuilder builder = builder(-1);
		failure(builder);
	}
	
	/**
	 * BufferInstance.builder(right_size)
	 */
	@Test
	public void testBuilder2() {
		setMethod(M_BUILDER1);
		final StringBuilder builder = builder(15);
		assertNotNull(getPosition(),builder);
	}

	/**
	 * BufferInstance.builder(null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testBuilder3() {
		setMethod(M_BUILDER2);
		final StringBuilder builder = builder(null);
		failure(builder);
	}
	/**
	 * BufferInstance.builder(String)
	 */
	@Test
	public void testBuilder4() {
		setMethod(M_BUILDER1);
		final StringBuilder builder = builder("Builder");
		assertNotNull(getPosition(),builder);
	}
	/**
	 * BufferInstance.builder()
	 */
	@Test
	public void testBuilder5() {
		setMethod(M_BUILDER3);
		final StringBuilder builder = builder();
		assertNotNull(getPosition(),builder);
	}

	/**
	 * BufferInstance.buffer(wrong_size)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testBuffer1() {
		setMethod(M_BUFFER1);
		final StringBuffer buffer = buffer(-1);
		failure(buffer);
	}
	/**
	 * BufferInstance.buffer(right_size)
	 */
	@Test
	public void testBuffer2() {
		setMethod(M_BUFFER1);
		final StringBuffer buffer = buffer(15);
		assertNotNull(getPosition(),buffer);
	}
	/**
	 * BufferInstance.buffer(null)
	 */
	@Test(expected = ConstraintsViolatedException.class)
	public void testBuffer3() {
		setMethod(M_BUFFER2);
		final StringBuffer buffer = buffer(null);
		failure(buffer);
	}
	/**
	 * BufferInstance.buffer(right_size)
	 */
	@Test
	public void testBuffer4() {
		setMethod(M_BUFFER2);
		final StringBuffer buffer = buffer("Buffer");
		assertNotNull(getPosition(),buffer);
	}
	/**
	 * BufferInstance.buffer()
	 */
	@Test
	public void testBuffer5() {
		setMethod(M_BUFFER3);
		final StringBuffer buffer = buffer();
		assertNotNull(getPosition(),buffer);
	}
	// ~ hashCode,equals,toString ============================
}
