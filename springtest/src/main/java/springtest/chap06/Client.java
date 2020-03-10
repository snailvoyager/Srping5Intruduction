package springtest.chap06;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean, DisposableBean{
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Client.afterPropertiesSet()");
	}
	
	public void send() {
		System.out.println("Client.send() to " + host);
	}
	
	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Client.destroy()");
	}
}
