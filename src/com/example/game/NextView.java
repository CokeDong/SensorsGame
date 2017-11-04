package com.example.game;

import static com.example.util.Constant.*;


import com.example.util.Constant;
import com.example.util.DBUtil;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class NextView extends SurfaceView implements SurfaceHolder.Callback
{
	FuckedGameActivity fuckedGameActivity;
	Paint paint;

	public NextView(FuckedGameActivity fuckedGameActivity ) {
		super(fuckedGameActivity);
		this.fuckedGameActivity=fuckedGameActivity;
		this.getHolder().addCallback(this);
		//����ת������п�����������������Ϊ���������̺߳��ж�2����ʱ���߳��໥Ӱ�죩
		
		Constant.IF_CLOSING_SENSOR_LISTENING=false;
		
		paint=new Paint();
		paint.setAntiAlias(true);
	}

	public void draw(Canvas canvas){
		super.draw(canvas);
		canvas.translate(Constant.screenScaleResult.lucX, Constant.screenScaleResult.lucY);
		canvas.scale(Constant.screenScaleResult.ratio, Constant.screenScaleResult.ratio);
		
		//����ͼƬ
		canvas.drawBitmap(TP_ARRAY[0],xyoffset[0][0], xyoffset[0][1],paint);
		//�ڰ�ͼƬ
		canvas.drawBitmap(TP_ARRAY[7], xyoffset[7][0], xyoffset[7][1], paint);
		//��ʾ��ͼƬ
		canvas.drawBitmap(NEXTVIEW_ARRAY[LEVEL], xyoffset[7][0]+20, xyoffset[7][1]+100, paint);
		
		//come-onͼƬ
		canvas.drawBitmap(TP_ARRAY[8],xyoffset[8][0], xyoffset[8][1],paint);
		
		//��һ��ͼƬ
		canvas.drawBitmap(TP_ARRAY[25], xyoffset[25][0], xyoffset[25][1], paint);
		//ѡ��ؿ�ͼƬ
		canvas.drawBitmap(TP_ARRAY[26], xyoffset[26][0], xyoffset[26][1], paint);
	}
	
	
	public boolean onTouchEvent(MotionEvent e)
	{
		int currentNUm=e.getAction();
		float x=e.getX();
		float y=e.getY();		
		switch(currentNUm)
		{
			case MotionEvent.ACTION_DOWN:
				
				//�ؿ�ѡ��
				if(x>xyoffsetEvent[26][0]&&x<xyoffsetEvent[26][0]+TP_ARRAY[26].getWidth()*Constant.screenScaleResult.ratio&&//��������������¿�ʼ
				   y>xyoffsetEvent[26][1]&&y<xyoffsetEvent[26][1]+TP_ARRAY[26].getHeight()*Constant.screenScaleResult.ratio)
				{
					fuckedGameActivity.hd.sendEmptyMessage(2);
				}
				//��һ��
				else if(x>xyoffsetEvent[25][0]&&x<xyoffsetEvent[25][0]+TP_ARRAY[25].getWidth()*Constant.screenScaleResult.ratio&&//�������������һ��
						y>xyoffsetEvent[25][1]&&y<xyoffsetEvent[25][1]+TP_ARRAY[25].getHeight()*Constant.screenScaleResult.ratio)
				{
					if(Constant.LEVEL==4)
					{
						fuckedGameActivity.hd.sendEmptyMessage(0);//�����ù�ϲȫ��ͨ�ؽ���
					}
					else
					{
						if(DBUtil.getLock(Constant.LEVEL+1)==1)
						{
							Constant.LEVEL=Constant.LEVEL+1;
							fuckedGameActivity.hd.sendEmptyMessage(3);//������Ϸ����
						}
					}
				}
			break;
		}
		return true;
	}
	
	

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		
		
		
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
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		
	}
	
}
