package kh.spring.configuator;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

public class WSConfiguator extends Configurator{

	@Override // 핸드쉐이크 과정
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {

		try {
			HttpSession session = (HttpSession)request.getHttpSession();
			sec.getUserProperties().put("hSession", session);
		}catch(Exception e) {}
	
	}	
	
}
