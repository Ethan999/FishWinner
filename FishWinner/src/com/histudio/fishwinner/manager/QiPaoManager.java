package com.histudio.fishwinner.manager;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;

import com.histudio.fishwinner.entity.QiPao;

public class QiPaoManager {

	private List<QiPao> qiPaos = new ArrayList<QiPao>();
	
	public boolean addQiPao(QiPao qiPao){
		
		if(qiPao !=null )return qiPaos.add(qiPao);
		else return false;
	}
	
	public void drawAll(Canvas c){
		
		for(QiPao qiPao : qiPaos){
			
			qiPao.render(c);
			
		}
		
	}
	
	public void updateAll(){
		
		for(QiPao qiPao : qiPaos){
			
			qiPao.update();
			
		}
		
	}
}
