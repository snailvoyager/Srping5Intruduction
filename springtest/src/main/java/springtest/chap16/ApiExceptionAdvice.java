package springtest.chap16;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import springtest.chap11.MemberNotFoundException;

@RestControllerAdvice("springtest.chap16")
public class ApiExceptionAdvice {

	@ExceptionHandler(MemberNotFoundException.class)	//에러 처리를 한곳에서
	public ResponseEntity<ErrorResponse> handleNoData(){		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)	//Controller에서 Valid 실패 시 에러처리
	public ResponseEntity<ErrorResponse> handleBindException(MethodArgumentNotValidException ex){
		String errorCodes = ex.getBindingResult().getAllErrors()
				.stream()
				.map(error -> error.getCodes()[0])
				.collect(Collectors.joining(","));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes="+errorCodes));
	}
}
