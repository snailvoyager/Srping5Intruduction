package springtest.chap04;

public class MemberSummaryPrinter extends MemberPrinter{
	
	@Override
	public void print(Member member) {
		System.out.printf("email=%s, name=%s", member.getEmail(), member.getName());
	}
}
