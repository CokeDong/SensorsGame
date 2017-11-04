package com.example.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import com.example.util.PicLoadUtil;

import static com.example.util.Constant.*;	//静态导入

import com.example.util.Constant;


public class WelcomeView extends SurfaceView implements Callback {
	
	FuckedGameActivity activity;//activity的引用
	Paint paint;      	//画笔
	
	Bitmap logo;  		//logo图片引用
	
	float currentX;     //图片绘画起点位置
	float currentY;
	
	int currentAlpha;  	//当前的不透明值
	int sleepSpan=150;  //动画的时延ms
	
	public WelcomeView(FuckedGameActivity activity){
		super(activity);
		this.activity=activity;
		this.getHolder().addCallback(this);//设置生命周期回调接口的实现者
		paint=new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿
		logo=PicLoadUtil.loadBM(getResources(),"welcome.png" );//加载图片
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e)
	{
		int currentNUm=e.getAction();	
		switch(currentNUm)
		{
			case MotionEvent.ACTION_DOWN:
				while(!LOAD_FINISH)
				{
					try {
						Thread.sleep(10);
					} catch (InterruptedException ee) {
						ee.printStackTrace();
					}
				}
				if(!WELCOME_VIEW_ONCE){
					
					WELCOME_VIEW_ONCE=true;
					//主界面跳转
					activity.hd.sendEmptyMessage(0);
				}
			break;
		}
		return true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		//绘制黑填充矩形清背景
		paint.setColor(Color.BLACK);//设置画笔颜色
		paint.setAlpha(255);//设置不透明度为255
		canvas.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, paint);
		//进行平面贴图
		if(logo==null)	return;
		paint.setAlpha(currentAlpha);
		canvas.drawBitmap(logo, currentX, currentY, paint);	
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		currentX=SCREEN_WIDTH/2-logo.getWidth()/2;
		currentY=1*SCREEN_HEIGHT/3-logo.getHeight()/2;
		
		//创建线程，可以sleep()
		new Thread(){
			public void run() {
				
				SurfaceHolder mholder=WelcomeView.this.getHolder();//获取回调接口
				
				for(int i=255;i>-10;i-=10){
					
					if(i<0)//如果当前不透明度小于零
					{
						i=0;//将不透明度置为零
					}
					currentAlpha=i;
					Canvas canvas=mholder.lockCanvas();//获取画布
					try {
						synchronized (mholder) //同步
						{
							draw(canvas);//进行绘制
						}
						Thread.sleep(sleepSpan);//动画的时延ms
					} catch (Exception e) {
						e.printStackTrace();
					}
					finally
					{
						if(canvas!=null)
						{
							mholder.unlockCanvasAndPost(canvas);
						}
					}
					
				}
				//等待TableBallActivity的OnCreate中资源加载线程完成
				while(!LOAD_FINISH)
				{
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(!WELCOME_VIEW_ONCE){
					
				WELCOME_VIEW_ONCE=true;
				//主界面跳转
				activity.hd.sendEmptyMessage(0);
				}
			}
		}.start();

	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

}