package com.bain.learn.innerclass;

public class TestBed {
	public void testBed(){
		System.out.println("This bed is comfortable");
	}
	
	static class Tester{
		public static void main(String[] args) {
			TestBed testBed = new TestBed();
			testBed.testBed();
		}
	}
}
