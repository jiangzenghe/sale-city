package com.tiger.billmall.adapter.binder;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import android.view.View;
import android.widget.TextView;

import com.tiger.billmall.adapter.DefaultViewBinderFactory.ViewBinderInstantiation;

/**
 * 文本绑定器
 */
@SuppressWarnings("deprecation")
public class TextViewBinder extends ReflectionEditorBinderSupport implements
		ViewBinder, ViewBinderInstantiation {
	private MessageFormat format;
	@Override
	public ViewBinder newInstance(View view) {
		return new TextViewBinder();
	}

	public TextViewBinder() {
		this.format = null;
	}
	public TextViewBinder(MessageFormat format) {
		this.format = format;
	}
	/**
	 * 绑定方法
	 */
	@Override
	public void setViewValue(View view, Object bean, String property) {
		TextView textView = (TextView)view;
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
			if(format == null){
				textView.setText(value.toString());
			}else{
				Object[] arrObjects = {value};
				textView.setText(format.format(arrObjects));
			}
		}
		else {
			textView.setText("");
		}
	}

	public MessageFormat getFormat() {
		return format;
	}

	public void setFormat(String strFormat) {
		this.format = new MessageFormat(strFormat);
	}

}
