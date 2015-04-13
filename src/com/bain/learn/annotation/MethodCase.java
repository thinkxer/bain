package com.bain.learn.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodCase {

	@MethodInfo(id=1, description="This is a method annotation example.") 
	public void action(String name, int id){
		System.out.println(MethodCase.class.getSimpleName()+".action() name="+name+" id="+id);
	}
	
	public static void main(String[] args) {
		for(Method method:MethodCase.class.getDeclaredMethods()){
			MethodInfo info = method.getAnnotation(MethodInfo.class);
			if(info!=null){
				System.out.println("id="+info.id()+" description=\""+info.description()+"\"");
				try {
					method.invoke(new MethodCase(), "Bain", 0);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
