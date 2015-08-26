package com.histudio.fishwinner.util;

import android.graphics.Bitmap;


public class HandleTouch {

	
	public static final int DOWN = 0;
	public static final int UP = 1;
	public static final int MOVE = 2;
	
	private static boolean isDown = false;
	private static boolean isUp   = false;
	private static boolean isMove = false;

	private static float x_down,y_down;
	private static float x_up,y_up;
	private static float x_move,y_move;
	
	public static void onTouch(int action ,float x,float y){
		
		switch (action) {
		case DOWN:

			isDown = true;
			x_down = x;
			y_down = y;

			break;

		case UP:

			isUp = true;
			x_up = x;
			y_up = y;
			break;

		case MOVE:
			isMove = true;
			x_move = x;
			y_move = y;
			break;

		}
	
	}
	
	
	public static boolean isTouchRect(int action,float x, float y,float width, float height) {
		
		switch (action) {
		case DOWN:
			
			if(isDown){
				if (x_down > x && x_down < x + width && y_down > y&& y_down < y + height) {
					isDown = false;
					return true;
				}
			}
			break;

		case UP:
			
			if(isUp){
				if (x_up > x && x_up < x + width && y_up > y&& y_up < y + height) {
					isUp = false;
					return true;
				}
			}
			
			break;
			
		case MOVE:

			if (isMove) {
				if (x_move > x && x_move < x + width && y_move > y&& y_move < y + height) {
					isMove = false;
					return true;
				}
			}
				
			break;
		}
		
		return false;
	}
	
	
	public static boolean isTouchBitmap(Bitmap bitmap,float x, float y){
		
		return isTouchRect(DOWN, x, y, bitmap.getWidth(), bitmap.getHeight());
		
	}
	
	public static boolean isTouchBitmap(int action,Bitmap bitmap,float x, float y){
		
		return isTouchRect(action, x, y, bitmap.getWidth(), bitmap.getHeight());
		
	}
	
	
	
}
























