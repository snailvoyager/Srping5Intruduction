package springtest.chap08;

public class MemberPrinter {
	public void print(Member member) {
		System.out.printf("ID = %d, email=%s, name=%s, regdate=%tF\n",
				member.getId(), member.getEmail(), member.getName(), member.getRegisterDateTime());
	}
}
