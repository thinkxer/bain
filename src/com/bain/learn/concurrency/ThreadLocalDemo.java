package com.bain.learn.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalDemo implements Runnable{

	private int id;
	private boolean canceled = false;
	private static final int MaxValue = 999;
	
	private ThreadLocal<Integer> localValue = new ThreadLocal<Integer>(){
		protected synchronized Integer initialValue() {
			return 0;
		}
	};
	
	public ThreadLocalDemo(int id) {
		this.id = id;
		localValue.set(0);
	}
	
	private void cancel(){
		this.canceled = true;
	}
	
	@Override
	public void run() {
		while(!canceled){
			int i = localValue.get();
			localValue.set(++i);
			System.out.println(this);
			if(i>MaxValue){
				cancel();
			}
		}
	}
	
	@Override
	public String toString() {
		return "#"+id+"\t"+localValue.get();
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			ExecutorService exec = Executors.newCachedThreadPool();
			exec.execute(new ThreadLocalDemo(i));
		}
	}
}
