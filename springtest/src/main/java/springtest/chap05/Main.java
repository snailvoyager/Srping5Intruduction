package springtest.chap05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	private static ApplicationContext ctx = null;
	
	public static void main(String[] args) throws IOException {
		ctx = new AnnotationConfigApplicationContext(AppCtx.class);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("Input: ");
			String command = reader.readLine();
			if(command.equalsIgnoreCase("exit")) {
				System.out.println("exit");
				break;
			}
			if(command.startsWith("new ")) {
				processNewCommand(command.split(" "));
				continue;
			} else if(command.startsWith("change ")) {
				processChangeCommand(command.split(" "));
				continue;
			} else if(command.equals("list")) {
				processListCommand();
				continue;
			} else if(command.startsWith("info ")) {
				processInfoCommand(command.split(" "));
				continue;
			}
			printHelp();
		}
	}
	
	//private static Assembler assembler = new Assembler();
	
	private static void processNewCommand(String[] arg) {
		if(arg.length != 5) {
			printHelp();
			return;
		}
		//MemberRegisterService regSvc = assembler.getRegSvc();
		MemberRegisterService regSvc = ctx.getBean(MemberRegisterService.class);
		
		RegisterRequest req = new RegisterRequest();
		req.setEmail(arg[1]);
		req.setName(arg[2]);
		req.setPassword(arg[3]);
		req.setConfirmPassword(arg[4]);
		
		if(!req.isPasswordEqualToConfirmPassword()) {
			System.out.println("incorrect password");
			return;
		}
		try {
			regSvc.regist(req);
			System.out.println("register");
		} catch(DuplicateMemberException e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	private static void processChangeCommand(String[] arg) {
		if(arg.length != 4) {
			printHelp();
			return;
		}
		//ChangePasswordService changePwdSvc = assembler.getPwdSvc();
		ChangePasswordService changePwdSvc = ctx.getBean(ChangePasswordService.class);
		
		try {
			changePwdSvc.changePassword(arg[1], arg[2], arg[3]);
			System.out.println("change password");
		} catch(MemberNotFoundException e) {
			System.out.println("not exist");
		} catch(WrongIdPasswordException e) {
			System.out.println("not correct password");
		}
	}
	
	private static void printHelp() {
		System.out.println("printHelp");
	}
	
	private static void processListCommand() {
		MemberListPrinter listPrinter = ctx.getBean("listPrinter", MemberListPrinter.class);
		listPrinter.printAll();
	}
	
	private static void processInfoCommand(String[] arg) {
		if(arg.length != 2) {
			printHelp();
			return;
		}
		MemberInfoPrinter infoPrinter = ctx.getBean("infoPrinter", MemberInfoPrinter.class);
		infoPrinter.printMemberInfo(arg[1]);
	}
}
