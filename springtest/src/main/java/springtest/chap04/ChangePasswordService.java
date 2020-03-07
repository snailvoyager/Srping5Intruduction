package springtest.chap04;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {
	@Autowired		//자동 의존 주입
	private MemberDao memberDao;
	
	public void changePassword(String email, String oldPwd, String newPwd) {
		Member member = memberDao.selectByEmail(email);
		if(member == null) {
			throw new MemberNotFoundException();
		}
		
		member.changePassword(oldPwd, newPwd);
		memberDao.update(member);
	}
	
	public void setMemberDao(MemberDao memberDao) {	//setter를 통한 DI
		this.memberDao = memberDao;
	}
}
