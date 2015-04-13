package com.tiger.billmall.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiger.billmall.R;
import com.tiger.billmall.activity.TabActivity;
import com.tiger.billmall.adapter.ProductGroupAdapter;
import com.tiger.billmall.entity.Product;
import com.tiger.billmall.entity.ProductLay;
import com.tiger.billmall.widgets.ImageIndicator;
import com.tiger.billmall.widgets.ImageIndicator.IndicatorOnItemClickListener;

/**
 * 列表页面
 * 
 * <p>Date             Author      Description</p>
 * <p>--------------------------------------------------------------</p>
 * <p>2014-12-09                  new</p>
 * <p>  </p>
 */
public class TestFragment extends Fragment implements IndicatorOnItemClickListener {
	
	private String TAG = TestFragment.class.getSimpleName();
	private TabActivity activity;

	private ListView testListView;//下拉列表
	private ArrayList<ProductLay> mDatas;//数据源
	private PullToRefreshListView mPullRefreshListView;
	
	private List<String> titles;//置顶标题list
	private List<Drawable> images;//置顶图片list
	private ImageIndicator imageIndicator;
	private ListView multiListView;
	
	private ProductGroupAdapter adapter;//适配器
	private String channelName;
	private String channelId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public void onAttach(Activity activity) {
		this.activity = (TabActivity) activity;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test, null);
		mPullRefreshListView = (PullToRefreshListView)view.findViewById(R.id.road_test_listview);
		testListView = mPullRefreshListView.getRefreshableView();
		
		mDatas = new ArrayList<ProductLay>();
		adapter = new ProductGroupAdapter(activity, mDatas);
		testListView.setAdapter(adapter);
		
		mPullRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				//获取所有置顶新闻
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				
			}
		});
		
		mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long id) {
				if (id == -1) {
					return;
				}
			}
		});
		getData();
		getPictures();
		return view;
	}

	//获取数据
	private void getData(){
		for (int i = 0; i <= 5; i++) {
			ProductLay tempGroup = new ProductLay();
			tempGroup.setDeviceGroupId("组名"+i);
			tempGroup.setDeviceGroup(new ArrayList<Product>());
			for(int j=0;j<=i;j++) {
				Product temp = new Product();
				temp.setDeviceId("" + i + j);
				temp.setDeviceLabel("设备" + i);
				byte[] imageByteArray = { 0, 1, 2, 3, 4 };
				temp.setPicBytes(imageByteArray);
				tempGroup.getDeviceGroup().add(temp);
			}
			mDatas.add(tempGroup);
		}
		
		adapter.notifyDataSetChanged();
	}
	//获取所有置顶新闻 标题 图片
		@SuppressLint("SdCardPath")
		private void getPictures() {
			LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
			View rootView = layoutInflater.inflate(R.layout.layout_listview_header, null);
//			View rootView = layoutInflater.inflate(R.layout.fragment_test, null);
			/**
			 * 首次增加时，添加置顶新闻，之后至改变置顶新闻的数据源。
			 */
			if(testListView.getHeaderViewsCount()==1){
				testListView.addHeaderView(rootView);
			}
			
			titles = new ArrayList<String>();
			images = new ArrayList<Drawable>();
			
			imageIndicator = (ImageIndicator)rootView.findViewById(R.id.road_news_imageIndicator);
			
			imageIndicator.setIndicatorOnItemClickListener(TestFragment.this);
			Drawable object = 
					this.getResources().getDrawable(R.drawable.image01);
			images.add(object);
			titles.add("image00");
			Drawable object1 = 
					this.getResources().getDrawable(R.drawable.image02);
			images.add(object1);
			titles.add("image01");
			Drawable object2 = 
					this.getResources().getDrawable(R.drawable.image03);
			images.add(object2);
			titles.add("image02");
			Drawable object3 = 
					this.getResources().getDrawable(R.drawable.image04);
			images.add(object3);
			titles.add("image03");
			imageIndicator.setSlideImages(images);
			imageIndicator.setSlideTitles(titles);
			imageIndicator.initViews();
		}
	
	@Override
	public void onIndicatorItemClick(int position) {
		// TODO Auto-generated method stub
	}
}
