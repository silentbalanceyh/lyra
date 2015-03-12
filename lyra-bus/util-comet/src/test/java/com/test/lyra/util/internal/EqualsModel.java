package com.test.lyra.util.internal;

import java.util.Date;

import static com.lyra.util.internal.Comparer.equal;

/**
 * 测试equals和hashCode辅助类
 * 
 * @author Lang
 * @see
 */
class EqualsModel {
	// ~ Instance Fields =====================================
	/**
	 * name *
	 */
	private String name;
	/**
	 * age *
	 */
	private int age;
	/**
	 * birthday *
	 */
	private Date birthday;

	// ~ Constructors ========================================

	/**
	 * Public constructor *
	 */
	public EqualsModel() {
		// Testing
	}

	/**
	 * Public constructor *
	 */
	public EqualsModel(final String name, final int age, final Date birthday) {
		this.name = name;
		this.age = age;
		this.birthday = birthday;
	}

	// ~ Methods =============================================

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age
	 *            the age to set
	 */
	public void setAge(final int age) {
		this.age = age;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday
	 *            the birthday to set
	 */
	public void setBirthday(final Date birthday) {
		this.birthday = birthday;
	}

	// ~ hashCode,equals,toString ============================

	/** **/
	@Override
	public int hashCode() {
		int result = 1;// NOPMD
		result = 31 * result + age;
		result = 31 * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = 31 * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/** **/
	@Override
	public boolean equals(final Object obj) {
		boolean retValue = true & equal(this, obj, getClass(), true);
		// (1), (2), (3)
		if (retValue) {
			// (4) Fields Comparing, lookup false situation
			final EqualsModel that = (EqualsModel) obj;
			retValue &= equal(age, that.age);
			retValue &= equal(name, that.name);
			retValue &= equal(birthday, that.birthday);
		}
		return retValue;
	}
}
