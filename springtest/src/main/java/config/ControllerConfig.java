package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springtest.chap11.MemberRegisterService;
import springtest.chap11.RegistController;
import springtest.chap11.survey.SurveyController;
import springtest.chap13.AuthService;
import springtest.chap13.LoginController;
import springtest.chap13.LogoutController;

@Configuration
public class ControllerConfig {
	
	@Autowired
	private MemberRegisterService memberRegSvc;
	@Autowired
	private AuthService authService;
	
	@Bean
	public RegistController registerController() {
		RegistController controller =  new RegistController();
		controller.setMemberRegisterService(memberRegSvc);		//setter 의존주입
		return controller;
	}
	
	@Bean
	public SurveyController surveyController() {
		return new SurveyController();
	}
	
	@Bean
	public LoginController loginController() {
		LoginController controller = new LoginController();
		controller.setAuthService(authService);
		return controller;
	}
	
	@Bean
	public LogoutController logoutController() {
		return new LogoutController();
	}
}
