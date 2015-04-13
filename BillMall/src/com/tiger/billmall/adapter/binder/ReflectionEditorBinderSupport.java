package com.tiger.billmall.adapter.binder;

import java.lang.reflect.InvocationTargetException;

import com.tiger.billmall.util.Util;


/**
 * 反射机制ViewBinder接口实现支持类，这个抽象类用于支持通过反射机制建立JavaBean对象属性与视图组的绑定，
 * 这个类提供了通过反射读取、设置JavaBean对象属性性的支持方法。
 */
public abstract class ReflectionEditorBinderSupport implements ViewBinder {
	/**
	 * 设置指定的bean对象的指定propertyName属性的值。
	 * @param bean bean对象
	 * @param propertyName propertyName属性名
	 * @param value 属性值
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected void setValue(Object bean, String propertyName, Object value) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Util.setBeanPropertyValue(bean, propertyName, value);
	}

	/**
	 * 返回指定bean参数指定的对象的参数propertyName指定的属性的值。
	 * @param bean JavaBean对象引用
	 * @param propertyName 属性名
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	protected Object getValue(Object bean, String propertyName) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return Util.getBeanPropertyValue(bean, propertyName);
	}
	
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
