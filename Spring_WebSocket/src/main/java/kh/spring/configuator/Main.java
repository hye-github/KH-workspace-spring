package kh.spring.configuator;

import com.google.common.collect.EvictingQueue;

public class Main {
	public static void main(String[] args) {

		
		// 이거 활용하면 가장 30개만 보이기 유지됨
		EvictingQueue<String> queue = EvictingQueue.create(3);
		
		queue.add("Apple");
		queue.add("Orange");
		queue.add("Mango");
		System.out.println(queue);
		queue.add("Grape");
		System.out.println(queue);
		
	}
}
