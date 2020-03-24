package springtest.chap11;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {
	private MemberDao memberDao;
	
	@Transactional
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
