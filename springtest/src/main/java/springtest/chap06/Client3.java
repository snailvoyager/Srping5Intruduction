package springtest.chap06;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client3 implements InitializingBean, DisposableBean{
	private String host;
	
	public void setHost(String host) {
		this.host = host;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {		//초기화 과정에서 실행
		// TODO Auto-generated method stub
		System.out.println("Client3.afterPropertiesSet()");
	}
	
	public void send() {
		System.out.println("Client3.send() to " + host);
	}
	
	@Override
	public void destroy() throws Exception {		//prototype 범위의 Bean은 소멸 과정에서 실행 X
		// TODO Auto-generated method stub
		System.out.println("Client3.destroy()");
	}
}
