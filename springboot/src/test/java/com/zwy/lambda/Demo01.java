package com.zwy.lambda;

import java.awt.Button;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class Demo01 {

	
	public static void main(String[] args) {
		String name = getUserName();
		Button btn = new Button();
		btn.addActionListener(event -> System.out.println("hi" + name));
		Predicate<Integer> atLeast5 = x -> x > 5;
		System.out.println(atLeast5.test(6));
		
		BinaryOperator<Long> addLongs = (x, y) -> x + y;
		System.out.println(addLongs.apply(3L, 5L));
	}

	public static String getUserName() {
		return "zhangsan";
	}
}
