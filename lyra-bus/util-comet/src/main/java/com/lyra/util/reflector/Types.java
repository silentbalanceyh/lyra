package com.lyra.util.reflector;

import static com.lyra.util.instance.ArrayInstance.arrayList;
import static com.lyra.util.internal.Validator.nullable;
import static com.lyra.util.internal.Validator.zero;

import java.util.List;

import net.sf.oval.constraint.NotNull;
import net.sf.oval.guard.Guarded;

/**
 * 和类型相关的反射类
 * 
 * @author Lang
 * @see
 */
@Guarded
public final class Types {
	// ~ Static Methods ======================================
	/**
	 * 获取输入类的所有类型列表 包括接口、父类、自身
	 * 
	 * @param clazz
	 * @return
	 */
	public static List<Class<?>> types(@NotNull final Class<?> clazz) {
		final List<Class<?>> retTypes = arrayList();
		// Current
		retTypes.add(clazz);
		// Interfaces
		retTypes.addAll(interfaces(clazz));
		// Super Classes
		retTypes.addAll(supers(clazz));
		return retTypes;
	}

	/**
	 * 获取传入类的所有接口类型，父类递归除开java.lang.Object
	 * 
	 * @param clazz
	 * @return
	 */
	@NotNull
	public static List<Class<?>> interfaces(@NotNull final Class<?> clazz) {
		final List<Class<?>> retList = arrayList();
		if (!nullable(clazz.getInterfaces()) && !zero(clazz.getInterfaces())) {
			for (final Class<?> interClass : clazz.getInterfaces()) {
				retList.add(interClass);
				retList.addAll(interfaces(interClass));
			}
		}
		// Super Class Interfaces
		if (!nullable(clazz.getSuperclass())
				&& !clazz.getSuperclass().isAssignableFrom(Object.class)) {
			retList.addAll(interfaces(clazz.getSuperclass()));
		}
		return retList;
	}

	/**
	 * 获取继承树上的父类列表，除开java.lang.Object
	 * 
	 * @param clazz
	 * @return
	 */
	@NotNull
	public static List<Class<?>> supers(@NotNull final Class<?> clazz) {
		final List<Class<?>> retList = arrayList();
		if (!nullable(clazz.getSuperclass())
				&& !clazz.getSuperclass().isAssignableFrom(Object.class)) {
			retList.add(clazz.getSuperclass());
			retList.addAll(supers(clazz.getSuperclass()));
		}
		return retList;
	}

	// ~ Constructors ========================================
	
	private Types() {
	}
	// ~ Private Methods =====================================
/*	@NotNull
	private static Class<?>[] toClass(@NotNull final Type[] types){
		final int length = types.length;
		Class<?>[] retClasses = new Class<?>[length];
		for(int i = 0; i < length; i++ ){
			if(!nullable(types[i])){
				retClasses[i] = (Class<?>)types[i];
			}
		}
		return retClasses;
	}
	*//**
	 * 获取泛型类型T
	 * 
	 * @param clazz
	 * @return
	 *//*
	public static Class<?> genericT(@NotNull final Class<?> clazz) {
		final Class<?>[] classes = genericTs(clazz);
		Class<?> retClass = nullObj();
		if (!zero(classes)) {
			retClass = classes[0];
		}
		return retClass;
	}

	*//**
	 * 获取泛型类型T..
	 * 
	 * @param clazz
	 * @return
	 *//*
	public static Class<?>[] genericTs(@NotNull final Class<?> clazz) {
		Class<?>[] retClasses = {};
		final Type genericType = clazz.getGenericSuperclass();
		if (genericType instanceof ParameterizedType) {
			final Type[] params = ((ParameterizedType) genericType)
					.getActualTypeArguments();
			retClasses = toClass(params);
		}
		return retClasses;
	}*/
}
