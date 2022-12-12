package kh.spring.endpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.common.collect.EvictingQueue;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kh.spring.configuator.WSConfiguator;

@ServerEndpoint(value = "/chat", configurator = WSConfiguator.class)
public class ChatEndpoint {

	// 접속한 사용자 Session 을 모아두는 컬렉션
		private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
		private static EvictingQueue<JsonObject> lately = EvictingQueue.create(30);
		private Gson g = new Gson();

		// 접속자의 HttpSession 객체를 저장할 멤버필드
		private HttpSession hSession;

		// WebSocket이 처음 연결 되었을 때 실행될 함수.
		@OnOpen
		public void onConnection(Session client, EndpointConfig config) {
			clients.add(client);
			this.hSession = (HttpSession)config.getUserProperties().get("hSession");

			String latelyMessages = g.toJson(lately);
			System.out.println(latelyMessages);
			
			try {
				client.getBasicRemote().sendText(latelyMessages);
			}catch(Exception e) {}
		}

		@OnMessage
		public void onMessage(String msg) {

			
			msg = msg.replace("<", "&lt;");

			JsonObject data = new JsonObject();
			data.addProperty("ip", (String)this.hSession.getAttribute("IP"));
			data.addProperty("sender", (String)this.hSession.getAttribute("loginID"));
			data.addProperty("msg", msg);
			
			lately.add(data); // 가장 최근 30개의 메세지를 보관
			
			JsonArray arr = new JsonArray();
			arr.add(data);
			
			synchronized(clients) {
				for(Session client : clients) {
					try {
						client.getBasicRemote().sendText(arr.toString());
					}catch(Exception e) {}
				}
			}
		}

		@OnClose
		public void onClose(Session client) {
			clients.remove(client);
		}

		@javax.websocket.OnError
		public void onError(Session client, Throwable t) {
			clients.remove(client);
		}

	}

	
	
	
	
	
	
	
	
	
//	// 접속한 사용자 Session을 모아두는 컬렉션
//	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<>());
//	// 동기화된 Set으로 변경 / 안정성을 가진다. / 얘가 작업하고 있을 때(쓰레드) 다른 작업 못하게 막아보림.
////	private static Set<Session> clients = new HashSet<>();
//	
//	private static EvictingQueue<JsonObject> lately = EvictingQueue.create(30);
//
//	private Gson g = new Gson();
//	
//	
//	// 접속자의 HttpSession 객체를 저장할 멤버필드
//	private HttpSession hSession; // 오토와이어드 안됨. 하면 null 임. 
//	// 오토와이어드는 스프링이 가지고 있는걸 멤버필드로 가져오는 것.
//	// 컴포넌트 스캔이 돌면서 찾는거인데 ChatEndpoint가 새로 들어온 사용자는 자기의 Endpoint가 있기때문에 
//	// 오토와이어드는 스프링이 켜질 때 한번만 동작해서 새로운 사용자한테는 오토와이어드가 적용되지않는다.
//	
//	
//	
//	
//	// client 가 접속할 때마다 Session을 만든다.
//
//	// 웹소켓이 처음 연결되었을 때 실행될 함수
//	@OnOpen
//	public void onConnection(Session client, EndpointConfig config) { // 처음 접속하면 onConnection 이 실행됨
//		// 접속자들의 Session 의 정보를 모아놔야한다.
//		// 안전하게 Set 이라는 객체를 만들어야한다.
//		// List랑 비슷한데 중복 방지하는 효과가 있다.
//		System.out.println("웹소켓 연결 확인");
//		clients.add(client);
//		
//		this.hSession = (HttpSession)config.getUserProperties().get("hSession");
//		
//		
//		String latelyMessages = g.toJson(lately);
//		
//        System.out.println("latelyMessages확인 : " + latelyMessages);
//
//		
//		try {
//			client.getBasicRemote().sendText(latelyMessages);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		// 그동안 쌓여있던 메세지를 배열 형태로 보여줌.
//		
//		
//		System.out.println(this.hSession.getAttribute("loginID") + " 가 채팅방에 입장하였습니다.");
//		
//	}
//
//	@OnMessage
//	public void onMessage(String msg) {
//
//		System.out.println("도착한 메세지 : " + msg);
//		
//		msg = msg.replace("<", "&lt;");
//		
//		
//		JsonObject data = new JsonObject();
//		data.addProperty("sender", (String) this.hSession.getAttribute("loginID"));
//		data.addProperty("ip", (String) this.hSession.getAttribute("IP"));
//		data.addProperty("msg", msg);
////		lately.add(msg); // 가장 최근 30개의 메세지를 보관
//		lately.add(data);
//
//		
//		
//		JsonArray arr = new JsonArray(); // 위에랑 짝 맞추기
//		arr.add(data);
//
//		System.out.println("data확인 : " + data);
//		System.out.println("arr확인 : " + arr);
//
//		
//		synchronized (clients) {
//			
//			for (Session client : clients) {
//				try {
//					client.getBasicRemote().sendText(arr.toString());
////					client.getBasicRemote().sendText(data.toString());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			
//			// for 문이 끝나서 onClose 되어야한다. 중간에 onClose 되면 동시성 오류 발생
//			// 중간에 누군가 나가도 for문은 유지됨.
//	
//			// throw 던지면 안됨
//			// 한명때문에 에러 생겼다고 가정했을 때 동작이 정지됨.
//		}
//	}
//
//	@OnClose
//	public void onClose(Session client) {
//		clients.remove(client); // 죽어버린 세션 버리기
//	}
//	
//	@javax.websocket.OnError
//	public void OnError(Session client, Throwable t) {
//		clients.remove(client); // 에러낸 애 쫓아내기
//	}
//	
//}