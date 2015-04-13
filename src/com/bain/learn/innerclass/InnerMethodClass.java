package com.bain.learn.innerclass;

public class InnerMethodClass {
	public Dream getDream(){
		class AmazingDream extends Dream{

			@Override
			void createIllusion() {
				System.out.println("It's "+getColor("colorful")+" everywhere~~");
			}
			
		}
		return new AmazingDream();
	}
	
	public static void main(String[] args) {
		InnerMethodClass object = new InnerMethodClass();
		Dream dream = object.getDream();
		dream.createIllusion();
	}
}
