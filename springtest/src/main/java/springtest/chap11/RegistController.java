package springtest.chap11;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistController {
	
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	@PostMapping("/register/step2")
	//@RequestMapping(value="register/step2", method=RequestMethod.POST)
	public String handleStep2(@RequestParam(value="agree", defaultValue="false") Boolean agreeVal) {
		if(!agreeVal) {
			return "register/step1";
		}
		return "register/step2";
	}
	
	@GetMapping("register/step2")
	public String handleStep2() {
		return "redirect:/register/step1";
	}
}
