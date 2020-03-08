package springtest.chap04;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppCtx {
	@Bean
	public MemberDao memberDao() {	//@Bean 메서드는 한개의 객체만 생성. 싱글톤
		return new MemberDao();		//항상 같은 객체를 리턴
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService();
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		//pwdSvc.setMemberDao(memberDao());		//의존 주입하지 않아도 스프링이 자동 주입
		return pwdSvc;
	}
	
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
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		//infoPrinter.setMemberDao(memberDao());
		//infoPrinter.setPrinter(memberPrinter());
		return infoPrinter;
	}
}
