package com.bain.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntGenerator implements Runnable {

	private static volatile AtomicInteger ai = new AtomicInteger(0);
	private static volatile boolean canceled = false;
	private static int value = 0;
	private int id;
	
	public AtomicIntGenerator(int i) {
		this.id = i;
	}
	
	public static long getUniqueLong() {
		return System.currentTimeMillis() + generateDigitInt(5);
	}

	public int getValue() {
		return ai.get();
	}

	public void cancel() {
		canceled = true;
	}

	private static int generateDigitInt(int digit) {
		ai.compareAndSet(digit, 0);
		value = ai.incrementAndGet();
		return value;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "#"+id+":"+ value;
	}

	@Override
	public void run() {
		while (!canceled) {
			System.out.println(generateDigitInt(5));
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			AtomicIntGenerator aig = new AtomicIntGenerator(i);
			exec.execute(aig);
		}
	}

}
