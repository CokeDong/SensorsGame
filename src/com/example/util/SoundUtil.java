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
	
	SoundPool soundPool;//������
	HashMap<Integer, Integer> soundPoolMap;
	
	FuckedGameActivity fuckedGameActivity;
	
	public SoundUtil(FuckedGameActivity fuckedGameActivity)//������
	{
		this.fuckedGameActivity=fuckedGameActivity;
	}
	
	//��������صĳ�ʼ��
    public void initSounds()
    {
    	 //�������������
	     soundPool = new SoundPool
	     (
	    		 6, 							//ͬʱ����ಥ�ŵĸ���
	    		 AudioManager.STREAM_MUSIC,     //��Ƶ������
	    		 100							//�����Ĳ���������Ŀǰ��Ч
	     );
	     
	     //����������ԴMap	     
	     soundPoolMap = new HashMap<Integer, Integer>();   
	     //�����ص�������Դid�Ž���Map
	     soundPoolMap.put(0, soundPool.load(fuckedGameActivity, R.raw.fail_sounds, 1));
	     soundPoolMap.put(1, soundPool.load(fuckedGameActivity, R.raw.other_sound, 1));
	     soundPoolMap.put(2, soundPool.load(fuckedGameActivity, R.raw.pass_sounds, 1));
	     
	     //�м�����Ч���м���soundPoolMap.put()�������1Ϊ���ȼ���Ŀǰ�����ǡ�
	} 
       
   //������Ч�ķ���
   public void playEffectsSound(int sound, int loop) {

		   	AudioManager mgr = (AudioManager)fuckedGameActivity.getSystemService(Context.AUDIO_SERVICE);
		    float streamVolumeCurrent = mgr.getStreamVolume(AudioManager.STREAM_MUSIC);//��ǰ����   
		    float streamVolumeMax = mgr.getStreamMaxVolume(AudioManager.STREAM_MUSIC);//�������       
		    float volume = streamVolumeCurrent / streamVolumeMax;   
		    
		    soundPool.play
		    (
	    		soundPoolMap.get(sound), //������Դid
	    		volume, 				 //����������
	    		volume, 				 //����������
	    		1, 						 //���ȼ�				 
	    		loop, 					 //ѭ������ -1������Զѭ��
	    		1f					 //�ط��ٶ�0.5f��2.0f֮��
		    );

	}
   
   //�����µ�BGM
   public void play_bg_sound()
   {	
		 //ͨ��assets ��������
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
   
   //ֹͣ�ɵ�bgm
   public void stop_bg_sound()
   {
	   //if(Constant.YINYUE_OPEN && mp!=null)
	   if(mp!=null)
	   {
		   mp.stop();
		   mp.release();
		   //ע�⣬release��mp������null
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
//			Thread.sleep(expeceted_intervel_time);//����   
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