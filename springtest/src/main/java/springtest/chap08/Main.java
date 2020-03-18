package springtest.chap08;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springtest.chap08.config.AppCtx;

public class Main {

	private static MemberDao memberDao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		memberDao = ctx.getBean(MemberDao.class);
		
		selectByEmail();
		
		selectAll();
		updateMember();
		insertMember();
		selectAll();
		
		ctx.close();
	}
	
	private static void selectByEmail() {
		Member member = memberDao.selectByEmail("fyal@naver.com");
		System.out.println(member.getId() + " : " + member.getEmail() + " : " + member.getName());
	}
	
	private static void selectAll() {
		System.out.println("##selectAll");
		int total = memberDao.count();
		System.out.println("Total : " + total);
		
		List<Member> members = memberDao.selectAll();
		for(Member m : members)
			System.out.println(m.getId() + " : " + m.getEmail() + " : " + m.getName());
	}
	
	private static void updateMember() {
		System.out.println("##updateMember");
		Member member = memberDao.selectByEmail("fyal@naver.com");
		String oldPw = member.getPassword();
		String newPw = "1598";
		member.changePassword(oldPw, newPw);
		
		memberDao.update(member);
		System.out.println("PW change : " + oldPw + " > " + newPw);
	}
	
	private static void insertMember() {
		System.out.println("$$insertMember");
		
		String prefix = DateTimeFormatter.ofPattern("MMddHHmmss").format(LocalDateTime.now());
		Member member = new Member(prefix + "@test.com", prefix, prefix, LocalDateTime.now());
		memberDao.insert(member);
		System.out.println(member.getId() + " Insert Data");
	}

}
