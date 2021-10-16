package com.mongta.myspringboot;

import java.util.List;
import java.util.function.Consumer;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class Lambdatest {
	@Test
	public void iterable() throws Exception{
		//...가변적 arg 배열, .of 를 사용하면 readOnly다 add 못함!
		List<String> list = List.of("aa","bb","cc");
		list.forEach(new Consumer<String>() {
			@Override
			public void accept(String t) {
				// TODO Auto-generated method stub
				System.out.println(t);
			}
		});
		
		//람다식 표현
		list.forEach(value -> System.out.println(value));
		
		//Method Reference
		list.forEach(System.out::println);
	}
	
	@Test @Disabled
	public void lambda() throws Exception{
		//1. Anonymous InnerClass 형태로 Runnable의 run()을 재정의 하기
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName());
			}
		});
		t1.setName("몽타");
		t1.start();
		
		Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
		t2.setName("호우");
		t2.start();
		
		
	}
}
