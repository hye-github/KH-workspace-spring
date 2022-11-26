package kh.spring.factory; 

import kh.spring.interfaces.TV;
import kh.spring.tools.LgTV;
import kh.spring.tools.SamsungTV;

public class TvFactory {

	public static TV getTv(String brand) {
		
		if(brand.equals("samsung")) {
			return new SamsungTV();
		} else if(brand.equals("lg")) {
			return new LgTV();
		}
		
		return null;
	}
	
}
