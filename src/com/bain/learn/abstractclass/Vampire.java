package com.bain.learn.abstractclass;

public class Vampire extends Monster {

	@Override
	void skill() {
		System.out.println("Suck blood attack!!!");
	}

	@Override
	void growl() {
		System.out.println("Heeeeeeeeeeeeeeee...");
	}

}
