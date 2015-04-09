package com.tiger.billmall.fragment;

import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.tiger.billmall.R;
import com.tiger.billmall.activity.MainActivity;

/**
 * 列表页面
 * 
 * <p>Date             Author      Description</p>
 * <p>--------------------------------------------------------------</p>
 * <p>2014-12-09                  new</p>
 * <p>  </p>
 */
public class TestListFragment extends Fragment {
	
	private String TAG = TestListFragment.class.getSimpleName();
	
	private MainActivity activity;
	private ListView newsListView;//下拉列表
	private PullToRefreshListView mPullRefreshListView;
	
	private List<String> titles;//置顶新闻标题list
	private List<Drawable> images;//置顶新闻图片list

	private Bitmap bitmap;
	
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
		newsListView = mPullRefreshListView.getRefreshableView();
		
			
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
		
		//获取所有置顶新闻
		getTopNews();
		// 获取底部事件列表内容
		return view;
	}

	//获取数据
	private void getData(){
		
	}
	//获取所有置顶新闻 标题 图片
	@SuppressLint("SdCardPath")
	private void getTopNews() {
		
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
