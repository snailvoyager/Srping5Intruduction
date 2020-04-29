package springtest.chap14;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ListCommand {
	@DateTimeFormat(pattern="yyyyMMddHH")		//입력된 문자열을 변환
	private LocalDateTime from;
	@DateTimeFormat(pattern="yyyyMMddHH")
	private LocalDateTime to;
	
	public LocalDateTime getFrom() {
		return from;
	}
	public void setFrom(LocalDateTime from) {
		this.from = from;
	}
	public LocalDateTime getTo() {
		return to;
	}
	public void setTo(LocalDateTime to) {
		this.to = to;
	}
	
}
