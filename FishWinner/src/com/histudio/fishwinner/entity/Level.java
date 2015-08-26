package com.histudio.fishwinner.entity;

public class Level {

	public static int[] targets = {
			1000,			
			1500,				
			2000,				
			3000,				
			4000,				
			5000,				
			6500,				
			8000,				
			10000,				
			12000,				
			15000,				
			30000				

	};
	
	
	public static int getTarget(int level){
		
		if(level > 0 && level < 13){
			return targets[level - 1];
		}
		return targets[0];
		
	}
	
	
}
