package com.learnautomation.testing;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionDemo {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		TestingClass obj1=new TestingClass();
		
		Class cls=obj1.getClass();
		
		System.out.println(cls.getName());
		
		Constructor constutor=cls.getConstructor();
	
		System.out.println(constutor.getName());
	  
		Method[]allMethods=cls.getMethods();
		
		for(Method m:allMethods)
		{
			System.out.println(m.getName());
		}
		
		Field field=cls.getDeclaredField("name");
		
		System.out.println(field.get(obj1));
		
		Method m3=cls.getDeclaredMethod("showAllDetails");
		
		m3.setAccessible(true);
		
		m3.invoke(obj1);
		
	}
	
	
}
