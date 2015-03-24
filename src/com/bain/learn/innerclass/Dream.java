package com.bain.learn.innerclass;

public abstract class Dream {
	private String color;
	abstract void createIllusion();
	
	private class Color{
		void draw(String name){
			color = name;
		}
	}
	
	String getColor(String name){
		new Color().draw(name);
		return color;
	}
}
