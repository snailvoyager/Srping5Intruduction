package springtest.chap07;

public class ImpeCalculator implements Calculator{
	@Override
	public long factorial(long num) {
		// TODO Auto-generated method stub
		long result = 1;
		for(long i=1; i<=num; i++) {
			result *= i;
		}
		return result;
	}
}
