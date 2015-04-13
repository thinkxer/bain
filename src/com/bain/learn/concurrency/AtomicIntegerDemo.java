package com.bain.learn.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo implements Runnable{
	
	private volatile static AtomicInteger ai = new AtomicInteger(0);

	private static int getNextSerial(){
		return ai.incrementAndGet();
	}
	
	public static long getOrderId(){
		return System.currentTimeMillis()+getNextSerial();
	}
	
	public static void main(String[] args) {
		Thread t1 = new Thread();
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
