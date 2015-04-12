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
public class TestFragment extends Fragment implements IndicatorOnItemClickListener {
	
	private String TAG = TestFragment.class.getSimpleName();
	
	private MainActivity activity;
	
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
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_test, null);
		return view;
	}

	//获取数据
	private void getData(){
		
	}
	//获取所有置顶新闻 标题 图片
	@SuppressLint("SdCardPath")
	private void getPictures() {
	}
	
	@Override
	public void onIndicatorItemClick(int position) {
		// TODO Auto-generated method stub
	}
}
