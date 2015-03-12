package com.test.lyra.util.assist;

import java.util.Comparator;

/**
 * 
 *
 * @author Lang
 * @see
 */
public class ModelComparator implements Comparator<InstanceModel>{ // NOPMD
	// ~ Override Methods ====================================
	/**
	 * Override method of comparator
	 * @param model1
	 * @param model2
	 * @return
	 */
	@Override
	public int compare(final InstanceModel model1, final InstanceModel model2) {
		int ret = -1;
		if(model1.getName().equals(model2.getName())){
			ret = 1;
		}
		return ret;
	}
	// ~ hashCode,equals,toString ============================
}
