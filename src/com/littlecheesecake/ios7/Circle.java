package com.littlecheesecake.ios7;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

/**
 * Generate a circle with randomly selected x, y and size
 * @author aki
 *
 */

public class Circle{
	private Drawable drawable;
	private int x; //x position
	private int y; //y position
	private int size; //size of circle
	private int motion_level;
	
	public Circle(Drawable drawable, int motion_level, int width, int height){
		this.drawable = drawable;
		this.motion_level = motion_level;
		randamParam(width, height);		
	}
	
	/**
	 * Draw the circle on canvas
	 * @param canvas
	 * @return 
	 */
	public void draw(Canvas canvas, int motion_x, int motion_y){
		int input_x = x + motion_x*motion_level;
		int input_y = y + motion_y*motion_level;
		drawable.setBounds(input_x, input_y, input_x+size, input_y+size);
		drawable.draw(canvas);
	}
	
	private void randamParam(int width, int height){
		Random generator = new Random();
		x = generator.nextInt(width);
		y = generator.nextInt(height);
		size = width/2 - generator.nextInt(width/4);
	}
}
