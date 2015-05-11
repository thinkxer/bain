package com.bain.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntGenerator implements Runnable {

	private static final int MAX_NUM = 99999;
	private static AtomicInteger ai = new AtomicInteger(0);
	private static Integer serial = 0;
	private static volatile boolean canceled = false;
	private int value = 0;
	private int id;
	private int count = 0;

	public AtomicIntGenerator(int i) {
		this.id = i;
	}

	public void cancel() {
		canceled = true;
	}

	/**
	 *	@Bain: I think this is much safer.
	 */
	private int generateDigitInt(int maxNum) {
//		synchronized (ai) {
//			if (ai.get() > maxNum) {
//				ai.set(0);
//			}
//			value = ai.getAndIncrement();
//		}
		synchronized (serial) {
			if(serial>maxNum){
				serial = 0;
			}
			value = serial++;
		}
		count++;
		return value;
	}

	/**
	 * @author Bain
	 * @comment: I think it will work
	 */
	private long  generateDigitLong(int maxNum){
		int i = generateDigitInt(maxNum);
		return Long.parseLong(System.currentTimeMillis()+addLeftZero(String.valueOf(maxNum).length(), i));
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
		return "#" + id + ":" + value+"\tcount:"+count;
	}

	/**
	 * @Bain: This is not safe Multi-thread increase atomicinteger
	 */
	public void run() {
		while (!canceled) {
			long num = generateDigitLong(MAX_NUM);
			if(value>MAX_NUM){
				cancel();
			}
			System.out.println(this+"\t"+num);
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
