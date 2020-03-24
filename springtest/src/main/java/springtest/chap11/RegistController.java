package springtest.chap11;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegistController {
	
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	@RequestMapping("/register/step2")
	public String handleStep2() {
		return "register/step2";
	}
	
	@RequestMapping("/register/step3")
	public String handleStep3() {
		return "register/step3";
	}
}
