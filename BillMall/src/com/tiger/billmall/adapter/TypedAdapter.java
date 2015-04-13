package com.tiger.billmall.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.tiger.billmall.adapter.impl.AnnotationAttachmentImpl;

/**
 * 一个类型化的列表适配器，该类在内部通过ViewBinderFactory工厂对象建立特定类型对象属性值与视图组件的绑定。
 * 
 * @param <T> 对象类型
 * 
 */
public class TypedAdapter<T> extends ArrayAdapter<T> {
	
	private int itemViewResource;
	private LayoutInflater mInflater;
	private Context context;
	private IAttachment<T> binder;
	/**
	 * 构造器
	 * @param context 应用上下文对象
	 * @param items 类型化对象列表
	 * @param itemViewResource 类型对象展示布局资源标识
	 */
	public TypedAdapter(Context context,
			List<T> items,
			int itemViewResource) {
		
		super(context, itemViewResource, 0, items);
		this.context = context;
		this.itemViewResource =itemViewResource;
		
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		binder = new AnnotationAttachmentImpl<T>();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// 获取需要关联数据的View
		View view;
		if (convertView == null) {
			view = mInflater.inflate(itemViewResource, parent, false);
		} else {
			view = convertView;
		}
		// 获取数据bean
		T bean = getItem(position);
		if(binder != null){
			getBinder().attachToView(context, view, bean);
		}
		return view;
	}

	public IAttachment<T> getBinder() {
		return binder;
	}

	public void setBinder(IAttachment<T> binder) {
		this.binder = binder;
	}
	
}
