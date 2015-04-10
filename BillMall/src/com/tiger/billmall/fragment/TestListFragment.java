package com.tiger.billmall.fragment;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
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
import com.tiger.billmall.activity.MainActivity;
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
public class TestListFragment extends Fragment implements IndicatorOnItemClickListener {
	
	private String TAG = TestListFragment.class.getSimpleName();
	
	private MainActivity activity;
	private ListView testListView;//下拉列表
	private PullToRefreshListView mPullRefreshListView;
	
	private List<String> titles;//置顶标题list
	private List<Drawable> images;//置顶图片list

	private Bitmap bitmap;
	private ImageIndicator imageIndicator;
	
	@SuppressWarnings("unused")
	private String channelName;
	private String channelId;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public void onAttach(Activity activity) {
		this.activity = (MainActivity) activity;
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test_list, null);
		mPullRefreshListView = (PullToRefreshListView)view.findViewById(R.id.road_news_listview);
		testListView = mPullRefreshListView.getRefreshableView();
		
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
		
		//获取所有置顶
		getPictures();
		// 获取底部事件列表内容
		return view;
	}

	//获取数据
	private void getData(){
		
	}
	//获取所有置顶新闻 标题 图片
	@SuppressLint("SdCardPath")
	private void getPictures() {
		LayoutInflater layoutInflater = LayoutInflater.from((MainActivity)getActivity());
		View rootView = layoutInflater.inflate(R.layout.layout_test_listview_header, null);
		
		titles = new ArrayList<String>();
		images = new ArrayList<Drawable>();
		
		imageIndicator = (ImageIndicator)rootView.findViewById(R.id.road_news_imageIndicator);
		imageIndicator.setIndicatorOnItemClickListener(TestListFragment.this);
//		Drawable object = new Drawable(R.drawable.image01);
		Drawable object = 
				this.getResources().getDrawable(R.drawable.image01);
		images.add(object);
		Drawable object1 = 
				this.getResources().getDrawable(R.drawable.image02);
		images.add(object1);
		Drawable object2 = 
				this.getResources().getDrawable(R.drawable.image03);
		images.add(object2);
		Drawable object3 = 
				this.getResources().getDrawable(R.drawable.image04);
		images.add(object3);
		
		imageIndicator.setSlideImages(images);
		imageIndicator.setSlideTitles(titles);
	}
	
	@Override
	public void onIndicatorItemClick(int position) {
		// TODO Auto-generated method stub
	}
	
	/** 
	 * 查询最后一页数据使用的异步关闭查询类
	 */
	private class CancelSearch extends AsyncTask<Void, Void, String> {

		@Override
		protected String doInBackground(Void... params) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
			return "";
		}

		@Override
		protected void onPostExecute(String result) {
			mPullRefreshListView.onRefreshComplete();

			super.onPostExecute(result);
		}
	}
}
