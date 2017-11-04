package com.example.util;

import java.io.IOException;
import java.util.HashMap;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.example.game.FuckedGameActivity;
import com.example.game.R;

public class SoundUtil
{
	public MediaPlayer mp=null;
	
	SoundPool soundPool;//声音池
	HashMap<Integer, Integer> soundPoolMap;
	
	FuckedGameActivity fuckedGameActivity;
	
	public SoundUtil(FuckedGameActivity fuckedGameActivity)//构造器
	{
		this.fuckedGameActivity=fuckedGameActivity;
	}
	
	//声音缓冲池的初始化
    public void initSounds()
    {
    	 //创建声音缓冲池
	     soundPool = new SoundPool
	     (
	    		 6, 							//同时能最多播放的个数
	    		 AudioManager.STREAM_MUSIC,     //音频的类型
	    		 100							//声音的播放质量，目前无效
	     );
	     
	     //创建声音资源Map	     
	     soundPoolMap = new HashMap<Integer, Integer>();   
	     //将加载的声音资源id放进此Map
	     soundPoolMap.put(0, soundPool.load(fuckedGameActivity, R.raw.fail_sounds, 1));
	     soundPoolMap.put(1, soundPool.load(fuckedGameActivity, R.raw.other_sound, 1));
	     soundPoolMap.put(2, soundPool.load(fuckedGameActivity, R.raw.pass_sounds, 1));
	     
	     //有几个音效就有几句soundPoolMap.put()，后面的1为优先级，目前不考虑。
	} 
       
   //播放音效的方法
   public void playEffectsSound(int sound, int loop) {

		   	AudioManager mgr = (AudioManager)fuckedGameActivity.getSystemService(Context.AUDIO_SERVICE);
		    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);//当前音量   
		    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//最大音量       
		    float volume = streamVolumeCurrent / streamVolumeMax;   
		    
		    soundPool.play
		    (
	    		soundPoolMap.get(sound), //声音资源id
	    		volume, 				 //左声道音量
	    		volume, 				 //右声道音量
	    		1, 						 //优先级				 
	    		loop, 					 //循环次数 -1带表永远循环
	    		1f					 //回放速度0.5f～2.0f之间
		    );

	}
   
   //播放新的BGM
   public void play_bg_sound()
   {	
		 //通过assets 加载音乐
	     AssetManager assetManager = fuckedGameActivity.getAssets();  
	     try {  
		     mp = new MediaPlayer();  
		     String s;
		     if(Constant.YINYUE1)
		     	{
		    	 	s="bg.mp3";
		     	}
		     	else
		     	{
		     		s="xiaoice.mp3"; 
		     	}
		     AssetFileDescriptor fileDescriptor= assetManager.openFd(s); 
		     mp.setDataSource(fileDescriptor.getFileDescriptor(),  
		     fileDescriptor.getStartOffset(),  
		     fileDescriptor.getLength()); 
		     mp.setLooping(true);
		     mp.prepare();  
		     mp.start();  
		     } 
	     catch (IOException e) 
		     {  
		      e.printStackTrace();  
		     } 
	     
   }
   
   //停止旧的bgm
   public void stop_bg_sound()
   {
	   //if(Constant.YINYUE_OPEN && mp!=null)
	   if(mp!=null)
	   {
		   mp.stop();
		   mp.release();
		   //注意，release后mp并不是null
		   mp=null;
	   }
   }
   
   
   public void lower_sound()
   {
	   if(mp!=null)
	   {
		   mp.setVolume(0.3f, 0.3f);
	   }
   }
   
   
//   public void continued_lower_sound()
//   {
//	   if(mp!=null)
//	   {	
//		   long expeceted_delay_time=5000;
//		   long expeceted_intervel_time=200;
//		   int  expected_num=(int)expeceted_delay_time/(int)expeceted_intervel_time;
//		   for(int i=0;i<expected_num;i++){
//			
//			try   
//		    {   
//		    Thread.currentThread();
//			Thread.sleep(expeceted_intervel_time);//毫秒   
//		    }   
//		    catch(Exception e){
//		    	
//		    } 
//			float sound=1-i*expeceted_intervel_time/expeceted_delay_time;
//			mp.setVolume(sound, sound);
//		   }
//		  
//	   }
//   }
   
   
   public void upper_sound()
   {
	   if(mp!=null)
	   {
		   mp.setVolume(1f, 1f);
	   }
   }
}