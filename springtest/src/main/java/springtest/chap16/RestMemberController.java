package springtest.chap16;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import springtest.chap11.DuplicateMemberException;
import springtest.chap11.Member;
import springtest.chap11.MemberDao;
import springtest.chap11.MemberNotFoundException;
import springtest.chap11.MemberRegisterService;
import springtest.chap11.RegisterRequest;

@RestController
public class RestMemberController {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberRegisterService registerService;
	
	@GetMapping("/api/members")
	public List<Member> members(){
		return memberDao.selectAll();
	}
	
	@GetMapping("/api/members/{id}")
	public Member member(@PathVariable Long id, HttpServletResponse response) throws IOException{
		Member member = memberDao.selectById(id);
		if(member == null) {
			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
			throw new MemberNotFoundException();
		}
		//return ResponseEntity.status(HttpStatus.OK).body(member);	//ResponseEntity의 body에 객체를 JSON으로 변환
		return member;
	}
	
	@PostMapping("/api/members")
	public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest regReq
			, HttpServletResponse response) throws IOException{	//검증 실패하면 400코드 응답
		try {
			Long newMemberId = registerService.regist(regReq);
			//response.setHeader("Location", "/api/members/" + newMemberId);
			//response.setStatus(HttpServletResponse.SC_CREATED);
			URI uri = URI.create("/api/members/" + newMemberId);
			return ResponseEntity.created(uri).build();
		} catch(DuplicateMemberException dupEx) {
			//response.sendError(HttpServletResponse.SC_CONFLICT);
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
	
}
