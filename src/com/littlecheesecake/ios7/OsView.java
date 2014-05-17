package com.littlecheesecake.ios7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class OsView extends View{
	private CircleLayer mLayer1;
	private CircleLayer mLayer2;
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
		loadCircles();
		
		super.onSizeChanged(w, h, oldw, oldh);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas){
		synchronized(this){
			if(mBitmap != null){
				canvas.drawBitmap(mBitmap, 0, 0, null);
				
				for(Circle c : mLayer2.getCircle()){
					c.draw(canvas, 1, 1);
				}
				for(Circle c : mLayer1.getCircle()){
					c.draw(canvas, 1, 1);
				}				
				
			}
		}
	}
	
	
	private void loadCircles(){
		//TODO:load the drawables in the arraylist
		//keep in mind the top layer have smaller motion than bottom layer: design the order
		CircleLayer.FirstLevelCircleLayer ff = new CircleLayer.FirstLevelCircleLayer(width, height, 1);
		CircleLayer.SecondLevelCircleLayer sf = new CircleLayer.SecondLevelCircleLayer(width, height, 2);
		mLayer1 = ff.createCircleLayer(getContext());
		mLayer2 = sf.createCircleLayer(getContext());

	}
	


}
