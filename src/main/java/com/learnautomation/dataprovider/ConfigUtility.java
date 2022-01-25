package com.learnautomation.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtility 
{
	
	static Properties pro;
	
	public static String getProperty(String key)
	{
		createInstance();
		return pro.getProperty(key);
	}
	
	public static Properties createInstance()
	{
		
		if(pro==null)
		{
			System.out.println("LOG:INFO - Creating new object of properties file");
			pro=new Properties();
			
			try 
			{
				pro.load(new FileInputStream(new File(System.getProperty("user.dir")+"/Config/Config.properties")));
			} 
			catch (IOException e) 
			{
				System.out.println("Could not load the file");
			}
			
			return pro;
		}
		else
		{
			System.out.println("LOG:INFO - Existing object found - reusing objects");
			
			return pro;
		}
	}
	

}
