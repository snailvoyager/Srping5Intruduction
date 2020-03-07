package springtest.chap04;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

public class MemberListPrinter {
	@Autowired
	private MemberDao memberDao;
	@Autowired
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
