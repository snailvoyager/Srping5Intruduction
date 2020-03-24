package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springtest.chap11.RegistController;

@Configuration
public class ControllerConfig {
	@Bean
	public RegistController registerController() {
		return new RegistController();
	}
}
