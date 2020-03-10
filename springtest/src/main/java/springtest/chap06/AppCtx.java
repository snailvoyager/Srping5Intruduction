package springtest.chap06;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppCtx {
	@Bean
	public Client client() {
		Client client = new Client();
		client.setHost("host");
		//client.afterPropertiesSet();	초기화가 두번 호출되므로 사용X
		return client;
	}
	
	@Bean(initMethod="connect", destroyMethod="close")
	public Client2 client2() {
		Client2 client = new Client2();
		client.setHost("host");
		return client;
	}
	
	@Bean
	@Scope("prototype")		//Bean을 prototype으로 지정
	public Client3 client3() {
		Client3 client = new Client3();
		client.setHost("host");
		return client;
	}
}
