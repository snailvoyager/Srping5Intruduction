package springtest.chap07;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ExeTimeAspect {
	@Pointcut("execution(public * springtest.chap07..factorial*(..))")		//공통기능을 적용할 대상. 하위 패키지에 속한 Bean객체의 public factorial메서드
	private void publicTarget() {
		
	}
	
	@Around("publicTarget()")		//Pointcut 대상에 measure() 메서드를 적용
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.nanoTime();
		try {
			Object result = joinPoint.proceed();	//프록시 대상 객체의 메서드 호출
			return result;
		} finally {
			long finish = System.nanoTime();
			Signature sig = joinPoint.getSignature();
			System.out.printf("%s.%s(%s) 실행시간 : %d ns\n",
					joinPoint.getTarget().getClass().getSimpleName(),		//대상 객체의 클래스, 메서드 이름 출력
					sig.getName(), Arrays.toString(joinPoint.getArgs()), (finish - start));
		}
	}
}
