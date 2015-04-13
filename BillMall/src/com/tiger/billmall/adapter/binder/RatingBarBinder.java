package com.tiger.billmall.adapter.binder;

import java.lang.reflect.InvocationTargetException;

import android.view.View;
import android.widget.RatingBar;
import android.widget.RatingBar.OnRatingBarChangeListener;

import com.tiger.billmall.adapter.DefaultViewBinderFactory.ViewBinderInstantiation;

/**
 * 评分器绑定器
 */
@SuppressWarnings("deprecation")
public class RatingBarBinder extends ReflectionEditorBinderSupport implements
	ViewBinderInstantiation, OnRatingBarChangeListener {
	// 与绑定器关联的属性
	private String property;
	// 变化事件
	private OnRatingBarChangeListener originally;
	
	@Override
	public void onRatingChanged(RatingBar ratingBar, float rating,
			boolean fromUser) {
		try {
			if(ratingBar.getTag() != null){
				setValue(ratingBar.getTag(), property, Float.valueOf(rating));
			}
		} catch (SecurityException e) {
			throw new IllegalAccessError(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new IllegalAccessError(e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new IllegalAccessError(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IllegalAccessError(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new IllegalAccessError(e.getMessage());
		}
		if (originally != null) {
			originally.onRatingChanged(ratingBar, rating, fromUser);
		}
	}

	/**
	 * 绑定方法
	 */
	@Override
	public void setViewValue(View view, Object bean, String property) {
		this.property = property;
		RatingBar ratingBar = (RatingBar)view;
		
		Object value = null;
		try {
			value = getValue(bean, property);
		} catch (SecurityException e) {
			throw new IllegalAccessError(e.getMessage());
		} catch (IllegalArgumentException e) {
			throw new IllegalAccessError(e.getMessage());
		} catch (NoSuchMethodException e) {
			throw new IllegalAccessError(e.getMessage());
		} catch (IllegalAccessException e) {
			throw new IllegalAccessError(e.getMessage());
		} catch (InvocationTargetException e) {
			throw new IllegalAccessError(e.getMessage());
		}
		if (value == null) {
			ratingBar.setRating(0);
		}
		else {
			ratingBar.setRating(Float.parseFloat(value.toString()));
		}
		
		if (ratingBar.getOnRatingBarChangeListener() != null &&
				!ratingBar.getOnRatingBarChangeListener().equals(this)) {
			originally = ratingBar.getOnRatingBarChangeListener();
		}
		ratingBar.setTag(bean);
		ratingBar.setOnRatingBarChangeListener(this);
	}

	@Override
	public ViewBinder newInstance(View view) {
		return new RatingBarBinder();
	}

}
