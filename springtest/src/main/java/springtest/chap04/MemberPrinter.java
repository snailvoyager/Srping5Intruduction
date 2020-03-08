package springtest.chap04;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberPrinter {
	private DateTimeFormatter dateTimeFormatter;
	
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
	
	@Autowired(required=false)		//자동 주입할 대상이 필수가 아닌 경우. 자동주입할 Bean객체가 없으면 메서드를 실행하지 않는다
	public void setDateFormatter(DateTimeFormatter dateTimeFormatter) {
		this.dateTimeFormatter = dateTimeFormatter;
	}
}
