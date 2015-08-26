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
		//�ܵƵ�����
		soundMap.put("go", soundPool.load(context, R.raw.go, 1));
		//�´�С������
		soundMap.put("bs", soundPool.load(context, R.raw.bs, 1));
		//��ʤ������
		soundMap.put("win", soundPool.load(context, R.raw.win, 1));
		//ʧ������
		soundMap.put("fail", soundPool.load(context, R.raw.fail, 1));
		//����
		soundMap.put("yuwang", soundPool.load(context, R.raw.bswin, 1));
		//����
		soundMap.put("train", soundPool.load(context, R.raw.train, 1));
		//��ը
		soundMap.put("baozha", soundPool.load(context, R.raw.baozha, 1));
		//�㹳
		soundMap.put("6", soundPool.load(context, R.raw.s2, 1));
		//�Ϸ�
		soundMap.put("anjian", soundPool.load(context, R.raw.anjian, 1));
		//��ȡ�����豸������
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
















