package com.tiger.billmall.adapter.impl;

import java.lang.reflect.Field;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.tiger.billmall.adapter.binder.CompoundButtonBinder;
import com.tiger.billmall.adapter.binder.EditTextBinder;
import com.tiger.billmall.adapter.binder.RatingBarBinder;
import com.tiger.billmall.adapter.binder.TextViewBinder;
import com.tiger.billmall.adapter.binder.ViewBinder;
import com.tiger.billmall.annotation.AdapterBinder;
import com.tiger.billmall.annotation.TextViewAdapterBinder;
import com.tiger.billmall.util.Util;
/**
 * 一个类型化的列表适配器接口实现类，实现通过注解方式进行控件与实体类的绑定
 */
public class AnnotationAttachmentImpl<T> extends ClassAttachmentImpl<T> {
	
	public AnnotationAttachmentImpl(){
		super();
	}
	@Override
	public void attachToView(Context context, View view, T bean) {
		// 获取加有标注的实体类属性
		List<Field> baseFields = Util.findField(bean.getClass(), AdapterBinder.class);
		List<Field> textViewFields = Util.findField(bean.getClass(), TextViewAdapterBinder.class);
		/*
		 * 用实体类属性对应的绑定器绑定数据
		 * 如果标注中注明绑定器类型，直接绑定
		 * 否则根据数据类型选择默认的绑定器
		 */
		for (int i = 0; i < baseFields.size(); i++) {
			ViewBinder viewBinderEntity = null;
			AdapterBinder ab = baseFields.get(i).getAnnotation(AdapterBinder.class);
			// 根据资源名称获取资源id
			int resId = context.getResources().getIdentifier(ab.resName(), "id", context.getPackageName());
			View v = view.findViewById(resId);
			
			Key key = new Key();
			key.setViewResId(resId);
			key.setProperty(baseFields.get(i).getName());
			
			if(map.containsKey(key)){// 如果绑定器已经存在
				viewBinderEntity = map.get(key);
			}else{// 不存在生成新绑定器
				if(ab.type() != ViewBinder.class){
					Class<? extends ViewBinder> viewBinderClass = ab.type();
					try {
						viewBinderEntity = viewBinderClass.newInstance();
						
					} catch (InstantiationException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					
				}else{
					if(v instanceof EditText){
						viewBinderEntity = new EditTextBinder();
					}else if(v instanceof CompoundButton){
						viewBinderEntity = new CompoundButtonBinder();
					}else if(v instanceof TextView){
						viewBinderEntity = new TextViewBinder();
					}else if(v instanceof RatingBar){
						viewBinderEntity = new RatingBarBinder();
					}else{
						throw new RuntimeException("映射了未许可的类型");
					}
				}
				// 新生成的绑定器放入map中
				map.put(key, viewBinderEntity);
			}
			viewBinderEntity.setViewValue(v,bean,baseFields.get(i).getName());
			
		}
		
		/*
		 * 扩展的TextView的绑定器
		 */
		for (int i = 0; i < textViewFields.size(); i++) {
			TextViewBinder viewBinderEntity = null;
			TextViewAdapterBinder tva = textViewFields.get(i).getAnnotation(TextViewAdapterBinder.class);
			// 根据资源名称获取资源id
			int resId = context.getResources().getIdentifier(tva.resName(), "id", context.getPackageName());
			View v = view.findViewById(resId);
			String strFormat = tva.formatMessage();
			Key key = new Key();
			key.setViewResId(resId);
			key.setProperty(textViewFields.get(i).getName());
			if(map.containsKey(key)){// 如果绑定器已经存在
				viewBinderEntity = (TextViewBinder)map.get(key);
			}else{// 不存在生成新绑定器
				viewBinderEntity = new TextViewBinder();
				viewBinderEntity.setFormat(strFormat);
				// 新生成的绑定器放入map中
				map.put(key, viewBinderEntity);
			}
			viewBinderEntity.setViewValue(v,bean,textViewFields.get(i).getName());
			
		}
		
	}

}
