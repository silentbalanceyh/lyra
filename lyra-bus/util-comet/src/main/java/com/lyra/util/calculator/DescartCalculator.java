package com.lyra.util.calculator;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.converter.ConditionalConverter.ifToBoolean;

import java.util.List;

import com.lyra.res.Symbol;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * 使用List集合计算笛卡尔积
 *
 * @author Lang
 * @see
 */
@Guarded
public final class DescartCalculator {
	// ~ Static Methods ======================================
	/**
	 * 计算dimValue这个二维表产生的笛卡尔积
	 * 
	 * @param dimValue
	 * @return
	 */
	@NotNull
	public static <T> List<List<T>> descart(
			@NotNull final List<List<T>> dimValue) {
		final List<List<T>> retList = arrayList();
		final List<T> layer0 = dimValue.get(0);
		final List<List<T>> layer0List = toTable(layer0);
		final int dimSize = dimValue.size();
		if (Symbol.I_ONE == dimSize) {
			retList.addAll(layer0List);
		} else {
			// 获取L1
			final List<List<T>> layerXList = arrayList();
			final List<List<T>> layerXRetList = arrayList();
			// 从L1开始遍历，起始值是1
			for (int i = 1; i < dimSize; i++) {
				// 遍历第一层所有Element元素
				for (final List<T> layer0Class : layer0List) {
					if (Symbol.I_ONE == i) {
						// 如果i的值为1表示直接计算第一层和第二层值
						fillReturnList(layerXRetList, layer0Class,
								dimValue.get(i));
					} else {
						// i不为1表示遍历下一层
						for (final List<T> layerXClass : layerXList) {
							fillReturnList(layerXRetList, layerXClass,
									dimValue.get(i));
						}
					}
				}
				layerXList.addAll(layerXRetList);
				if (i < dimSize - 1) {
					layerXRetList.clear();
				}
			}
			retList.addAll(layerXRetList);
		}
		// 笛卡尔集数量不满足的移除
		for (int i = retList.size() - 1; i >= 0; i--) {
			if (retList.get(i).size() != dimSize) {
				retList.remove(i);
			}
		}
		return retList;
	}

	// ~ Constructors ========================================
	private DescartCalculator() {
	}

	// ~ Private Methods =====================================
	/*
	 * Private所有的私有方法仅仅检查Null Pointer即可，因为是内部调用
	 * 保证不为null就等同于做了最基础的防御，因为为开放所以从代码控制 输入的值
	 */
	private static <T> void fillReturnList(
			@NotNull final List<List<T>> retList,
			@NotNull final List<T> previousList,
			@NotNull final List<T> nextLayerList) {
		for (final T addedClass : nextLayerList) {
			final List<T> tempList = arrayList();
			tempList.addAll(previousList);
			tempList.add(addedClass);
			if (!ifToBoolean(tempList, retList)) {
				retList.add(tempList);
			}
		}
	}

	@NotNull
	private static <T> List<List<T>> toTable(@NotNull final List<T> list) {
		final List<List<T>> retList = arrayList();
		for (final T element : list) {
			final List<T> elementList = arrayList();
			elementList.add(element);
			retList.add(elementList);
		}
		return retList;
	}

}
