package springtest.chap06;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		Client client = ctx.getBean(Client.class);
		client.send();
		
		Client2 client2A = ctx.getBean(Client2.class);
		Client2 client2B = ctx.getBean(Client2.class);	//싱글톤으로 Bean 객체 생성
		System.out.println(client2A==client2B); 	//동일 객체
		
		Client3 clientA = ctx.getBean(Client3.class);
		Client3 clientB = ctx.getBean(Client3.class);	//싱글톤이 아닌 새로운 객체 생성
		System.out.println(clientA==clientB); 		//서로 다른 객체
		
		ctx.close();
	}

}
