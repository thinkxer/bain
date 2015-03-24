package com.bain.learn.abstractclass;

public class WereWolf extends Monster {

	@Override
	void skill() {
		System.out.println("Worry attack!");
	}

	@Override
	void growl() {
		System.out.println("Ar Woooooooooooooooo...");
	}

}
