package com.bain.learn.innerclass;

public class AnonymousInnerClass {
	
	//param must be final or wrong
	public Dream getDream(final String param){
		//Anonymous inner class
		return new Dream(){

			@Override
			void createIllusion() {
				System.out.println("Where am I? A "+param+"dream?");
			}
			
		};
	}
	
	public static void main(String[] args) {
		AnonymousInnerClass object = new AnonymousInnerClass();
		Dream dream = object.getDream("sad");
		dream.createIllusion();
	}
}
