package springtest.chap02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {		//스프링 설정 클래스로 지정
	@Bean
	public Greeter greeter() {		//메서드가 생성한 객체를 스프링이 관리하는 Bean객체로 등록
		Greeter g = new Greeter();	//객체 생성
		g.setFormat("%s, Hello World!");	//객체 초기화
		return g;
	}
}
