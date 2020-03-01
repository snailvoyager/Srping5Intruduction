package springtest.chpa02;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		Greeter g = ctx.getBean("greeter", Greeter.class);
		String msg = g.greet("Spring");
		System.out.println(msg);
		ctx.close();
	}

}
