package kh.spring.tools;

import kh.spring.interfaces.Speaker;
import kh.spring.interfaces.TV;

public class LgTV implements TV{
	
	private Speaker speaker;
	private int price;
	
	// private Speaker speaker = new GenericXmlApplicationContext().getBean(Speaker.class);
	// GenericXmlApplicationContext 이 인스턴스 자체가 스프링이고, 스프링 컨데이너를 main에서도 만들었고, 여기에서도 만들었음.
	// 서비스 운영하는데 톰캣 두번이나 켜지않는 것이랑 동일. 스프링은 한번만 생성해도 됨.

	
	public LgTV() {
		super();
	}
	

	public LgTV(Speaker speaker, int price) {
		super();
		this.speaker = speaker;
		this.price = price;
	}
	

	public Speaker getSpeaker() {
		return speaker;
	}

	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	
	
	@Override
	public void on() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void off() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void volumeUp() {
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		
	}



	
	
//	public void on() {}
//	public void off() {}
//	public void toneUp() {}
//	public void toneDown() {}
	
}
