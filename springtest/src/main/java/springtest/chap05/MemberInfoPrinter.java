package springtest.chap05;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("infoPrinter")
public class MemberInfoPrinter {
	private MemberDao memDao;
	private MemberPrinter printer;
	
	public void printMemberInfo(String email) {
		Member member = memDao.selectByEmail(email);
		if(member == null) {
			System.out.println("not exists");
			return;
		}
		printer.print(member);
		System.out.println();
	}
	@Autowired		//메서드에 자동주입 시 파라미터 타입에 해당하는 Bean객체를 찾아 주입
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}
	@Autowired
	//@Qualifier("memberPrinter2")
	public void setPrinter(MemberSummaryPrinter printer) {	//@Qualifier 사용하지 않더라도 파라미터를 사용할 Bean객체로 수정
		this.printer = printer;
	}
	
}
