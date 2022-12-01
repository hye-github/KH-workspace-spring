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

		// 유지보수를 높이고 결합도를 낮추기 위함

		// 다형성 활용 - 같은 변수명인 tv의 재활용
		// interface 활용 - 메서드 이름에 대한 통일 강요

//		LgTV tv = new LgTV();
//		TV tv = new LgTV();
//		TV tv = TvFactory.getTv("samsung");
//		TV tv = TvFactory.getTv(args[0]);


		ApplicationContext ctx = new GenericXmlApplicationContext("context.xml");
		// Spring Container 생성
//		ctx.getBean("tv"); // "야! 스프링 Tv id 가진 인스턴스 나 줘"
		// 타입이 뭔지 몰라서 오브젝트 타입으로 가져온다. 캐스팅 해줘야한다.

//		TV tv = (SamsungTV)ctx.getBean("tv");

		TV tv = ctx.getBean(TV.class); // Spring Container 에게 tv 라는 id 를 가진 인스턴스 요청
		// getBean = Lookup 이랑 비슷
		System.out.println(tv.getClass());
		((SamsungTV)tv).checkGson();

//		tv.volumeUp();

//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class);
//		tv = ctx.getBean(TV.class); // Spring Container 으로 만들어진 애들은 싱글턴으로 되어있다.

//		tv.on();
//		tv.volumeUp();
//		tv.off();



	}
}

