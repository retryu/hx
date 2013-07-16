package com.ht.huaxin.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time：2013-7-16 下午04:52:42
 * file declare:
 */
public class MyViewPaper extends   ViewPager {

	public MyViewPaper(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		Log.e("debug","MyViewPaper  onTouch");
		return super.onTouchEvent(arg0);
	}
	

}
