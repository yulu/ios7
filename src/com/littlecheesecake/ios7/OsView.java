package com.littlecheesecake.ios7;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.view.View;

public class OsView extends View implements SensorEventListener{
	private CircleLayer mLayer1;
	private CircleLayer mLayer2;
	private int width;
	private int height;
	
	private Bitmap mBitmap;

	private SensorManager mSensorManager;
	private float start_a = -1000;
	private float start_p = -1000;
	private int motion_x = 1;
	private int motion_y = 1;

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
		mSensorManager = (SensorManager)context.getSystemService(Context.SENSOR_SERVICE);
		resumeSensorManager();
	}
	
	public void resumeSensorManager(){
		mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_UI);
		
	}
	
	public void stopSensorManager(){
		mSensorManager.unregisterListener(this);
	}
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh){
		this.width = w;
		this.height = h;
		
		mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bk);
		mBitmap = Bitmap.createScaledBitmap(mBitmap, w, h, false);
		//mCircleBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		//mCanvas.setBitmap(mBitmap);
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
					c.draw(canvas, motion_x, motion_y);
				}
				for(Circle c : mLayer1.getCircle()){
					c.draw(canvas, motion_x, motion_y);
				}	
			}
		}
	}
	
	
	private void loadCircles(){
		//TODO:load the drawables in the arraylist
		//keep in mind the top layer have smaller motion than bottom layer: design the order
		CircleLayer.FirstLevelCircleLayer ff = new CircleLayer.FirstLevelCircleLayer(width, height, 4);
		CircleLayer.SecondLevelCircleLayer sf = new CircleLayer.SecondLevelCircleLayer(width, height, 6);
		mLayer1 = ff.createCircleLayer(getContext());
		mLayer2 = sf.createCircleLayer(getContext());

	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		synchronized(this){
			if(start_a != -1000 || start_p != -1000){			
				final float azimuth = event.values[0];
				final float pitch = event.values[1];
				
				motion_x = (int) (start_a - azimuth);
				motion_y = (int)(start_p - pitch);
				//System.err.println(motion_y);
				
			}else{
				start_a = event.values[0];
				start_p = event.values[1];
			}
				
			invalidate();
		}
		
	}
	


}
