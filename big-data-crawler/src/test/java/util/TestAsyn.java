package util;

import org.springframework.scheduling.annotation.Async;

public class TestAsyn {
	
	public static void main(String[] args) {
		testAsyn();
		for (int i = 0; i < 1000; i++) {
			System.out.println("main:"+i);
		}
	}
	
	@Async
	public static void testAsyn() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("Asyn:"+i);
		}
	}

}
