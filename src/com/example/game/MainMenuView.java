package com.example.game;

import static com.example.util.Constant.*;


import com.example.util.Constant;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainMenuView extends SurfaceView implements SurfaceHolder.Callback{
	FuckedGameActivity fuckedGameActivity;//获取activity
	Paint paint;//创建画笔
	
	//构造器
	public MainMenuView(FuckedGameActivity fuckedGameActivity)
	{ 
		super(fuckedGameActivity);
		this.fuckedGameActivity=fuckedGameActivity;
		this.getHolder().addCallback(this);//设置生命周期回调接口的实现者
		paint=new Paint();
		paint.setAntiAlias(true);
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		canvas.translate(Constant.screenScaleResult.lucX, Constant.screenScaleResult.lucY);
		canvas.scale(Constant.screenScaleResult.ratio, Constant.screenScaleResult.ratio);
		
		//绘制背景
		canvas.drawBitmap(TP_ARRAY[6], xyoffset[6][0], xyoffset[6][1],paint);
		//绘制名字
		canvas.drawBitmap(TP_ARRAY[1], xyoffset[1][0], xyoffset[1][1],paint);
		//绘制play按钮
		canvas.drawBitmap(TP_ARRAY[2], xyoffset[2][0], xyoffset[2][1],paint);
		//绘制声音按钮
		if(YINYUE_OPEN)//如果音乐开
		{
			canvas.drawBitmap(TP_ARRAY[3],xyoffset[3][0], xyoffset[3][1],paint);
		}
		else
		{
			canvas.drawBitmap(TP_ARRAY[4],xyoffset[4][0], xyoffset[4][1],paint);
		}
		//绘制唐老鸭图片
		canvas.drawBitmap(TP_ARRAY[5], xyoffset[5][0], xyoffset[5][1],paint);
	}
	
	//设置监听
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		int currentNum=e.getAction();
		float x=e.getX();
		float y=e.getY();		
		switch(currentNum)
		{
			case MotionEvent.ACTION_DOWN:
				//play按钮
				if(x>xyoffsetEvent[2][0]&&x<xyoffsetEvent[2][0]+TP_ARRAY[2].getWidth()*Constant.screenScaleResult.ratio&&
				   y>xyoffsetEvent[2][1]&&y<xyoffsetEvent[2][1]+TP_ARRAY[2].getHeight()*Constant.screenScaleResult.ratio)
				{
					fuckedGameActivity.hd.sendEmptyMessage(2);//去关卡选择界面
					
				}
				//声音按钮
				else if(x>xyoffsetEvent[3][0]&&x<xyoffsetEvent[3][0]+TP_ARRAY[3].getWidth()*Constant.screenScaleResult.ratio&&
					y>xyoffsetEvent[3][1]&&y<xyoffsetEvent[3][1]+TP_ARRAY[3].getHeight()*Constant.screenScaleResult.ratio)
				{
					if(Constant.YINYUE_OPEN)//如果音乐开
					{
						this.fuckedGameActivity.soundutil.stop_bg_sound();
						Constant.YINYUE_OPEN=!Constant.YINYUE_OPEN;
					}
					else if(!Constant.YINYUE_OPEN)//如果音乐关
					{
						this.fuckedGameActivity.soundutil.play_bg_sound();
						Constant.YINYUE_OPEN=!Constant.YINYUE_OPEN;
					}
					this.repaint();
					
				}
				
				
			break;
		}
		return true;
	}
	

	public void surfaceCreated(SurfaceHolder holder) {
		
		//播放音乐（BGM）
		if(Constant.YINYUE_OPEN)//如果声音按钮打开
		{
			if(Constant.FROM_START)//如果需要重新播放
			{
				fuckedGameActivity.soundutil.stop_bg_sound();//停止播放背景音乐
				fuckedGameActivity.soundutil.play_bg_sound();//开始停止播放背景音乐
			}
			
		}
		Constant.FROM_START=false;
		
		//绘画
		Canvas canvas=holder.lockCanvas();
		try
		{
			synchronized(holder)
			{
				draw(canvas);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(canvas!=null)
			{
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
	
	
	public void repaint()
	{
		SurfaceHolder holder=this.getHolder();
		Canvas canvas = holder.lockCanvas();//获取画布
		try{
				synchronized(holder){
				draw(canvas);//绘制 
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}
	
	
	
	
}
