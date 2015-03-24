package com.bain.learn.abstractclass;

public class VanHelsing extends MonsterHunter {

	@Override
	void huntMonster(Monster monster) {
		monster.growl();
		monster.skill();
		System.out.println("Go die "+monster.getName()+"!!!");
	}

	public static void main(String[] args) {
		MonsterHunter hunter = new VanHelsing();
		hunter.huntMonster(new Vampire());
		hunter.huntMonster(new WereWolf());
	}
}
