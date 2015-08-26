package com.histudio.fishwinner.entity;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.histudio.fishwinner.game.GameObject;

public class Fish extends GameObject{

	public static final int Left2Right = 100;
	public static final int Right2Left = 200;
	
	
	private Bitmap img_fish;
	private float x;
	private float y;
	private int step = 10;
	private Random random = new Random();
	private int direction = Left2Right;
	
	public Fish(Bitmap bitmap,float x,float y) {
		this.img_fish = bitmap;
		this.x = x;
		this.y = y;
	}
	
	public Fish(Bitmap bitmap,float x,float y,int step) {
		this.img_fish = bitmap;
		this.x = x;
		this.y = y;
		this.step = step;
	}
	
	public Fish(Bitmap bitmap,float x,float y,int step,int direction) {
		this.img_fish = bitmap;
		this.x = x;
		this.y = y;
		this.step = step;
		this.direction = direction;
	}
	
	@Override
	public void render(Canvas c) {

		c.drawBitmap(img_fish, x, y, paint);
		
	}
	
	@Override
	public void update() {

		
		if (direction == Left2Right) {
			if (x > width + 50) {//有出屏幕，重新游动
				x = -random.nextInt(20);
				y = random.nextInt(600)+5;
				/*if(0 == random.nextInt(5))
					direction = (direction == Left2Right)?Right2Left:Left2Right;*/
			}
			x += random.nextInt(step) + 10;
		}else if(direction == Right2Left){
			
			if (x < -200) {//有出屏幕，重新游动
				x = width + random.nextInt(20)+300;
				y = random.nextInt(height / 2);
				/*if(0 == random.nextInt(5))
					direction = (direction == Left2Right)?Right2Left:Left2Right;*/
			}
			x -= random.nextInt(step) + 10;
			y += 5;
			
		}
		
	}
	
	//---------------getters and setters
	
	public Bitmap getImg_fish() {
		return img_fish;
	}

	public void setImg_fish(Bitmap img_fish) {
		this.img_fish = img_fish;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
}
