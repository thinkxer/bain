package com.bain.learn;

public class Client {
	
	public static Pimp callPimp(String number){
		return new Pimp(number);
	}
	
	public static void main(String[] args) {
		String number = "110";
		Pimp pimp  = callPimp(number);
		Service hooker = pimp.callHooker("Lily");
		hooker.supply();
	}
}
