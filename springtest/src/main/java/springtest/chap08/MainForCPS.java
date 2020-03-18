package springtest.chap08;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import springtest.chap08.config.AppCtx;

public class MainForCPS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		ChangePasswordService cps = ctx.getBean("changePwdSvc", ChangePasswordService.class);	//프록시 객체를 리턴
		
		try {
			cps.changePassword("fyal@naver.com", "1598", "1598");	//프록시 객체는 트랜잭션 메서드를 호출하면 PlatformTransactionManager를 사용해서 트랜잭션을 시작
			System.out.println("Change Password");
		} catch(MemberNotFoundException e) {
			System.out.println("Not exists....");
		} catch(WrongIdPasswordException e) {	//프록시는 원본 객체 메서드에서 RuntimeException 발생하면 롤백
			System.out.println("Not correct password");
		}
		
		ctx.close();
	}

}
