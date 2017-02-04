package com.yuhi.sys.test;



public class Provider {

	public static void main(String[] args) throws Exception {
//		ClassPathXmlApplicationContext context = new Cl assPathXmlApplicationContext(
//				new String[] { "spring-context.xml" });
//		context.start();
//		System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
		com.alibaba.dubbo.container.Main.main(args);
	}
}