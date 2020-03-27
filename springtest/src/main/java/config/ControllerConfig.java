package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springtest.chap11.MemberRegisterService;
import springtest.chap11.RegistController;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberRegisterService memberRegSvc;
	
	@Bean
	public RegistController registerController() {
		RegistController controller =  new RegistController();
		controller.setMemberRegisterService(memberRegSvc);		//setter 의존주입
		return controller;
	}
}
