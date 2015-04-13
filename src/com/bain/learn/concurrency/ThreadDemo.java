package com.bain.learn.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadDemo implements Runnable {

	@Override
	public void run() {
		try {
		while(true) {
			TimeUnit.MILLISECONDS.sleep(100);
			print(Thread.currentThread() + " " + this);
		}
		} catch(InterruptedException e) {
			print("sleep() interrupted");
		}
		AtomicIntegerDemo.getOrderId();
	}

	private static void print(String string) {
		System.out.println(string);
	}

	public static void main(String[] args) throws InterruptedException {
		for(int i=0;i<5;i++){
			Thread demo = new Thread(new ThreadDemo());
			demo.start();
		}
		print("All daemons started");
		TimeUnit.MILLISECONDS.sleep(175);
	}
}
