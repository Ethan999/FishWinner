package com.histudio.fishwinner.util;

import java.util.HashMap;
import java.util.Map;
import com.histudio.fishwinner.R;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPoolPlayer {

	private static SoundPoolPlayer instance = null;
	private Context context;
	private SoundPool soundPool = null;
	private Map<String, Integer> soundMap = null;
	private float volume;

	private SoundPoolPlayer(Context context) {

		
		this.context = context;
		soundPool = new SoundPool(100, AudioManager.STREAM_MUSIC, 100);
		soundMap = new HashMap<String, Integer>();
		//跑灯的音乐
		soundMap.put("go", soundPool.load(context, R.raw.go, 1));
		//猜大小的音乐
		soundMap.put("bs", soundPool.load(context, R.raw.bs, 1));
		//获胜的音乐
		soundMap.put("win", soundPool.load(context, R.raw.win, 1));
		//失败音乐
		soundMap.put("fail", soundPool.load(context, R.raw.fail, 1));
		//渔网
		soundMap.put("yuwang", soundPool.load(context, R.raw.bswin, 1));
		//开火车
		soundMap.put("train", soundPool.load(context, R.raw.train, 1));
		//爆炸
		soundMap.put("baozha", soundPool.load(context, R.raw.baozha, 1));
		//鱼钩
		soundMap.put("6", soundPool.load(context, R.raw.s2, 1));
		//上分
		soundMap.put("anjian", soundPool.load(context, R.raw.anjian, 1));
		//获取声音设备和音量
	     AudioManager mgr = (AudioManager)this.context.getSystemService(Context.AUDIO_SERVICE);
	     float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
	     float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	     volume = streamVolumeCurrent/streamVolumeMax;
	}
	
	public static SoundPoolPlayer getInstance(Context context) {
		if(instance == null)instance  =  new SoundPoolPlayer(context);
		return instance;
	}
	
	 public int play(String sound, int loop){
	   
	   return  soundPool.play(soundMap.get(sound), volume, volume, 1, loop, 1f);
	 }
	 
	 public int play(String sound){
		 
		 return soundPool.play(soundMap.get(sound), volume, volume, 1, 0, 1f);
		 
	 }
	 
	 
	 public void stop(int streamID){
		 
		 soundPool.stop(streamID);
	 }
	
}
















