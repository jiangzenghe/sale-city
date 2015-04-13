package com.tiger.billmall.widgets;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.tiger.billmall.adapter.TypedAdapter;
import com.tiger.billmall.adapter.impl.AnnotationAttachmentImpl;
import com.tiger.billmall.annotation.AdapterBinder;


/**
 * 九宫格
 * 1.使用的单元Item必须实现CellItem接口
 * 2.必须调用init()
 * 3.如果使用默认的cell实体类，那么createGridView传入的资源布局中必须包含cell_title和cell_icon这两个资源名称
 * 
 */
public class GridView extends android.widget.GridView  implements OnItemClickListener {

	final public static String LAYOUT_INFO = "layout_info";
	
	private List<CellItem> items = new ArrayList<CellItem>();
	
	private int resCellId;
	
	private Context context;
	
	public interface CellItem {
		public void onClick(GridView gridView, View itemView);
	}
	/**
	 * GridView使用的默认元素实体
	 * 需要实现@AdapterBinder 标注
	 */
	public static class Cell implements CellItem {
		@AdapterBinder(resName = "cell_title")
		private String title;
		@AdapterBinder(resName = "cell_icon")
		private Drawable icon;
		private Intent intent;
		
		public Intent getIntent() {
			return intent;
		}

		public void setIntent(Intent intent) {
			this.intent = intent;
		}

		@Override
		public void onClick(GridView gridView, View itemView) {
			if(intent != null){
				gridView.getContext().startActivity(intent);
			}
			
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Drawable getIcon() {
			return icon;
		}

		public void setIcon(Drawable icon) {
			this.icon = icon;
		}
	}
	
	/**
	 * 构造器
	 * @param context上下文
	 * @param attrs自定义资源
	 */
	public GridView(Context context, AttributeSet attrs){
		super(context, attrs);
		this.context = context;
		setOnItemClickListener(this);
	}
	
	public void init(int resCellId){
		setResCellId(resCellId);
	}
	/**
	 * 构造器
	 * @param context上下文
	 */
	public GridView(Context context){
		this(context, null);
	}

	/**
	 * 在指定位置添加一个元素
	 *
	 * @param cell 添加的元素
	 * @param position 指定的位置
	 */
	@SuppressWarnings("unchecked")
	public void addItem(CellItem cell, int position ){
		getItems().add(position, cell);
		TypedAdapter<CellItem> adapter = (TypedAdapter<CellItem>)getAdapter();
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * 添加一个元素
	 *
	 * @param cell 添加的元素
	 */
	@SuppressWarnings("unchecked")
	public void addItem(CellItem cell){
		getItems().add(cell);
		TypedAdapter<CellItem> adapter = (TypedAdapter<CellItem>)getAdapter();
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * 添加多个元素
	 *
	 * @param cells 添加的元素集合
	 */
	@SuppressWarnings("unchecked")
	public void addItems(List<CellItem> cells){
		getItems().addAll(cells);
		TypedAdapter<CellItem> adapter = (TypedAdapter<CellItem>)getAdapter();
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * 移除指定位置的元素
	 *
	 * @param position 元素所在位置
	 */
	@SuppressWarnings("unchecked")
	public void removeItem(int position){
		getItems().remove(position);
		TypedAdapter<CellItem> adapter = (TypedAdapter<CellItem>)getAdapter();
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * 移除一个元素
	 *
	 * @param cell 被移除的元素
	 */
	@SuppressWarnings("unchecked")
	public void removeItem(CellItem cell){
		getItems().remove(cell);
		TypedAdapter<CellItem> adapter = (TypedAdapter<CellItem>)getAdapter();
		adapter.notifyDataSetChanged();
	}
	
	/**
	 * 点击子项时跳转到相应的Activity
	 * @param parent 发生点击时所在的视图
	 * @param view     视图对象
	 * @param position 在适配器中视图的位置
	 * @param id  被点击的子项所对应的ID号
	 */
	@SuppressWarnings("unchecked")
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		TypedAdapter<CellItem> adapter = 
				(TypedAdapter<CellItem>)getAdapter();
		CellItem item = adapter.getItem(position);
		item.onClick(this, view);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(     
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST); 
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
	public int getResCellId() {
		return resCellId;
	}
	public void setResCellId(int resCellId) {
		if(this.resCellId == resCellId){
			return;
		}
		this.resCellId = resCellId;
		
		if(this.items != null){
			TypedAdapter<CellItem> adapter = new TypedAdapter<CellItem>(context, getItems(), resCellId);
			adapter.setBinder(new AnnotationAttachmentImpl<GridView.CellItem>());
			setAdapter(adapter);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void clear(){
		items.clear();
		TypedAdapter<CellItem> adapter = (TypedAdapter<CellItem>)getAdapter();
		adapter.notifyDataSetChanged();
	}
	
	public List<CellItem> getItems() {
		return items;
	}
	@SuppressWarnings("unchecked")
	public void setItems(List<CellItem> items) {
		this.items = items;
		if(getAdapter() != null){
			TypedAdapter<CellItem> adapter = (TypedAdapter<CellItem>)getAdapter();
			adapter.notifyDataSetChanged();
		}
	}	
}
