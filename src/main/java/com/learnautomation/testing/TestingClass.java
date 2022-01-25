package com.learnautomation.testing;

public class TestingClass 
{
	String name;
	
	public TestingClass()
	{
		name="Selenium Session";
	}
	
	
	public void showPrice()
	{
		System.out.println("Price is 200 USD");
	}
	
	public void showPrice(int numberOfStudents)
	{
		System.out.println("Price for "+numberOfStudents +"wil be "+200*numberOfStudents+"USD");
	}

	private void showAllDetails()
	{
		System.out.println("All details are below");
	}
	
}
