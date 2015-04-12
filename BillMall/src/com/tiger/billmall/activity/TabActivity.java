/**
 * 
 */
package com.tiger.billmall.activity;


import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tiger.billmall.R;
import com.tiger.billmall.adapter.TestFragmentPagerAdapter;
import com.tiger.billmall.fragment.TestListFragment;
import com.tiger.billmall.util.Util;
import com.tiger.billmall.widgets.ImageIndicator;
import com.tiger.billmall.widgets.ImageIndicator.IndicatorOnItemClickListener;

/**
 * @author Jiang
 *
 */
public class TabActivity extends Activity implements IndicatorOnItemClickListener {
	private LinearLayout mRadioGroup_content;
	private ViewPager mViewPager;
	/** 分类列表*/
	private ArrayList<String> channelList=new ArrayList<String>();
	/** 当前选中的栏目*/
	private int columnSelectIndex = 0;
	/** 屏幕宽度 */
	private int mScreenWidth = 0;
	/** Item宽度 */
	private int mItemWidth = 0;
	private ArrayList<Fragment> fragments = new ArrayList<Fragment>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_activity_tab);
//		setActivityHeaderTitle("Test");
		
		channelList = new ArrayList<String>();
		channelList.add("首页");
		channelList.add("首页");
		channelList.add("首页");
		channelList.add("首页");
		mScreenWidth = Util.getWindowsWidth(this);
		mItemWidth = mScreenWidth / channelList.size();// 一个Item宽度为屏幕的1/6
		initView();
	}
	
	/** 初始化layout控件*/
	private void initView() {
		mRadioGroup_content = (LinearLayout) findViewById(R.id.mRadioGroup_content);
		mViewPager = (ViewPager) findViewById(R.id.mViewPager);
		setChangelView();
		initColumnData();
	}
	
	/** 
	 *  当栏目项发生变化时候调用
	 * */
	private void setChangelView() {
		

	}
	
	/** 获取Column栏目 数据*/
	private void initColumnData() {
//		userChannelList.add(new ChannelItem(1, "推荐", 1, 0));
//		userChannelList.add(new ChannelItem(2, "热点", 2, 1));
//		userChannelList.add(new ChannelItem(3, "杭州", 3, 1));
//		userChannelList.add(new ChannelItem(4, "时尚", 4, 1));
//		userChannelList.add(new ChannelItem(5, "科技", 5, 1));
//		userChannelList.add(new ChannelItem(6, "体育", 6, 1));
//		userChannelList.add(new ChannelItem(7, "军事", 7, 1));
//		userChannelList.add(new ChannelItem(8, "财经", 1, 1));
		
		initTabColumn();
	}
	

	/** 
	 *  初始化Column栏目项
	 * */
	private void initTabColumn() {
		mRadioGroup_content.removeAllViews();
		int count =  channelList.size();
		for(int i = 0; i< count; i++){
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mItemWidth , LayoutParams.WRAP_CONTENT);
//			params.leftMargin = 5;
//			params.rightMargin = 5;
			TextView columnTextView = new TextView(this);
			columnTextView.setBackgroundResource(R.drawable.radio_buttong_bg);
			columnTextView.setGravity(Gravity.CENTER);
//			columnTextView.setPadding(5, 5, 5, 5);
			columnTextView.setId(i);
			columnTextView.setText(channelList.get(i));
			columnTextView.setTextColor(getResources().getColorStateList(R.color.top_category_scroll_text_color_day));
			if(columnSelectIndex == i){
				columnTextView.setSelected(true);
			}
			columnTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
			          for(int i = 0;i < mRadioGroup_content.getChildCount();i++){
				          View localView = mRadioGroup_content.getChildAt(i);
				          if (localView != v)
				        	  localView.setSelected(false);
				          else{
				        	  localView.setSelected(true);
				        	  mViewPager.setCurrentItem(i);
				          }
			          }
				}
			});
			mRadioGroup_content.addView(columnTextView, i ,params);
		}
	}
	
	/** 
	 *  选择的Column里面的Tab
	 * */
	private void selectTab(int tab_postion) {
		columnSelectIndex = tab_postion;
		//判断是否选中
		for (int j = 0; j <  mRadioGroup_content.getChildCount(); j++) {
			View checkView = mRadioGroup_content.getChildAt(j);
			boolean ischeck;
			if (j == tab_postion) {
				ischeck = true;
			} else {
				ischeck = false;
			}
			checkView.setSelected(ischeck);
		}
	}
	
	@Override
	public void onIndicatorItemClick(int position) {
		// TODO Auto-generated method stub
	}
}
