package springtest.chap06;

public class Client2 {
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void connect() {		//초기화 과정에서 실행될 메서드
		System.out.println("Client2.connect()");
	}
	
	public void send() {
		System.out.println("Client2.send()");
	}
	
	public void close() {		//소멸 과정에서 실행될 메서드
		System.out.println("Client2.close()");
	}
}
