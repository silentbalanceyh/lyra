package com.test.lyra.util.assist;
/**
 * 
 *
 * @author Lang
 * @see
 */
public class InstanceModel implements IInstanceModel{
	// ~ Instance Fields =====================================
	/** **/
	private transient String name;
	/** **/
	private transient String email;
	// ~ Constructors ========================================
	/**
	 * 
	 */
	public InstanceModel(){
		this("Lang Yu");
	}
	/**
	 * 
	 * @param name
	 */
	public InstanceModel(final String name){
		this(name,"lang.yu@hp.com");
	}
	/**
	 * 
	 * @param name
	 * @param email
	 */
	public InstanceModel(final String name, final String email){
		this.name = name;
		this.email = email;
	}
	// ~ Methods =============================================
	/**
	 * 
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	/**
	 * 
	 * @return
	 */
	public String getEmail(){
		return this.email;
	}
	// ~ Private Methods =====================================
	
	// ~ hashCode,equals,toString ============================
	/**
	 * 
	 */
	@Override
	public String toString() {
		return "InstanceModel [name=" + name + ", email=" + email + "]";
	}
}
