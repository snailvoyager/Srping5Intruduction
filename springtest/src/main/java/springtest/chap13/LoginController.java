package springtest.chap13;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springtest.chap11.WrongIdPasswordException;

@Controller
@RequestMapping("/login")
public class LoginController {

	private AuthService authService;
	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}
	
	@GetMapping
	public String form(LoginCommand loginCommand, @CookieValue(value="REMEMBER", required=false) Cookie rCookie) {	//쿠키를 파라미터로 전달 받는다
		if(rCookie != null) {
			loginCommand.setEmail(rCookie.getValue());
			loginCommand.setRememberEmail(true);
		}
		return "login/loginForm";
	}
	
	@PostMapping
	public String submit(LoginCommand loginCommand, Errors errors, HttpSession session, HttpServletResponse response) {
		new LoginCommandValidator().validate(loginCommand, errors);
		if(errors.hasErrors()) {
			return "login/loginForm";
		}
		
		try {
			AuthInfo authInfo = authService.authenticate(loginCommand.getEmail(), loginCommand.getPassword());
			session.setAttribute("authInfo", authInfo); 	//인증정보를 세션에 담는다
			
			Cookie rememberCookie = new Cookie("REMEMBER", loginCommand.getEmail());	//쿠키 생성
			rememberCookie.setPath("/");
			if(loginCommand.isRememberEmail()) {
				rememberCookie.setMaxAge(60*60*24*30);		//30일 유지
			} else {
				rememberCookie.setMaxAge(0);				//쿠키 삭제
			}
			response.addCookie(rememberCookie);
			
			return "login/loginSuccess";
		} catch(WrongIdPasswordException e) {
			errors.reject("idPasswordNotMatching");
			return "login/loginForm";
		}
	}
	
}
