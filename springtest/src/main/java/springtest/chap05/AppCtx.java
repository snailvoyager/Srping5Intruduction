package springtest.chap05;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"springtest.chap05"})	//스캔 대상 패키지 목록
public class AppCtx {
	
	@Bean
	@Qualifier("printer")		//자동 주입 대상 Bean객체를 한정
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	public MemberSummaryPrinter memberPrinter2() {		//의존 주입하려는 Bean 객체가 두개일 때
		return new MemberSummaryPrinter();				//상속받는 동일한 Bean 객체
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter();
	}
}
