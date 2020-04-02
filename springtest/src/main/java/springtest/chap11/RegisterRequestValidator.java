package springtest.chap11;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegisterRequestValidator implements Validator{

	private static final String emailRegExp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
												"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;
	
	public RegisterRequestValidator() {
		// TODO Auto-generated constructor stub
		pattern = Pattern.compile(emailRegExp);
	}
	
	@Override
	public boolean supports(Class<?> clazz) {		//Validator가 검증할 수 있는 타입인지 검사
		// TODO Auto-generated method stub
		return RegisterRequest.class.isAssignableFrom(clazz);	//SpringMVC가 자동으로 검증 기능 수행
	}
	
	@Override
	public void validate(Object target, Errors errors) {	//target 객체를 검증하고 오류 결과를 Errors에 담는 기능
		// TODO Auto-generated method stub
		RegisterRequest regReq = (RegisterRequest) target;
		if(regReq.getEmail() == null || regReq.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");	//email 프로퍼티 에러 코드 저장
		} else {
			Matcher matcher = pattern.matcher(regReq.getEmail());	//정규표현식 검사
			if(!matcher.matches())
				errors.rejectValue("email", "bad");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");	//target을 전달하지 않더라도 Errors 객체를 통해 얻는다
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if(!regReq.getPassword().isEmpty()) {
			if(!regReq.isPasswordEqualToConfirmPassword()) {
				errors.rejectValue("confirmPassword", "nomatch");
			}
		}
	}
}
