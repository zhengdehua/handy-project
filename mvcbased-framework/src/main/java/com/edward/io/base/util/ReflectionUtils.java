package com.edward.io.base.util;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.lang.reflect.*;
import java.util.*;

public class ReflectionUtils {
	private static Logger logger = LoggerFactory
			.getLogger(ReflectionUtils.class);

	public static Object getFieldValue(Object object, String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null) {
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");
		}

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	public static void setFieldValue(Object object, String fieldName,
			Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null)
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");

		makeAccessible(field);
		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	public static Object invokeMethod(Object object, String methodName,
			Class<?>[] parameterTypes, Object[] parameters)
			throws InvocationTargetException {
		Method method = getDeclaredMethod(object, methodName, parameterTypes);
		if (method == null) {
			throw new IllegalArgumentException("Could not find method ["
					+ methodName + "] on target [" + object + "]");
		}

		method.setAccessible(true);
		try {
			return method.invoke(object, parameters);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		return null;
	}

	protected static Field getDeclaredField(Object object, String fieldName) {
		Assert.notNull(object, "object不能为空");
		Assert.hasText(fieldName, "fieldName");

		Class superClass = object.getClass();

		try {
			return getDeclaredField(superClass, fieldName);

		} catch (Exception e) {

			logger.error("Could not find field [" + fieldName + "] on target ["
					+ superClass + "]");

			throw new RuntimeException("Could not find field [" + fieldName
					+ "] on target [" + superClass + "]", e);

		}

	}

	protected static Field getDeclaredField(Class clazz, String fieldName) {
		Assert.notNull(clazz, "object不能为空");
		Assert.hasText(fieldName, "fieldName");

		Class superClass = clazz;

		try {
			return superClass.getDeclaredField(fieldName);

		} catch (NoSuchFieldException e) {

			logger.error("Could not find field [" + fieldName + "] on target ["
					+ superClass + "]");

			if (superClass == Object.class) {
				throw new RuntimeException("Could not find field [" + fieldName
						+ "] on target [" + superClass + "]");
			}

			superClass = superClass.getSuperclass();

			try {

				return getDeclaredField(superClass, fieldName);

			} catch (Exception e1) {

				throw new RuntimeException("Could not find field [" + fieldName
						+ "] on target [" + superClass + "]", e1);
			}

		}

	}

	protected static void makeAccessible(Field field) {
		if ((!(Modifier.isPublic(field.getModifiers())))
				|| (!(Modifier.isPublic(field.getDeclaringClass()
						.getModifiers()))))
			field.setAccessible(true);
	}

	protected static Method getDeclaredMethod(Object object, String methodName,
			Class<?>[] parameterTypes) {
		Assert.notNull(object, "object不能为空");

		Class superClass = object.getClass();
		try {
			return superClass.getDeclaredMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			do {
				e.printStackTrace();

				superClass = superClass.getSuperclass();
			} while (superClass != Object.class);
		}

		return null;
	}

	public static <T> Class<T> getSuperClassGenricType(Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	public static Class getSuperClassGenricType(Class clazz, int index) {
		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if ((index >= params.length) || (index < 0)) {
			logger.warn("Index: " + index + ", Size of "
					+ clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName()
					+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}

		return ((Class) params[index]);
	}

	public static List fetchElementPropertyToList(Collection collection,
			String propertyName) {
		List list = new ArrayList();
		try {
			for (Iterator localIterator = collection.iterator(); localIterator
					.hasNext();) {
				Object obj = localIterator.next();
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (IllegalAccessException e) {
			convertToUncheckedException(e);
		} catch (InvocationTargetException e) {
			convertToUncheckedException(e);
		} catch (NoSuchMethodException e) {
			convertToUncheckedException(e);
		}

		return list;
	}

	public static String fetchElementPropertyToString(Collection collection,
			String propertyName, String separator) {
		List list = fetchElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	public static Object convertValue(Object value, Class<?> toType) {
		DateConverter dc = new DateConverter();
		dc.setUseLocaleFormat(true);
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		ConvertUtils.register(dc, Date.class);
		return ConvertUtils.convert(value, toType);
	}

	public static IllegalArgumentException convertToUncheckedException(
			Exception e) {
		if ((e instanceof IllegalAccessException)
				|| (e instanceof IllegalArgumentException)
				|| (e instanceof NoSuchMethodException))
			return new IllegalArgumentException("Refelction Exception.", e);

		return new IllegalArgumentException(e);
	}
}