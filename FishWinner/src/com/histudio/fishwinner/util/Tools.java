package com.histudio.fishwinner.util;

public class Tools {

	private static long time = 0;
	public static void delayTime(int delay){
		
		while (true) {
			
			if(time == 0)time = System.currentTimeMillis();
			if(System.currentTimeMillis() - time >= delay){
				
				time = 0;
				return;
			}
		}
		
	}
	
}
