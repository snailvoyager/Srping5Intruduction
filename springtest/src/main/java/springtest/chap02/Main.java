package springtest.chap02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		//AppContext에 정의한 @Bean 객체 생성, 초기화
		
		Greeter g = ctx.getBean("greeter", Greeter.class);	//Bean 객체 검색
		String msg = g.greet("Spring");
		System.out.println(msg);
		
		Greeter g2 = ctx.getBean("greeter", Greeter.class);	//같은 객체를 리턴. 싱글톤
		System.out.println("g == g2 : " + (g == g2));
		ctx.close();
	}

}
