package com.bain.learn.innerclass;

public class Dreamer {
	public static void dream(Dream dream){
		dream.createIllusion();
	}
	
	public static void main(String[] args) {
		dream(new NightMare());
	}
}
