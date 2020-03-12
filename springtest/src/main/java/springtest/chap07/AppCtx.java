package springtest.chap07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)		//@Aspect 붙은 Bean객체를 찾아서 Pointcut, Around 설정을 사용
public class AppCtx {
	@Bean
	public ExeTimeAspect exeTimeAspect() {
		return new ExeTimeAspect();
	}
	
	@Bean
	public Calculator calculator() {	//AOP적용 시 RecCalculator가 상속받은 Interface를 이용해서 프록시를 생성
		return new RecCalculator();
	}
}
