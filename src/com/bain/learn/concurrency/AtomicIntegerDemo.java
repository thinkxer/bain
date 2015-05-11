package com.bain.learn.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo implements Runnable{
	
	private int id;
	private static boolean canceled = false;
	private static AtomicInteger ai = new AtomicInteger(0);
	private String value;
	
	public AtomicIntegerDemo(int id) {
		this.id = id;
	}

	private static int getNextSerial(){
		return ai.incrementAndGet();
	}
	
	public static long getOrderId(){
		return System.currentTimeMillis()+getNextSerial();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread(new AtomicIntegerDemo(i));
			t.start();
		}
	}

	@Override
	public void run() {
		while(!canceled){
			value = addLeftZero(10, getNextSerial());
			System.out.println(this);
		}
	}
	
	@Override
	public String toString() {
		return "#"+id+"\t "+value;
	}
	
	private void cancel(){
		canceled = true;
	}
	
	private String addLeftZero(int digit, int num){
		String str = String.valueOf(num);
		int length = str.length();
		if (length < digit) {
			for (int i = 0; i < digit - length; i++) {
				str = "0" + str;
			}
		}
		if(str.length()>digit){
			cancel();
		}
		return str;
	}
	
}
