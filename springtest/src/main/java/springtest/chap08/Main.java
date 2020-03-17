package springtest.chap08;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springtest.chap08.config.AppCtx;

public class Main {

	private static MemberDao memberDao;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		memberDao = ctx.getBean(MemberDao.class);
		
		selectByEmail();
		
		ctx.close();
	}
	
	private static void selectByEmail() {
		Member member = memberDao.selectByEmail("fyal@naver.com");
		System.out.println(member.getId() + " : " + member.getEmail() + " : " + member.getName());
	}

}
