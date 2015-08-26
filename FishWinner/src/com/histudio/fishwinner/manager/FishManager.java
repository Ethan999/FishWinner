package com.histudio.fishwinner.manager;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

import com.histudio.fishwinner.entity.Fish;

public class FishManager {

	private List<Fish> fishs = new ArrayList<Fish>();
	
	public boolean addFish(Fish fish){
		
		if(fish != null){
			
			return fishs.add(fish);
			
		}
		return false;
		
	}
	
	public void drawAll(Canvas c){
		
		for(Fish fish : fishs){
			fish.render(c);
		}
		
	}
	
	public void updateAll(){
		
		for(Fish fish : fishs){
			fish.update();
		}
	}
	
	
	
}












