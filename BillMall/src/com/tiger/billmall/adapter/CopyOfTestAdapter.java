package com.tiger.billmall.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiger.billmall.R;
import com.tiger.billmall.activity.Activity;
import com.tiger.billmall.entity.PhoneShow;
import com.tiger.billmall.entity.ProductLay;

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
public class CopyOfTestAdapter extends BaseAdapter{
	
//	private final String TAG = NewsAdapter.class.getSimpleName();
//	private static Logger log = Logger.getLogger(Activity.class);
	private Bitmap bitmap;//一张图片
	private Bitmap bitmap1;//两张图片
	private Bitmap bitmap2;//三张图片
	private ArrayList<ProductLay> newsList;
	private LayoutInflater inflater = null;
	private Activity activity;
	
	public CopyOfTestAdapter(Activity activity, ArrayList<ProductLay> newsList) {
		this.newsList = newsList;
		inflater = LayoutInflater.from(activity);
	}
	

	@Override
	public int getCount() {
		return newsList == null ? 0 : newsList.size();
	}

	@Override
	public ProductLay getItem(int position) {
		if (newsList != null && newsList.size() != 0) {
			return newsList.get(position);
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
			view = inflater.inflate(R.layout.test_list_item, null);
			mHolder = new ViewHolder();
			//一张图片的布局
			mHolder.item_layout1 = (LinearLayout)view.findViewById(R.id.layout_type1);
			mHolder.type1_image = (ImageView)view.findViewById(R.id.layout_type1_image);
			mHolder.title1 = (TextView)view.findViewById(R.id.layout_type1_title);
			mHolder.simContent = (TextView)view.findViewById(R.id.layout_type1_simTitle);
			//3张图片时候的布局
			mHolder.item_layout2 = (LinearLayout)view.findViewById(R.id.layout_type2);
			mHolder.title2 = (TextView)view.findViewById(R.id.layout_type2_title);
			mHolder.type2_image1 = (ImageView)view.findViewById(R.id.layout_type2_image1);
			mHolder.type2_image2 = (ImageView)view.findViewById(R.id.layout_type2_image2);
			mHolder.type2_image3 = (ImageView)view.findViewById(R.id.layout_type2_image3);
			
			view.setTag(mHolder);
		} else {
			mHolder = (ViewHolder) view.getTag();
		}
		//获取position对应的数据
		ProductLay news = getItem(position);
			
//		bitmap.recycle();
		return view;

	}

	static class ViewHolder {
	 	LinearLayout item_layout1;
		ImageView type1_image;
		TextView title1;
		TextView simContent;
		
		LinearLayout item_layout2; //3张图片时候的布局
		TextView title2;
		ImageView type2_image1;
		ImageView type2_image2;
		ImageView type2_image3;
	}
	
}
