package com.example.util;

import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBUtil {
	
	//create	
		//创建或者打开数据库
		public static SQLiteDatabase createOrOpenDatabase()
		{
			SQLiteDatabase sld=null;
			try
	    	{
				sld=SQLiteDatabase.openDatabase
		    	(
		    			"/data/data/com.example.game/gamedb",
		    			null, 							//游标工厂
		    			SQLiteDatabase.OPEN_READWRITE|SQLiteDatabase.CREATE_IF_NECESSARY //读写权限；若不存在则创建
		    	);	    		
				String sql="create table if not exists gaterecord(gate integer,lock integer,pass interger);";
		    	sld.execSQL(sql);
		    	sql="create table if not exists setting(yinyue integer);";
		    	sld.execSQL(sql);
	    	}
	    	catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
			return sld;
		}
		
		//关闭数据库的方法
	    public static void closeDatabase(SQLiteDatabase sld)
	    {
	    	try
	    	{
		    	sld.close();     		
	    	}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	    }
	    
	//insert  
	    //向gaterecord表中插入 不存在才插入
	    public static void insert(int gate,int lock,int pass)
	    {
	    	SQLiteDatabase sld=null;
	    	try
	    	{
	    			sld=createOrOpenDatabase();//打开数据库
	    			String sql="insert into gaterecord values("+gate+","+lock+","+pass+");";
	            	sld.execSQL(sql);            	
	    	}  
			catch(Exception e)
			{
				e.printStackTrace(); 
			}
	    	finally
	    	{
	    		closeDatabase(sld);
	    	}
	    }
	    
	    //向setting表中插入 不存在才插入
	    public static void insertSetting(int yinyue)
	    {
	    	SQLiteDatabase sld=null;
	    	try
	    	{
	    		sld=createOrOpenDatabase();//打开数据库
	    		String sql="insert into setting values("+yinyue+");";
	        	sld.execSQL(sql);            	
			}  
			catch(Exception e)
			{
				e.printStackTrace(); 
			}
	    	finally
	    	{
	    		closeDatabase(sld);
	    	}
		}
	    
	//update    
	    
	    //更新levelrecord表 存在才更新
	    public static void upDateLevel(int gate)
	    {
	    	SQLiteDatabase sld=null;
	    	try
	    	{
	    		sld=createOrOpenDatabase();//打开数据库
	    		String sql="update levelrecord set pass="+1+"where gate="+gate+";";
	    		sld.execSQL(sql);
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally
	    	{
	    		closeDatabase(sld);
	    	}
	    }
	    
	    //更新setting表 存在才更新
	    public static void updateSetting(int yinyue)
	    {
	    	SQLiteDatabase sld=null;
	    	try
	    	{
	    		sld=createOrOpenDatabase();//打开数据库
	    		String sql="update setting set yinyue="+yinyue+";";
	    		sld.execSQL(sql);
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	}
	    	finally
	    	{
	    		closeDatabase(sld);
	    	}
	    }

	//inquiry
	    //获取lock，记录不存在返回0
	    public static Integer getLock(int gate)  
	    {
	    	SQLiteDatabase sld=null;
	    	int lock=0;//要初始化，可能作为返回值！
	    	try
	    	{
	    		sld=createOrOpenDatabase();//创建或者打开数据库
	    		String sql="select lock from gaterecord where gate ="+gate;
	    		Cursor cur=sld.rawQuery(sql, new String[]{});//获取游标
	    		while(cur.moveToNext())//遍历结果集，找不到则不做循环！
	    		{
	    			lock=cur.getInt(0);
	    		}
	    		cur.close();        	
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	} 
	    	finally
	    	{
	    		try{closeDatabase(sld);}
	    		catch(Exception e)
	    		{e.printStackTrace();}
	    	}
	    	return lock;
	    }
	    
	    //获取yinyue,，记录不存在返回0
	    public static Integer getYinyue()  
	    {
	    	SQLiteDatabase sld=null;
	    	int yinyue2=0;
	    	try
	    	{
	    		sld=createOrOpenDatabase();//创建或者打开数据库
	    		String sql="select yinyue from setting";
	    		Cursor cur=sld.rawQuery(sql, new String[]{});//获取游标
	    		while(cur.moveToNext())//遍历结果集，找不到则不做循环！
	    		{
	    			yinyue2=cur.getInt(0);
	    		}
	    		cur.close();        	
	    	}catch(Exception e)
	    	{
	    		e.printStackTrace();
	    	} 
	    	finally
	    	{
	    		try{closeDatabase(sld);}
	    		catch(Exception e)
	    		{e.printStackTrace();}
	    	}
	    	return yinyue2;
	    }
	 
	}