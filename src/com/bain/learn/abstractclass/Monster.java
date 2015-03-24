package com.bain.learn.abstractclass;

public abstract class Monster {
	abstract void skill();
	abstract void growl();
	String getName(){
		return getClass().getSimpleName();
	}
}

