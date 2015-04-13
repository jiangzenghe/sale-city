package com.tiger.billmall.adapter.binder;

import java.lang.reflect.InvocationTargetException;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

import com.tiger.billmall.adapter.DefaultViewBinderFactory.ViewBinderInstantiation;
/**
 * 输入框绑定器
 */
@SuppressWarnings("deprecation")
public class EditTextBinder extends ReflectionEditorBinderSupport 
	implements OnFocusChangeListener, ViewBinderInstantiation {
	// 获得焦点事件
	private OnFocusChangeListener originally;
	// 与绑定器关联的属性
	private String property;
	
	@Override
	public void setViewValue(View view, Object bean, String property) {
		this.property = property;
		
		EditText editText = (EditText)view;
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
		if (value != null) {
			editText.setText(value.toString());
		}
		else {
			editText.setText("");
		}
		editText.setTag(bean);
		if (editText.getOnFocusChangeListener() != null 
				&& !editText.getOnFocusChangeListener().equals(this)) {
			originally = editText.getOnFocusChangeListener();
		}
		editText.setOnFocusChangeListener(this);
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		// 失去焦点后，将输入框中的内容重写到bean中
		if (!hasFocus) {
			EditText editText = (EditText)v;
			String value = editText.getText().toString();
			try {
				if (editText.getTag() != null) {
					setValue(editText.getTag(), property, value);
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
		
		if (originally != null) {
			originally.onFocusChange(v, hasFocus);
		}
	}

	@Override
	public ViewBinder newInstance(View view) {
		return new EditTextBinder();
	}

}
