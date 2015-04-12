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
public class TestActivity extends Activity implements IndicatorOnItemClickListener {
	private List<String> titles;//置顶标题list
	private List<Drawable> images;//置顶图片list

	private ImageIndicator imageIndicator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_test_listview_header);
//		setActivityHeaderTitle("Test");
		
		initView();
	}
	
	/** 初始化layout控件*/
	private void initView() {
		titles = new ArrayList<String>();
		images = new ArrayList<Drawable>();
		
		imageIndicator = (ImageIndicator)findViewById(R.id.road_news_imageIndicator);
		imageIndicator.setIndicatorOnItemClickListener(TestActivity.this);
		
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
