package com.learnautomation.testing;

public class ThreadLocalDemo {

	public static void main(String[] args) {
		
		ThreadLocal<String> th1=new ThreadLocal<>();
		
		th1.set("Selenium");
		
		System.out.println(th1.get());
		
		
		ThreadLocal<String> th2=new ThreadLocal<>();
		
		th2.set("Java");
		
		System.out.println(th2.get());
		
		Student s1=new Student();
		ThreadLocal<Student> th3=new ThreadLocal<>();
		th3.set(s1);
	}

}
