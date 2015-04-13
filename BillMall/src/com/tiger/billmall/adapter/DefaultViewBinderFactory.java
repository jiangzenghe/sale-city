package com.tiger.billmall.adapter;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.view.View;

import com.tiger.billmall.adapter.binder.ViewBinder;
import com.tiger.billmall.util.StringUtils;
/**
 * ViewBinderFactory接口的默认实现类。该类建立资源视图标识与属性名到ViewBinder绑定器的映射关系。
 * 以视图组件标识与属性名为单位返回对象的绑定器。
 * @deprecated
 */
@SuppressLint("UseSparseArrays")
public class DefaultViewBinderFactory {
	public static interface ViewBinderInstantiation {
		public ViewBinder newInstance(View view);
	}
	
	static private final class Key {
		private Integer viewResId;
		private String property;
		
		@SuppressWarnings("unused")
		public Integer getViewResId() {
			return viewResId;
		}
		public void setViewResId(Integer viewResId) {
			this.viewResId = viewResId;
		}
		@SuppressWarnings("unused")
		public String getProperty() {
			return property;
		}
		public void setProperty(String property) {
			this.property = property;
		}
		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			
			if (!(o instanceof Key)) {
				return false;
			}
			
			Key key = (Key)o;
			return this.viewResId.intValue() == key.viewResId.intValue()
					&& StringUtils.isEquals(this.property, key.property);
		}
		@Override
		public int hashCode() {
			int code = 17;
			
			code += 37 * viewResId.hashCode();
			
			if (property != null) {
				code += 37 * property.hashCode();
			}
			
			return code;
		}
	}
	
	private Map<Key, ViewBinderInstantiation> map;
	
	public DefaultViewBinderFactory() {
		map = new HashMap<Key, ViewBinderInstantiation>();
	}
	
	public void addViewBinderInstantiation(int viewResId, String property, ViewBinderInstantiation inst) {
		Key key = new Key();
		key.setViewResId(viewResId);
		key.setProperty(property);
		if (map.containsKey(key)) {
			return ;
		}
		map.put(key, inst);
	}
	public void removeViewBinderInstantiation(int viewResId, String property) {
		Key key = new Key();
		key.setViewResId(viewResId);
		key.setProperty(property);
		map.remove(key);
	}
}
