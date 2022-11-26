package kh.spring.tools;

import com.google.gson.Gson;

import kh.spring.interfaces.TV;

public class SamsungTV implements TV{

	private Gson gson;
	
	public SamsungTV() {
		super();
	}

	public SamsungTV(Gson gson) {
		super();
		this.gson = gson;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		
	}

	public void checkGson() {
		System.out.println(gson);
	}


	
	
	
//	public void powerOn() {}
//	public void powerOff() {}
//	public void volumeUp() {}
//	public void volumeDown() {}
	
}
