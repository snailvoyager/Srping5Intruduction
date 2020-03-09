package springtest.chap05;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("listPrinter")
public class MemberListPrinter {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	@Qualifier("printer")		//printer 값을 갖는 Bean 객체 주입
	private MemberPrinter printer;
	
	/*
	 * public MemberListPrinter(MemberDao memberDao, MemberPrinter printer) {
	 * this.memberDao = memberDao; this.printer = printer; }
	 */
	
	public MemberListPrinter() {
		
	}
	
	public void printAll() {
		Collection<Member> members = memberDao.selectAll();
		members.forEach(m -> printer.print(m));
	}
}
