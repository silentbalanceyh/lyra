package com.test.lyra.util.internal;

import static com.lyra.util.instance.NullInstance.nullObj;
import static com.lyra.util.logger.Logger.trace;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

import com.test.base.AbstractTestCase;

/**
 * 测试Equals和hashCode类
 * 
 * @author Lang
 * @see
 */
public class EqualsTestCase extends AbstractTestCase implements InternalConstant{
	// ~ Constructors ========================================
	/**
	 * 默认构造函数
	 */
	public EqualsTestCase() {
		super(TestClasses.EQUALS);
	}

	// ~ Methods =============================================

	/**
	 * EqualsModel.equals 自反性测试、传递性测试（同引用）
	 */
	@Test
	public void testEquals1() {
		setMethod(M_EQUALS);
		final EqualsModel user1 = new EqualsModel();
		final EqualsModel user2 = user1;
		final EqualsModel user3 = user2;
		// Symmetry
		boolean ret = user1.equals(user2) && user2.equals(user1); // NOPMD
		// Transitivity
		ret = user1.equals(user2) && user2.equals(user3) && user3.equals(user1);// NOPMD//NOPMD//NOPMD
		assertTrue(getPosition(), ret);
	}

	/**
	 * EqualsModel.equals 和null值的自反性测试（同引用）
	 */
	@Test
	public void testEquals2() {
		setMethod(M_EQUALS);
		final EqualsModel user1 = new EqualsModel();
		final EqualsModel user2 = user1;
		final EqualsModel user3 = user2;
		// Symmetry
		final boolean ret = user1.equals(nullObj()) && user2.equals(nullObj())// NOPMD
				&& user3.equals(nullObj());// NOPMD
		assertFalse(getPosition(), ret);
	}

	/**
	 * EqualsModel.equals 非同类型equals测试
	 */
	@Test
	public void testEquals3() {
		setMethod(M_EQUALS);
		final EqualsModel user1 = new EqualsModel();
		final Long user3 = Long.valueOf(12L);
		final boolean ret = user1.equals(user3);
		assertFalse(getPosition(), ret);
	}

	/**
	 * EqualsModel.equals 自反性测试、传递性测试（不同引用）
	 */
	@Test
	public void testEquals4() {
		setMethod(M_EQUALS);
		final EqualsModel user1 = getSameModel();
		final EqualsModel user2 = getSameModel();
		final EqualsModel user3 = getSameModel();
		// Symmetry
		boolean ret = user1.equals(user2) && user2.equals(user1);// NOPMD
		// Transitivity
		ret = user1.equals(user2) && user2.equals(user3) && user3.equals(user1);// NOPMD
		assertTrue(getPosition(), ret);
	}

	/**
	 * EqualsModel.equals age基本类型：状态一致性测试（不同引用）
	 */
	@Test
	public void testAgeNotEquals() {
		setMethod(M_EQUALS);
		final EqualsModel user1 = getSameModel();
		final EqualsModel user2 = getSameModel();
		final EqualsModel user3 = getSameModel();
		user3.setAge(25);// NOPMD
		// Symmetry
		boolean ret = user1.equals(user2) && user2.equals(user1);// NOPMD
		// Transitivity
		ret = user2.equals(user3) || user3.equals(user1);// NOPMD
		assertFalse(getPosition(), ret);
	}

	/**
	 * EqualsModel.equals name对象类型：状态一致性测试（不同引用）
	 */
	@Test
	public void testNameNotEquals() {
		setMethod(M_EQUALS);
		final EqualsModel user1 = getSameModel();
		final EqualsModel user2 = getSameModel();
		final EqualsModel user3 = getSameModel();
		user3.setName("Lang Yu");
		// Symmetry
		boolean ret = user1.equals(user2) && user2.equals(user1);
		// Transitivity
		ret = user2.equals(user3) || user3.equals(user1);
		assertFalse(getPosition(), ret);
	}

	/**
	 * EqualsModel.equals birthday对象类型：状态一致性测试（不同引用）
	 */
	@Test
	public void testBirthdayNotEquals() {
		setMethod(M_EQUALS);
		final EqualsModel user1 = getSameModel();
		final EqualsModel user2 = getSameModel();
		final EqualsModel user3 = getSameModel();
		user3.setBirthday(new Date());
		// Symmetry
		boolean ret = user1.equals(user2) && user2.equals(user1);// NOPMD
		// Transitivity
		ret = user2.equals(user3) || user3.equals(user1);
		assertFalse(getPosition(), ret);
	}

	/**
	 * EqualsModel.equals 改变状态的状态一致性测试（不同引用）
	 */
	@Test
	public void testChangesEquals() {
		setMethod(M_EQUALS);
		final EqualsModel user1 = getSameModel();
		final EqualsModel user2 = getSameModel();
		final EqualsModel user3 = getSameModel();
		// Symmetry
		boolean ret = user1.equals(user2) && user2.equals(user1);// NOPMD
		// Transitivity
		ret = user1.equals(user2) && user2.equals(user3) && user3.equals(user1);// NOPMD
		// Consistency
		setSameChanges(user1);
		setSameChanges(user2);
		setSameChanges(user3);
		// Symmetry
		ret = user1.equals(user2) && user2.equals(user1);// NOPMD
		// Transitivity
		ret = user1.equals(user2) && user2.equals(user3) && user3.equals(user1);
		assertTrue(getPosition(), ret);
	}

	// ~ Private Methods =====================================

	private EqualsModel getSameModel() {
		return new EqualsModel("Lang", 28, generateDate("2014-12-12 11:34:44"));
	}

	private Date generateDate(final String inputValue) {
		final SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date retDate = null;// NOPMD
		try {
			retDate = format.parse(inputValue);
		} catch (ParseException ex) {
			trace(getClass(), ex);
		}
		return retDate;
	}

	private void setSameChanges(final EqualsModel user) {
		user.setName("Lang Yu");
		user.setAge(30);
		user.setBirthday(generateDate("2014-11-12 15:33:44"));
	}
}
