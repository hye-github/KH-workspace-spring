package kh.spring.tools;

import kh.spring.interfaces.Speaker;
import kh.spring.interfaces.TV;

public class LgTV implements TV{
	
	private Speaker speaker;
	private int price;
	
	// private Speaker speaker = new GenericXmlApplicationContext().getBean(Speaker.class);
	// GenericXmlApplicationContext �� �ν��Ͻ� ��ü�� �������̰�, ������ �����̳ʸ� main������ �������, ���⿡���� �������.
	// ���� ��ϴµ� ��Ĺ �ι��̳� �����ʴ� ���̶� ����. �������� �ѹ��� �����ص� ��.

	
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
