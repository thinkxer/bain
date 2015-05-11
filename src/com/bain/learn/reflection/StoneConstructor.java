package com.bain.learn.reflection;

import java.lang.reflect.Constructor;

public class StoneConstructor {
	
	public static void main(String[] args) {
		Class<?> c;
		Stone stone = null;
		try {
			c = Class.forName("com.bain.learn.reflection.Stone");
			Constructor<?> constructor = c.getDeclaredConstructor(long.class);
			stone = (Stone) constructor.newInstance(2134l);
			System.out.println(stone.getAge());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
