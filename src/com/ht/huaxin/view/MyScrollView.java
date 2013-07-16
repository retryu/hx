package com.ht.huaxin.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;
import android.widget.Scroller;
 
/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create Time：2013-7-16 下午04:55:22
 * file declare:
 */
public class MyScrollView extends  ScrollView{

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		Log.e("debug", "MyScrollView  ontouch");
		return super.onTouchEvent(ev);
	
	}

}
