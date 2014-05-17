package com.littlecheesecake.ios7;

import java.util.ArrayList;

import android.content.Context;

public class CircleLayer {
	private ArrayList<Circle> circles;
	
	public CircleLayer(){
		circles = new ArrayList<Circle>();
	}
	
	public void add(Circle c){
		circles.add(c);
	}
	
	public ArrayList<Circle> getCircle(){
		return circles;
	}
	
	
	/**
	 * facotry method
	 * @author aki
	 *
	 */
	public abstract static class CircleFactory{
		protected int width;
		protected int height;
		protected int motion_level;
		
		public CircleFactory(int width, int height, int motion_level){
			this.width = width;
			this.height = height;
			this.motion_level = motion_level;

		}
		
		protected CircleLayer createCircleLayer(Context context){
			CircleLayer cLayer = new CircleLayer();
			addCircles(context, cLayer);
			
			return cLayer;
		}
		
		protected abstract void addCircles(Context context, CircleLayer clayer);
	}
	
	public static class FirstLevelCircleLayer extends CircleFactory{

		public FirstLevelCircleLayer(int width, int height, int motion_level) {
			super(width, height, motion_level);
		}

		@Override
		public void addCircles(Context context, CircleLayer cLayer) {
			Circle c1 = new Circle(context.getResources().getDrawable(R.drawable.c3), motion_level, width, height);
			Circle c2 = new Circle(context.getResources().getDrawable(R.drawable.c2), motion_level, width, height);
			Circle c3 = new Circle(context.getResources().getDrawable(R.drawable.c6), motion_level, width, height);
			Circle c4 = new Circle(context.getResources().getDrawable(R.drawable.c5), motion_level, width, height);
			
			cLayer.add(c1);
			cLayer.add(c2);	
			cLayer.add(c3);
			cLayer.add(c4);	
		}
	}
	
	public static class SecondLevelCircleLayer extends CircleFactory{

		public SecondLevelCircleLayer(int width, int height,
									int motion_level) {
			super(width, height, motion_level);
		}

		@Override
		public void addCircles(Context context, CircleLayer cLayer) {
			Circle c1 = new Circle(context.getResources().getDrawable(R.drawable.c2), motion_level, width, height);
			Circle c2 = new Circle(context.getResources().getDrawable(R.drawable.c4), motion_level, width, height);
			Circle c3 = new Circle(context.getResources().getDrawable(R.drawable.c2), motion_level, width, height);
			Circle c4 = new Circle(context.getResources().getDrawable(R.drawable.c5), motion_level, width, height);
			
			cLayer.add(c1);
			cLayer.add(c2);	
			cLayer.add(c3);
			cLayer.add(c4);	
		}
	}
}
