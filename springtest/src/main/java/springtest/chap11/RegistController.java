package springtest.chap11;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistController {
	
	private MemberRegisterService memberRegisterService;
	
	public void setMemberRegisterService(MemberRegisterService memberRegisterService) {
		this.memberRegisterService = memberRegisterService;
	}
	
	@RequestMapping("/register/step1")
	public String handleStep1() {
		return "register/step1";
	}
	
	@PostMapping("/register/step2")
	//@RequestMapping(value="register/step2", method=RequestMethod.POST)
	public String handleStep2(@RequestParam(value="agree", defaultValue="false") Boolean agreeVal, Model model) {
		if(!agreeVal) {
			return "register/step1";
		}
		model.addAttribute("formData", new RegisterRequest());	//커맨드 객체를 사용하기 위해 모델에 전달
		return "register/step2";
	}
	
	@GetMapping("/register/step2")
	public String handleStep2() {
		return "redirect:/register/step1";
	}
	
	@PostMapping("/register/step3")
	public String handleStep3(@ModelAttribute("formData") @Valid RegisterRequest regReq, Errors errors) {		//커맨드 객체에 요청 파라미터 값을 전달
		//new RegisterRequestValidator().validate(regReq, errors);	//커맨드 객체 검증. @Valid 붙이면 검증 코드를 작성X
		if(errors.hasErrors())
			return "register/step2";
		
		try {
			memberRegisterService.regist(regReq);
			return "register/step3";				//커맨드 객체를 registerRequest로 뷰에 전달
		} catch(DuplicateMemberException ex) {
			//System.out.println(ex.getLocalizedMessage());
			errors.rejectValue("email", "duplicate");
			return "register/step2";
		}
	}
	
//	@InitBinder		//컨트롤러 범위 적용 Validator 설정
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(new RegisterRequestValidator());	//글로벌 범위 Validator 대신에 컨트롤러 범위 Validator 사용
//	}
}
