package com.bain.learn.enumeration;

public class EnumTest {
	
	Week day;

	public EnumTest(Week day){
		this.day = day;
	}
	
	@Override
	public String toString(){
		return day.toString();
	}
	
	public static void main(String[] args) {
		for(Week day:Week.values()){
			System.out.println(day.name()+" "+day.compareTo(Week.MON)+" "+day.ordinal()+" "+ day.toString() +" "+day.valueOf(day.toString()));
		}
		EnumTest test = new EnumTest(Week.SAT);
		System.out.println(test.day);
	}

}
