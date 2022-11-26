package kh.spring.controllers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import kh.spring.interfaces.TV;
import kh.spring.tools.SamsungTV;

public class HomeController {
	public static void main(String[] args) {
		
//		SamsungTV tv = new SamsungTV();
//		tv.powerOn();
//		tv.volumeUp();
//		tv.powerOff();
		
		// ���������� ���̰� ���յ��� ���߱� ����
		
		// ������ Ȱ�� - ���� �������� tv�� ��Ȱ��
		// interface Ȱ�� - �޼��� �̸��� ���� ���� ����
		
//		LgTV tv = new LgTV();
//		TV tv = new LgTV();
//		TV tv = TvFactory.getTv("samsung");
//		TV tv = TvFactory.getTv(args[0]);
		
		
		ApplicationContext ctx = new GenericXmlApplicationContext("context.xml");
		// Spring Container ����
//		ctx.getBean("tv"); // "��! ������ Tv id ���� �ν��Ͻ� �� ��"
		// Ÿ���� ���� ���� ������Ʈ Ÿ������ �����´�. ĳ���� ������Ѵ�.

//		TV tv = (SamsungTV)ctx.getBean("tv");

		TV tv = ctx.getBean(TV.class); // Spring Container ���� tv ��� id �� ���� �ν��Ͻ� ��û
		// getBean = Lookup �̶� ���
		System.out.println(tv.getClass());
		((SamsungTV)tv).checkGson();
		
		
		
//		tv.volumeUp();
		
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class); // Spring Container ���� ������� �ֵ��� �̱������� �Ǿ��ִ�.
		
//		tv.on();
//		tv.volumeUp();
//		tv.off();
			
		
		
	}
}

