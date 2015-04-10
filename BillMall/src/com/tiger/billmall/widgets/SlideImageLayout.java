package com.tiger.billmall.widgets;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tiger.billmall.R;

public class SlideImageLayout {
	// 包含图片的ArrayList
	private ArrayList<ImageView> imageList = null;
	private Context context = null;
	// 圆点图片集合
	private ImageView[] imageViews = null; 
	private ImageView imageView = null;
	// 表示当前滑动图片的索引
	private int pageIndex = 0;
	
	public SlideImageLayout(Context context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		imageList = new ArrayList<ImageView>();
	}
	
	/**
	 * 生成滑动图片区域布局
	 * @param index
	 * @return
	 */
	public View getSlideImageLayout(int index){
		// 包含TextView的LinearLayout
		LinearLayout imageLinerLayout = new LinearLayout(context);
		LinearLayout.LayoutParams imageLinerLayoutParames = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, 
				LinearLayout.LayoutParams.WRAP_CONTENT,
				1);
		
		ImageView iv = new ImageView(context);
		iv.setBackgroundResource(index);
		iv.setOnClickListener(new ImageOnClickListener());
		imageLinerLayout.addView(iv,imageLinerLayoutParames);
		imageList.add(iv);
		
		return imageLinerLayout;
	}
	
	/**
	 * 获取LinearLayout
	 * @param view
	 * @param width
	 * @param height
	 * @return
	 */
	public View getLinearLayout(View view,int width,int height){
		LinearLayout linerLayout = new LinearLayout(context);
		LinearLayout.LayoutParams linerLayoutParames = new LinearLayout.LayoutParams(
				width, 
				height,
				1);
		// 这里最好也自定义设置，有兴趣的自己设置。
		linerLayout.setPadding(10, 0, 10, 0);
		linerLayout.addView(view, linerLayoutParames);
		
		return linerLayout;
	}
	
	/**
	 * 设置圆点个数
	 * @param size
	 */
	public void setCircleImageLayout(int size){
		imageViews = new ImageView[size];
	}
	
	/**
	 * 生成圆点图片区域布局对象
	 * @param index
	 * @return
	 */
	public ImageView getCircleImageLayout(int index){
		imageView = new ImageView(context);  
		imageView.setLayoutParams(new LayoutParams(10,10));
        imageView.setScaleType(ScaleType.FIT_XY);
        
        imageViews[index] = imageView;
         
        if (index == 0) {  
            //默认选中第一张图片
            imageViews[index].setBackgroundResource(R.drawable.dot_selected);  
        } else {  
            imageViews[index].setBackgroundResource(R.drawable.dot_none);  
        }  
         
        return imageViews[index];
	}
	
	/**
	 * 设置当前滑动图片的索引
	 * @param index
	 */
	public void setPageIndex(int index){
		pageIndex = index;
	}
	
	public int getPageIndex(){
		return pageIndex;
	}
	
	// 滑动页面点击事件监听器
    private class ImageOnClickListener implements OnClickListener{
    	@Override
    	public void onClick(View v) {
    		Toast.makeText(context, "sss:"+pageIndex,Toast.LENGTH_LONG).show();
    	}
    }
}
