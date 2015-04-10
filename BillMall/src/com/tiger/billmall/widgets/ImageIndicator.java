package com.tiger.billmall.widgets;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiger.billmall.R;

public class ImageIndicator extends FrameLayout{

	public interface IndicatorOnItemClickListener{
		public void onIndicatorItemClick(int position);
	}
	
	public ImageIndicator(Context context) {
		super(context);
		this.context = context;
	}

	public ImageIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}
	
	public ImageIndicator(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.context = context;
	}

	private Context context;
	// 滑动图片列表
	private ArrayList<View> imagePageViews = new ArrayList<View>();
	private ViewPager viewPager = null;
	// 滑动图片数组，初始化之前需传入
	private List<Drawable> slideImages;
	
	// 包含圆点图片的View
	private ViewGroup imageCircleView = null;
	private ImageView[] imageCircleViews = null; 
	
	// 滑动标题
	private TextView tvSlideTitle = null;
	private List<String> slideTitles;
	
	// 布局设置类
	private SlideImageLayout slideLayout = null;
	//轮循服务
	private ScheduledExecutorService scheduledExecutorService;
	// 当前ViewPager索引
	private int pageIndex = 0;
	
	private IndicatorOnItemClickListener indicatorOnItemClickListener;
	
	
	/**
	 * 初始化
	 */
	public void initViews(){
		
		
		if(slideImages == null){
			throw new RuntimeException("不能为空");
		}
		// 滑动图片区域
		
		LayoutInflater.from(context).inflate(R.layout.image_indicator,
				this, true);
		viewPager = (ViewPager) findViewById(R.id.image_slide_page);
		
		// 圆点图片区域
		int length = slideImages.size();
		imageCircleViews = new ImageView[length];
		imageCircleView = (ViewGroup) findViewById(R.id.layout_circle_images);
		slideLayout = new SlideImageLayout(context);
		slideLayout.setCircleImageLayout(length);
		
		for(int i = 0;i < length;i++){
			ImageView iv = new ImageView(context);
			iv.setImageDrawable(slideImages.get(i));//更改这里，将图片传入
			imagePageViews.add(iv);
			imageCircleViews[i] = slideLayout.getCircleImageLayout(i);
			imageCircleView.addView(slideLayout.getLinearLayout(imageCircleViews[i], 10, 10));
		}
		
		// 设置默认的滑动标题
		tvSlideTitle = (TextView) findViewById(R.id.tvSlideTitle);
		tvSlideTitle.setGravity(Gravity.CENTER_VERTICAL);
		tvSlideTitle.setTextSize(13);
		tvSlideTitle.setText(slideTitles.get(0));
		
		// 设置ViewPager
		SlideImageAdapter imageAdapter = new SlideImageAdapter();
        viewPager.setAdapter(imageAdapter);  
        viewPager.setOnPageChangeListener(new ImagePageChangeListener());

	}
	
	public ArrayList<View> getImagePageViews() {
		return imagePageViews;
	}

	public void setImagePageViews(ArrayList<View> imagePageViews) {
		this.imagePageViews = imagePageViews;
	}

	public List<String> getSlideTitles() {
		return slideTitles;
	}

	public void setSlideTitles(List<String> slideTitles) {
		this.slideTitles = slideTitles;
	}

	public List<Drawable> getSlideImages() {
		return slideImages;
	}

	public void setSlideImages(List<Drawable> slideImages) {
		this.slideImages = slideImages;
	}

	// 滑动图片数据适配器
    /**
     * @author admin
     *
     */
    private class SlideImageAdapter extends PagerAdapter {  
        @Override  
        public int getCount() { 
            return getImagePageViews().size();  
        }  
  
        @Override  
        public boolean isViewFromObject(View arg0, Object arg1) {  
            return arg0 == arg1;  
        }  
  
        @Override  
        public int getItemPosition(Object object) {  
            
            return super.getItemPosition(object);  
        }  
  
        @Override  
        public void destroyItem(View arg0, int arg1, Object arg2) {  
            
            ((ViewPager) arg0).removeView(getImagePageViews().get(arg1));  
        }  
  
        @Override  
        public Object instantiateItem(View arg0, final int arg1) {  
            
        	((ViewPager) arg0).addView(getImagePageViews().get(arg1));
        	getImagePageViews().get(arg1).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(getIndicatorOnItemClickListener() == null){
						return;
					}
					getIndicatorOnItemClickListener().onIndicatorItemClick(arg1);
				}
			});
            return getImagePageViews().get(arg1);  
        }  
  
        @Override  
        public void restoreState(Parcelable arg0, ClassLoader arg1) {  
            
  
        }  
  
        @Override  
        public Parcelable saveState() {  
            
            return null;  
        }  
  
        @Override  
        public void startUpdate(View arg0) {  
            
  
        }  
  
        @Override  
        public void finishUpdate(View arg0) {  
            
  
        }  
    }
    
    // 滑动页面更改事件监听器
    /**
     * @author admin
     *
     */
    private class ImagePageChangeListener implements OnPageChangeListener {
        @Override  
        public void onPageScrollStateChanged(int arg0) {  
            
  
        }  
  
        @Override  
        public void onPageScrolled(int arg0, float arg1, int arg2) {  
            
  
        }  
  
        @Override  
        public void onPageSelected(int index) {  
        	pageIndex = index;
        	slideLayout.setPageIndex(index);
        	tvSlideTitle.setText(slideTitles.get(index));
        	
            for (int i = 0; i < imageCircleViews.length; i++) {  
            	imageCircleViews[index].setBackgroundResource(R.drawable.dot_selected);
                
                if (index != i) {  
                	imageCircleViews[i].setBackgroundResource(R.drawable.dot_none);  
                }  
            }
        }  
    }
    
    /**
     * 开始轮循.
     *
     * @param initialDelay 首次执行的延迟时间
     * @param period 连续执行之间的时间
     * @param unit 轮循周期参数单位
     */
    public void startCycle(long initialDelay, long period, TimeUnit unit){
    	scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), initialDelay, period,
				unit);
    }
    
    /**
     * @author admin
     *
     */
    public class ScrollTask implements Runnable {

		public void run() {
			synchronized (viewPager) {
				
				pageIndex = (pageIndex + 1) % imagePageViews.size();
				
				handler.obtainMessage().sendToTarget();
			}
		}
	}
    
    @SuppressLint("HandlerLeak")
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			viewPager.setCurrentItem(pageIndex);
		};
	};
    /**
     * 结束轮循
     * */
    public void stopCycle(){
    	if(scheduledExecutorService!=null){
    		scheduledExecutorService.shutdown();
    	}
    }

	public IndicatorOnItemClickListener getIndicatorOnItemClickListener() {
		return indicatorOnItemClickListener;
	}

	public void setIndicatorOnItemClickListener(
			IndicatorOnItemClickListener indicatorOnItemClickListener) {
		this.indicatorOnItemClickListener = indicatorOnItemClickListener;
	}
    
    
}
