package com.histudio.fishwinner.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;

public class MusicPlayer {

	private MediaPlayer mediaPlayer = null;
	private Context context ;
	private boolean playerSwitch = false;
	private int musicState = Constants.NON_AUDIO;
	private float volume;
	
	public MusicPlayer(Context context) {

		this.context = context;
		 //获取声音设备和音量
	     AudioManager mgr = (AudioManager)this.context.getSystemService(Context.AUDIO_SERVICE);
	     float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);
	     float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	     volume = streamVolumeCurrent/streamVolumeMax-0.1f;
	}
	
	public void play(int state){
		if(mediaPlayer == null){
			mediaPlayer = MediaPlayer.create(context, state);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setVolume(volume, volume);
			mediaPlayer.setLooping(true);
			mediaPlayer.start();
		}else {
			if(mediaPlayer.isPlaying()){
				mediaPlayer.stop();
			}
			mediaPlayer.release();
			mediaPlayer = MediaPlayer.create(context, state);
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setVolume(volume, volume);
			mediaPlayer.setLooping(true);
			mediaPlayer.start();
		}
		musicState = state;
	}
	
	public void pause(){
		
		if(mediaPlayer == null){
			System.out.println("---------media为空---------");
		}else {
			if(mediaPlayer.isPlaying()){
				mediaPlayer.pause();
				playerSwitch = false;
			}
		}
		
	}
	
	public void restart(){
		
		if(mediaPlayer == null){
			System.out.println("---------media为空---------");
			//play(musicState);
		}else {
				mediaPlayer.start();
				playerSwitch = true;
		}
		
	}
	
	public void switchMusic(int state){
		if(mediaPlayer.isPlaying()){
			mediaPlayer.stop();
		}
		mediaPlayer.release();
		mediaPlayer = MediaPlayer.create(context, state);
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setVolume(volume, volume);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();
		musicState = state;
	}
	
	public int getMusicState() {
		return musicState;
	}

	public boolean isPlayerSwitch() {
		return playerSwitch;
	}

	public void setPlayerSwitch(boolean playerSwitch) {
		this.playerSwitch = playerSwitch;
	}
	
}














