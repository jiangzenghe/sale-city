package com.tiger.billmall.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiger.billmall.R;
import com.tiger.billmall.activity.Activity;
import com.tiger.billmall.activity.TabActivity;
import com.tiger.billmall.adapter.binder.TextViewBinder;
import com.tiger.billmall.adapter.impl.ClassAttachmentImpl;
import com.tiger.billmall.entity.Product;
import com.tiger.billmall.widgets.GridView;

/**
 * 
 *
 * @author   
 * 
 * <p>Modification History:</p>
 * <p>Date             Author      Description</p>
 * <p>--------------------------------------------------------------</p>
 * <p>             new</p>
 * <p>  </p>
 */
public class KindAdapter extends BaseAdapter{
	

	private ArrayList<String> list;
	private LayoutInflater inflater = null;
	private Activity activity;
	
	public KindAdapter(Activity activity, ArrayList<String> list) {
		this.list = list;
		this.inflater = LayoutInflater.from(activity);
		this.activity = activity;
		list = new ArrayList<String>();
		list.add("1");
		list.add("2");
	}
	

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public String getItem(int position) {
		if (list != null && list.size() != 0) {
			return list.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("SdCardPath")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder mHolder;
		View view = convertView;
		if (view == null) {
			view = inflater.inflate(R.layout.expandable_list_item, null);
			mHolder = new ViewHolder();
			mHolder.gridView = (GridView)view.findViewById(R.id.grid_view);
			mHolder.groupName = (TextView)view.findViewById(R.id.text);
			view.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) view.getTag();
		}
		//获取position对应的数据
		String news = getItem(position);
		mHolder.groupName.setText(news);
		mHolder.gridView.setOnItemClickListener(new OnItemClickListener() {//二级图层短按事件，控制所点击子图层在地图上的隐藏or显示

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position,
					long id) {
				
			}
        	
        });
		getData(mHolder.gridView);
			
//		bitmap.recycle();
		return view;

	}
	
	//获取数据
		private void getData(GridView gridView){
			ArrayList<Product> mDatas = new ArrayList<Product>();
			for(int i=0;i<2;i++) {
				Product object = new Product();
				object.setDeviceId(""+i);
				object.setDeviceLabel("设备" + i);
				byte[] imageByteArray = { 0, 1, 2, 3, 4 };
				object.setPicBytes(imageByteArray);
				mDatas.add(object);
			}
			if(mDatas.size()%3!=0) {
				int m = 3-mDatas.size()%3;
				for(int i=0;i<m;i++) {
					Product object = new Product();
					mDatas.add(object);
				}
			}
			ClassAttachmentImpl<Product> attachments = new ClassAttachmentImpl<Product>();
			attachments.addBinderMapItem(R.id.device_center_id, "deviceId",new TextViewBinder());

			attachments.addBinderMapItem(R.id.device_center_title, "deviceLabel",new TextViewBinder());

			TypedAdapter<Product> deviceAdapter = 
					new TypedAdapter<Product>((TabActivity)activity, mDatas, R.layout.device_item);
			deviceAdapter.setBinder(attachments);

			gridView.setAdapter(deviceAdapter);
			deviceAdapter.notifyDataSetChanged();
		}

	static class ViewHolder {
	 	TextView groupName;
		GridView gridView;
		
	}
	
}
