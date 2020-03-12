package springtest.chap07;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAspect {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		//Calculator cal = ctx.getBean("calculator", Calculator.class);
		RecCalculator cal = ctx.getBean("calculator", RecCalculator.class);		//프록시 객체를 리턴
		//생성할 Bean객체가 인터페이스를 상속하면 인터페이스를 이용하여 프록시 생성
		//Calculator 를 상속한 프록시 타입이면 RecCalculator 로 타입 변환 불가
		//@EnableAspectJAutoProxy(proxyTargetClass=true) 속성 지정으로 자바 클래스를 상속받아 프록시 생성
		long fiveFact = cal.factorial(5);
		System.out.println("cal.factorial(5) = " + fiveFact);
		System.out.println(cal.getClass().getName());
		
		cal.factorial(5);
		cal.factorial(5);
		cal.factorial(7);		//CacheAspect -> ExeTimeAspect -> RecCalculator
		cal.factorial(7);		//CacheAspect
		cal.factorial(100);
		cal.factorial(100);
		cal.factorial(100);
		ctx.close();
	}
}
