package config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import interceptor.AuthCheckInterceptor;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer{
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/view/", ".jsp");
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {	//컨트롤러 구현없이 요청경로와 뷰 이름을 연결
		// TODO Auto-generated method stub
		registry.addViewController("/main").setViewName("main");
	}
	
	@Bean
	public MessageSource messageSource() {		//메시지 파일에서 값을 읽어오는 빈
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("message.label");	//src/main/resource/message/label.properties를 읽어온다
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(authCheckInterceptor()).addPathPatterns("/edit/**");	//인터셉터 적용할 경로 지정
	}
	
	@Bean
	public AuthCheckInterceptor authCheckInterceptor() {
		return new AuthCheckInterceptor();
	}
	
//	@Override
//	public Validator getValidator() {	//글로벌 범위 Validator 구현
//		// TODO Auto-generated method stub
//		return new RegisterRequestValidator();
//	}
}
