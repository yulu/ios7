package com.littlecheesecake.ios7;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class OsView extends View{
	private Circle[] circles;
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


	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		this.width = w;
		this.height = h;
		
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bk);
		mBitmap = Bitmap.createScaledBitmap(mBitmap, w, h, false);

		//mCanvas.setBitmap(mBitmap.copy(Bitmap.Config.ARGB_8888, true));
		loadCircles(10);
		
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas){
		synchronized(this){
			if(mBitmap != null){
				canvas.drawBitmap(mBitmap, 0, 0, null);
				
				for(int i = 0; i < 10; i++){
					int x = circles[i].x;
					int y = circles[i].y;
					int s = circles[i].size;
					circles[i].drawable.setBounds(x, y, x+s, y+s);
					circles[i].drawable.draw(canvas);
				}
			}
		}
	}
	
	
	private void loadCircles(int num){
		//TODO:load the drawables in the arraylist
		//keep in mind the top layer have smaller motion than bottom layer: design the order
		circles = new Circle[num];
		circles[0] = new Circle(getResources().getDrawable(R.drawable.c1), 1, width, height);
		circles[1] = new Circle(getResources().getDrawable(R.drawable.c2), 2, width, height);
		circles[2] = new Circle(getResources().getDrawable(R.drawable.c3), 3, width, height);
		circles[3] = new Circle(getResources().getDrawable(R.drawable.c4), 4, width, height);
		circles[4] = new Circle(getResources().getDrawable(R.drawable.c5), 5, width, height);
		circles[5] = new Circle(getResources().getDrawable(R.drawable.c1), 1, width, height);
		circles[6] = new Circle(getResources().getDrawable(R.drawable.c2), 2, width, height);
		circles[7] = new Circle(getResources().getDrawable(R.drawable.c3), 3, width, height);
		circles[8] = new Circle(getResources().getDrawable(R.drawable.c4), 4, width, height);
		circles[9] = new Circle(getResources().getDrawable(R.drawable.c5), 5, width, height);

		

	}
	
	/**
	 * Generate a set of parameters to draw the circle (random with input of width and height of canvas)
	 * @author aki
	 *
	 */
	private class Circle{
		Drawable drawable;
		int x; //x position
		int y; //y position
		int size; //size of circle
		int motion; //motion parameter
		
		public Circle(Drawable drawable, int motion, int width, int height){
			this.drawable = drawable;
			this.motion = motion;
			randamParam(width, height);		
		}
		
		public void setMotion(int motion){
			this.motion = motion;
		}
		
		private void randamParam(int width, int height){
			Random generator = new Random();
			x = generator.nextInt(width);
			y = generator.nextInt(height);
			size = width/2 - generator.nextInt(width/4);
		}
	}

}
