package com.littlecheesecake.ios7;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class OsView extends View{
	private ArrayList<Drawable> circles;
	private int width;
	private int height;
	
	private Bitmap mBitmap;
	private Paint mPaint = new Paint();
	private Canvas mCanvas = new Canvas();
	

	public OsView(Context context) {
		super(context);
		init(context);
	}
	
	public OsView(Context context, AttributeSet attrs){
		super(context, attrs, 0);
		init(context);
	}
	
	public OsView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context){
		loadCircles(5);

	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		this.width = w;
		this.height = h;
		
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bk);
		mBitmap = Bitmap.createScaledBitmap(mBitmap, w, h, false);

		//mCanvas.setBitmap(mBitmap.copy(Bitmap.Config.ARGB_8888, true));
		
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas){
		synchronized(this){
			if(mBitmap != null){
				canvas.drawBitmap(mBitmap, 0, 0, null);
				
				circles.get(0).setBounds(250, 100, 400, 250);
				circles.get(0).draw(canvas);
			}
		}
	}
	
	
	private void loadCircles(int num){
		//TODO:load the drawables in the arraylist
		//keep in mind the top layer have smaller motion than bottom layer: design the order
		circles = new ArrayList<Drawable>(num);
		Drawable circle = getResources().getDrawable(R.drawable.c1);
		circles.add(circle);
	}
	
	/**
	 * Generate a set of parameters to draw the circle (random with input of width and height of canvas)
	 * @author aki
	 *
	 */
	private class DrawingParameters{
		int x; //x position
		int y; //y position
		int size; //size of circle
		int motion; //motion parameter
		
		public DrawingParameters(int motion){
			//TODO:generate random parameters for x, y, size
		}
	}

}
