package com.bain.learn.factory;

public class Hooker implements Service{

	public Hooker(String name) {
		System.out.println("Hi my name is "+name+".");
	}

	@Override
	public void supply() {
		System.out.println("Let's do this baby.");
	}
	
}
