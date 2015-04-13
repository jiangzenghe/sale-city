package com.tiger.billmall.adapter.binder;

import java.lang.reflect.InvocationTargetException;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.tiger.billmall.adapter.DefaultViewBinderFactory.ViewBinderInstantiation;

/**
 * 复选按钮绑定器
 */
@SuppressWarnings("deprecation")
public class CompoundButtonBinder extends ReflectionEditorBinderSupport 
	implements OnClickListener, ViewBinderInstantiation, OnCheckedChangeListener {
	// 与绑定器关联的属性
	private String property;
	
	@Override
	public ViewBinder newInstance(View view) {
		return new CompoundButtonBinder();
	}

	/**
	 * 绑定方法
	 */
	@Override
	public void setViewValue(View view, Object bean, String property) {
		this.property = property;
		
		CompoundButton button = (CompoundButton)view;
		button.setOnClickListener(null);
		button.setOnCheckedChangeListener(null);
		button.setTag(bean);
		
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
			button.setChecked(false);
		}
		else if (value instanceof Boolean 
				|| value.getClass().equals(boolean.class)) {
			button.setChecked((Boolean)value);
		}
		else {
			button.setChecked(Boolean.parseBoolean(value.toString()));
		}
		button.setOnClickListener(this);
		button.setOnCheckedChangeListener(this);
	}

	@Override
	public void onClick(View v) {
		CompoundButton cv = (CompoundButton)v;
		try {
			if (cv.getTag() != null) {
				setValue(cv.getTag(), property, Boolean.valueOf(cv.isChecked()));
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
		
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (buttonView.getTag() == null) {
			return ;
		}
		
		try {
			setValue(buttonView.getTag(), property, isChecked);
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
		
	}

}
