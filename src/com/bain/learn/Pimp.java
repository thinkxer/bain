package com.bain.learn;

public class Pimp {
	
	public static ServiceFactory hookerFactory = new ServiceFactory() {
		@Override
		public Service createService(String name) {
			return new Hooker(name);
		}
	};
	
	public Pimp(String number) {
		System.out.println("Yo this is "+number+", what can I do for yoooo?");
	}

	public Service callHooker(String hookerName){
		return hookerFactory.createService(hookerName);
	}
}
