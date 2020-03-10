package springtest.chap06;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean{
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {		//초기화 과정에서 실행
		// TODO Auto-generated method stub
		System.out.println("Client.afterPropertiesSet()");
	}
	
	public void send() {
		System.out.println("Client.send() to " + host);
	}
	
	@Override
	public void destroy() throws Exception {		//소멸 과정에서 실행
		// TODO Auto-generated method stub
		System.out.println("Client.destroy()");
	}
}
