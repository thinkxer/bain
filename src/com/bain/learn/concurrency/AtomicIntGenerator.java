package com.bain.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntGenerator implements Runnable {

	private static AtomicInteger ai = new AtomicInteger(0);
	private static volatile boolean canceled = false;
	private int value = 0;
	private int id;

	public AtomicIntGenerator(int i) {
		this.id = i;
	}

	// public long getUniqueLong() {
	// return System.currentTimeMillis() + generateDigitInt(5);
	// }

	public void cancel() {
		canceled = true;
	}

	/**
	 *	@Bain: I think this is much safer.
	 */
	private int generateDigitInt(int digit) {
		synchronized (ai) {
			if (ai.get() > digit) {
				ai.set(0);
			}
			value = ai.getAndIncrement();
		}
		return value;
	}

	/**
	 * @Bain: this is not safe about the if-else operation
	 * @param digit
	 * @return {@link Integer}
	 */
	// private int generateDigitInt(int digit) {
	// if(ai.get()>digit){
	// ai.set(0);
	// }
	// value = ai.getAndIncrement();
	// return value;
	// }

	/**
	 * Bain: this is not safe too because the ai.getAndIncrement() is outside of
	 * the synchronized brace
	 * 
	 * @param digit
	 * @return
	 */
	// private int generateDigitInt(int digit) {
	// synchronized (ai) {
	// if(ai.get()>digit){
	// ai.set(0);
	// }
	// }
	// value = ai.getAndIncrement();
	// return value;
	// }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "#" + id + ":" + value;
	}

	/**
	 * @Bain: This is not safe Multi-thread increase atomicinteger
	 */
	public void run() {
		while (!canceled) {
			int value = generateDigitInt(3);
			if (value > 3) {
				cancel();
			}
			System.out.println(this);
		}
	}

	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 50; i++) {
			AtomicIntGenerator aig = new AtomicIntGenerator(i);
			exec.execute(aig);
		}
	}

}
