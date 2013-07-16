package com.ht.huaxin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.ht.huaxin.R;

public class ShaderView extends ImageView {
	private Bitmap bitmap;
	private ShapeDrawable drawable;
	private static final int RADIUS = 80;
	private static final int FACTOR = 1;
	private Matrix matrix = new Matrix();
	private boolean isShowing;

	public ShaderView(Context context) {
		super(context);
	}

	public ShaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		isShowing = false;
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b);
		BitmapShader shader = new BitmapShader(bitmap, TileMode.CLAMP,
				TileMode.CLAMP);
		// 圆形的drawable
		drawable = new ShapeDrawable(new OvalShape());
		drawable.getPaint().setShader(shader);
		drawable.setBounds(10, 10, RADIUS * 4, RADIUS * 4);
	}

	public void config(Bitmap b) {
		// bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.b);
		bitmap = b;
		BitmapShader shader = new BitmapShader(Bitmap.createScaledBitmap(
				bitmap, bitmap.getWidth() * FACTOR,
				bitmap.getHeight() * FACTOR, true), TileMode.CLAMP,
				TileMode.CLAMP);
		// 圆形的drawable
		drawable = new ShapeDrawable(new OvalShape());
		drawable.getPaint().setShader(shader);
		drawable.setBounds(10, 10, RADIUS * 4, RADIUS * 4);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		 if (isShowing == false) {
//		 return super.onTouchEvent(event);
//		 }
		final int x = (int) event.getX();
		final int y = (int) event.getY();
		int action = event.getAction();
		 Log.e("debug", "onToucvhEvent:" + event + "  isShowing:" +
		 isShowing);
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			// isShowing = true;
			// invalidate();
			return false;
		 

		case MotionEvent.ACTION_MOVE:
			if (drawable == null) {
				init();
				return true;
			}

			matrix.setTranslate(RADIUS - x * FACTOR, RADIUS - y * FACTOR);
			matrix.postScale(2.0f, 2.0f);
			drawable.getPaint().getShader().setLocalMatrix(matrix);
			// bounds，就是那个圆的外切矩形
			drawable.setBounds(x - RADIUS * 2, y - RADIUS * 2, x + RADIUS * 2,
					y + RADIUS * 2);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			isShowing = false;
			invalidate();
			return false;
		case MotionEvent.ACTION_CANCEL:
			isShowing = false;
			invalidate();
			break;
		}

		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (drawable != null) {
			if (isShowing == true) {
				drawable.draw(canvas);
			}
		}
	}

	public boolean isShowing() {
		return isShowing;
	}

	public void setShowing(boolean isShowing) {
		this.isShowing = isShowing;
	}

}
