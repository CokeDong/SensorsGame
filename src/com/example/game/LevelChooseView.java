package com.example.game;


import static com.example.util.Constant.*;



import com.example.util.Constant;
import com.example.util.DBUtil;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LevelChooseView extends SurfaceView implements SurfaceHolder.Callback
{
	FuckedGameActivity fuckedGameActivity;
	Paint paint;

	public LevelChooseView(FuckedGameActivity fuckedGameActivity ) {
		super(fuckedGameActivity);
		this.fuckedGameActivity=fuckedGameActivity;
		this.getHolder().addCallback(this);
		
		Constant.IF_CLOSING_SENSOR_LISTENING=false;
		
		paint=new Paint();
		paint.setAntiAlias(true);
	}

	public void draw(Canvas canvas){
		super.draw(canvas);
		canvas.translate(Constant.screenScaleResult.lucX, Constant.screenScaleResult.lucY);
		canvas.scale(Constant.screenScaleResult.ratio, Constant.screenScaleResult.ratio);
		
		
		//背景图片
		canvas.drawBitmap(TP_ARRAY[6],xyoffset[6][0], xyoffset[6][1],paint);	
		//第一关
		canvas.drawBitmap(TP_ARRAY[11],xyoffset[11][0], xyoffset[11][1],paint);
		//第二关
		canvas.drawBitmap(TP_ARRAY[13],xyoffset[13][0], xyoffset[13][1],paint);
		if(DBUtil.getLock(1)==0)//静态类可以直接用类名调用
		{
			canvas.drawBitmap(TP_ARRAY[9],xyoffset[13][0]+25, xyoffset[13][1]+20,paint);
		}
		//第三关
		canvas.drawBitmap(TP_ARRAY[15],xyoffset[15][0], xyoffset[15][1],paint);
		if(DBUtil.getLock(2)==0)
		{
			canvas.drawBitmap(TP_ARRAY[9],xyoffset[15][0]+25, xyoffset[15][1]+20,paint);
		}
		//第四关
		canvas.drawBitmap(TP_ARRAY[19],xyoffset[19][0], xyoffset[19][1],paint);
		if(DBUtil.getLock(3)==0)
		{
			canvas.drawBitmap(TP_ARRAY[9],xyoffset[19][0]+25, xyoffset[19][1]+20,paint);
		}
		//第五关
		canvas.drawBitmap(TP_ARRAY[22],xyoffset[22][0], xyoffset[22][1],paint);
		if(DBUtil.getLock(4)==0)
		{
			canvas.drawBitmap(TP_ARRAY[9],xyoffset[22][0]+25, xyoffset[22][1]+20,paint);
		}
		
		//返回按键
		canvas.drawBitmap(TP_ARRAY[10],xyoffset[10][0], xyoffset[10][1],paint);
		
	}
	public boolean onTouchEvent(MotionEvent e)
	{
		int currentNUm=e.getAction();
		float x=e.getX();
		float y=e.getY();		
		switch(currentNUm)
		{
			case MotionEvent.ACTION_DOWN:
				
				if(x>xyoffsetEvent[11][0]&&x<xyoffsetEvent[11][0]+TP_ARRAY[11].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的是第一关
					y>xyoffsetEvent[11][1]&&y<xyoffsetEvent[11][1]+TP_ARRAY[11].getHeight()*Constant.screenScaleResult.ratio)
				{
					Constant.LEVEL=0;
					fuckedGameActivity.hd.sendEmptyMessage(3);//进入第一关
				}
				else if(x>xyoffsetEvent[13][0]&&x<xyoffsetEvent[13][0]+TP_ARRAY[13].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的是第二关
						y>xyoffsetEvent[13][1]&&y<xyoffsetEvent[13][1]+TP_ARRAY[13].getHeight()*Constant.screenScaleResult.ratio)
					{
						if(DBUtil.getLock(1)==1)
						{
							Constant.LEVEL=1;
							fuckedGameActivity.hd.sendEmptyMessage(3);//进入第二关
						}
					}
				else if(x>xyoffsetEvent[15][0]&&x<xyoffsetEvent[15][0]+TP_ARRAY[15].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的是第三关
						y>xyoffsetEvent[15][1]&&y<xyoffsetEvent[15][1]+TP_ARRAY[15].getHeight()*Constant.screenScaleResult.ratio)
					{
						if(DBUtil.getLock(2)==1)
						{
							Constant.LEVEL=2;
							fuckedGameActivity.hd.sendEmptyMessage(3);//进入第三关
						}
					}
				else if(x>xyoffsetEvent[19][0]&&x<xyoffsetEvent[19][0]+TP_ARRAY[19].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的是第三关
						y>xyoffsetEvent[19][1]&&y<xyoffsetEvent[19][1]+TP_ARRAY[19].getHeight()*Constant.screenScaleResult.ratio)
					{
						if(DBUtil.getLock(3)==1)
						{
							Constant.LEVEL=3;
							fuckedGameActivity.hd.sendEmptyMessage(3);//进入第四关
						}
					}
				else if(x>xyoffsetEvent[22][0]&&x<xyoffsetEvent[22][0]+TP_ARRAY[22].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的是第三关
						y>xyoffsetEvent[22][1]&&y<xyoffsetEvent[22][1]+TP_ARRAY[22].getHeight()*Constant.screenScaleResult.ratio)
					{
						if(DBUtil.getLock(4)==1)
						{
							Constant.LEVEL=4;
							fuckedGameActivity.hd.sendEmptyMessage(3);//进入第五关
						}
					}
				else if(x>xyoffsetEvent[10][0]&&x<xyoffsetEvent[10][0]+TP_ARRAY[10].getWidth()*Constant.screenScaleResult.ratio&&//如果差模的是返回按钮
						y>xyoffsetEvent[10][1]&&y<xyoffsetEvent[10][1]+TP_ARRAY[10].getHeight()*Constant.screenScaleResult.ratio)
					{
						
						fuckedGameActivity.hd.sendEmptyMessage(0);//返回到主界面
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
