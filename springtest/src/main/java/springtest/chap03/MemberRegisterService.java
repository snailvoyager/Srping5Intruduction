package springtest.chap03;

import java.time.LocalDateTime;

public class MemberRegisterService {
	private MemberDao memberDao;
	
	public MemberRegisterService(MemberDao memberDao) {	//생성자를 통한 DI
		this.memberDao = memberDao;
	}
	
	public Long regist(RegisterRequest req) {
		Member member = memberDao.selectByEmail(req.getEmail());
		if(member!=null) {
			throw new DuplicateMemberException("dup email " + req.getEmail());
		}
		
		Member newMember = new Member(
				req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
		return newMember.getId();
	}
}
