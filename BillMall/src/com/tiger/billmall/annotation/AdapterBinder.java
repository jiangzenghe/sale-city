package com.tiger.billmall.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tiger.billmall.adapter.binder.ViewBinder;



/**
 * 定义绑定适配器的注解
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AdapterBinder {
	String resName();
	Class<? extends ViewBinder> type() default ViewBinder.class;
}