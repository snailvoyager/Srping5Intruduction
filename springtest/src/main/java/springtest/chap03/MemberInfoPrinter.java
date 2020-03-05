package springtest.chap03;

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
	
	public void setMemberDao(MemberDao memberDao) {
		this.memDao = memberDao;
	}
	
	public void setPrinter(MemberPrinter printer) {
		this.printer = printer;
	}
}
