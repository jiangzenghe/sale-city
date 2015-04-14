package com.tiger.billmall.fragment;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import com.tiger.billmall.R;
import com.tiger.billmall.activity.TabActivity;
import com.tiger.billmall.adapter.KindAdapter;
import com.tiger.billmall.widgets.ImageIndicator.IndicatorOnItemClickListener;
import com.tjerkw.slideexpandable.library.ActionSlideExpandableListView;

/**
 * 列表页面
 * 
 * <p>Date             Author      Description</p>
 * <p>--------------------------------------------------------------</p>
 * <p>2014-12-09                  new</p>
 * <p>  </p>
 */
public class SearchFragment extends Fragment implements IndicatorOnItemClickListener {
	
	private String TAG = SearchFragment.class.getSimpleName();
	private TabActivity activity;

	private ActionSlideExpandableListView testListView;//下拉列表
	
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
		View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_search, null);
		testListView = (ActionSlideExpandableListView)view.findViewById(R.id.search_list);

		// fill the list with data
		testListView.setAdapter(buildDummyData());
		
		return view;
	}

	/**
	 * Builds dummy data for the test.
	 * In a real app this would be an adapter
	 * for your data. For example a CursorAdapter
	 */
	public ListAdapter buildDummyData() {
		final int SIZE = 10;
		ArrayList<String> values = new ArrayList<String>();
		for(int i=0;i<SIZE;i++) {
			values.add("Item "+i);
		}
//		KindAdapter adapter = new KindAdapter(activity, values);
		return new KindAdapter(activity, values);
//		return new ArrayAdapter<String>(
//				activity,
//				R.layout.expandable_list_origin_item,
//				R.id.text,
//				values
//		);
	}
	
	@Override
	public void onIndicatorItemClick(int position) {
		// TODO Auto-generated method stub
	}
}
