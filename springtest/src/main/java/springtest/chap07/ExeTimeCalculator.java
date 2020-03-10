package springtest.chap07;

public class ExeTimeCalculator implements Calculator{
	private Calculator delegate;
	
	public ExeTimeCalculator(Calculator delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public long factorial(long num) {
		// TODO Auto-generated method stub
		long start = System.nanoTime();
		
		long result = delegate.factorial(num);	//핵심 기능은 대상 객체에 위임
		//기존 코드를 변경하지 않고 기능 변경 가능. 코드 중복 제거
		
		long end = System.nanoTime();
		
		System.out.printf("%s.factorial(%d) 실행시간 = %d\n",
				delegate.getClass().getSimpleName(),
				num, (end - start));		//부가적인 기능 수행
		
		return result;
	}
}
