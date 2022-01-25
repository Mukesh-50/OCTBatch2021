package com.learnautomation.testcases;

public class Demo1 {

	public static void main(String[] args) 
	{
		String demo=Demo1.class.getClassLoader().getResource("VSCOde.PNG").getFile();

		System.out.println(demo);
		
	}

}
