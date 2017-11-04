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

import static com.example.util.Constant.*;	//��̬����

import com.example.util.Constant;


public class WelcomeView extends SurfaceView implements Callback {
	
	FuckedGameActivity activity;//activity������
	Paint paint;      	//����
	
	Bitmap logo;  		//logoͼƬ����
	
	float currentX;     //ͼƬ�滭���λ��
	float currentY;
	
	int currentAlpha;  	//��ǰ�Ĳ�͸��ֵ
	int sleepSpan=150;  //������ʱ��ms
	
	public WelcomeView(FuckedGameActivity activity){
		super(activity);
		this.activity=activity;
		this.getHolder().addCallback(this);//�����������ڻص��ӿڵ�ʵ����
		paint=new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����
		logo=PicLoadUtil.loadBM(getResources(),"welcome.png" );//����ͼƬ
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
					//��������ת
					activity.hd.sendEmptyMessage(0);
				}
			break;
		}
		return true;
	}
	
	@Override
	public void draw(Canvas canvas) {
		//���ƺ��������屳��
		paint.setColor(Color.BLACK);//���û�����ɫ
		paint.setAlpha(255);//���ò�͸����Ϊ255
		canvas.drawRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, paint);
		//����ƽ����ͼ
		if(logo==null)	return;
		paint.setAlpha(currentAlpha);
		canvas.drawBitmap(logo, currentX, currentY, paint);	
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		currentX=SCREEN_WIDTH/2-logo.getWidth()/2;
		currentY=1*SCREEN_HEIGHT/3-logo.getHeight()/2;
		
		//�����̣߳�����sleep()
		new Thread(){
			public void run() {
				
				SurfaceHolder mholder=WelcomeView.this.getHolder();//��ȡ�ص��ӿ�
				
				for(int i=255;i>-10;i-=10){
					
					if(i<0)//�����ǰ��͸����С����
					{
						i=0;//����͸������Ϊ��
					}
					currentAlpha=i;
					Canvas canvas=mholder.lockCanvas();//��ȡ����
					try {
						synchronized (mholder) //ͬ��
						{
							draw(canvas);//���л���
						}
						Thread.sleep(sleepSpan);//������ʱ��ms
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
				//�ȴ�TableBallActivity��OnCreate����Դ�����߳����
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
				//��������ת
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