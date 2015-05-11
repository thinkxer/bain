package com.bain.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 用于生成唯一序列号
 * @author Bain
 *
 */
public class SerialGenerator {
	
	private static int serialValue = 0;
	
	private static final long startTime = System.currentTimeMillis();
	
	/**
	 * MAX_VALUE = 99999
	 */
	private static final int MAX_VALUE = 99999;

	/**
	 * 返回格式为:<br>
	 * {@link System#currentTimeMillis()}+"{@link #MAX_VALUE}以内的5位数值"<br>
	 * 的唯一序列号
	 * @return {@link Long}
	 */
	public static long generateTimeSerial(long userId) {
		return Long.parseLong(System.currentTimeMillis()
				+ addLeft0(String.valueOf(MAX_VALUE).length(),
						generateSerialValue()));
	}

	/**
	 * value左侧添加0直到value位数为digit
	 * @param digit
	 * @param value
	 * @return {@link String}
	 */
	private static String addLeft0(int digit, int value) {
		String strValue = String.valueOf(value);
		int valueLength = strValue.length();
		if (digit > valueLength) {
			for (int i = 0; i < digit - valueLength; i++) {
				strValue = "0" + strValue;
			}
		}
		return strValue;
	}

	/**
	 * 递增生成{@link #MAX_VALUE}以内的值
	 * @return {@link Integer}
	 */
	private synchronized static int generateSerialValue() {
		return serialValue > MAX_VALUE ? 0:serialValue++;
	}
	
	private class SerialThread implements Runnable{

		private int id;
		public void run() {
			for (int i = 0; i < 10000; i++) {
			System.out.println("#"+id+" \t"+generateTimeSerial(23));
			}
			System.out.println("Cost time: "+(System.currentTimeMillis()-startTime));
		}
		public SerialThread(int id) {
			this.id = id;
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		for (int i = 0; i < 10000; i++) {
			exec.execute(new SerialGenerator().new SerialThread(i));
		}
	}
}
