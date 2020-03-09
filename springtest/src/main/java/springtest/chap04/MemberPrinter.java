package springtest.chap04;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
	public MemberPrinter() {		//생성자 초기화
		this.dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");	//초기화를 하더라도 자동 주입 시 바뀔 수 있음
	}
	
	public void print(Member member) {
		if(dateTimeFormatter == null) {
			System.out.printf("ID = %d, email=%s, name=%s, regdate=%tF\n",
					member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
		} else {
			System.out.printf("ID = %d, email=%s, name=%s, regdate=%s\n",
					member.getId(), member.getEmail(), member.getName(),
					dateTimeFormatter.format(member.getRegisterDateTime()));	//날짜 형식을 맞춰 출력
		}
	}
	
//	@Autowired(required=false)		//자동 주입할 대상이 필수가 아닌 경우. 자동주입할 Bean객체가 없으면 메서드를 실행하지 않는다
//	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
//		this.dateTimeFormatter = dateTimeFormatter;
//	}
	
//	@Autowired
//	public void setDateFormatter(@Nullable DateTimeFormatter dateTimeFormatter) {	//의존 대상이 없으면 인자로 null값을 전달. 메서드는 호출
//		this.dateTimeFormatter = dateTimeFormatter;
//	}
	
	@Autowired
	public void setDateFormatter(Optional<DateTimeFormatter> formatterOpt) {
		if(formatterOpt.isPresent()) {
			this.dateTimeFormatter = formatterOpt.get();	//의존 대상이 있으면 Optional 인자
		} else {
			this.dateTimeFormatter = null;
		}
	}
}
