package com.tiger.billmall.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tiger.billmall.R;
import com.tiger.billmall.activity.Activity;
import com.tiger.billmall.activity.TabActivity;
import com.tiger.billmall.adapter.binder.TextViewBinder;
import com.tiger.billmall.adapter.impl.ClassAttachmentImpl;
import com.tiger.billmall.entity.Product;
import com.tiger.billmall.entity.ProductLay;
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
public class ProductGroupAdapter extends BaseAdapter{
	

	private ArrayList<ProductLay> list;
	private LayoutInflater inflater = null;
	private Activity activity;
	
	public ProductGroupAdapter(Activity activity, ArrayList<ProductLay> list) {
		this.list = list;
		this.inflater = LayoutInflater.from(activity);
		this.activity = activity;
	}
	

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public ProductLay getItem(int position) {
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
			//一张图片的布局
			view = inflater.inflate(R.layout.device_group_item, null);
			mHolder = new ViewHolder();
			//一张图片的布局
			mHolder.gridView = (GridView)view.findViewById(R.id.grid_view);
			mHolder.groupName = (TextView)view.findViewById(R.id.group_name);
			view.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) view.getTag();
		}
		//获取position对应的数据
		ProductLay news = getItem(position);
		mHolder.groupName.setText(news.getDeviceGroupId());
		getData(mHolder.gridView, news.getDeviceGroup());
			
//		bitmap.recycle();
		return view;

	}
	
	//获取数据
		private void getData(GridView gridView, ArrayList<Product> mDatas){
			
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
