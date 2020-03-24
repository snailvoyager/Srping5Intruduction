package springtest.chap11;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class MemberDao {
	
	private JdbcTemplate jdbcTemplate;
	
	public MemberDao(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Member selectByEmail(String email) {
		List<Member> results = jdbcTemplate.query("select * from MEMBER where EMAIL=?", new RowMapper<Member>() {
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
				Member member = new Member(
						rs.getString("EMAIL"),
						rs.getString("PASSWORD"),
						rs.getString("NAME"),
						rs.getTimestamp("REGDATE").toLocalDateTime());
				member.setId(rs.getLong("ID"));
				return member;
			}
		}, email);	//쿼리 파라미터 인자
		return results.isEmpty() ? null : results.get(0);
	}
	
	public void insert(Member member) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException{ //Connection을 이용해서 PreparedStatement 생성
				PreparedStatement pstmt = con.prepareStatement("insert into MEMBER (EMAIL, PASSWORD, NAME, REGDATE) values(?,?,?,?)",
						new String[] {"ID"});	//자동 생성되는 키 칼럼 목록을 지정
				pstmt.setString(1, member.getEmail());
				pstmt.setString(2, member.getPassword());
				pstmt.setString(3, member.getName());
				pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
				return pstmt;
			}
		}, keyHolder);		//KeyHolder를 이용해서 자동으로 생성된 키값을 구할 수 있음
		Number keyValue = keyHolder.getKey();
		member.setId(keyValue.longValue());
	}
	
	public void update(Member member) {
		jdbcTemplate.update("update MEMBER set NAME = ?, PASSWORD = ? where EMAIL = ?"
				, member.getName(), member.getPassword(), member.getEmail());
	}
	
	public List<Member> selectAll(){
		List<Member> results = jdbcTemplate.query("select * from MEMBER", new RowMapper<Member>() {
			public Member mapRow(ResultSet rs, int rowNum) throws SQLException{
				Member member = new Member(
						rs.getString("EMAIL"),
						rs.getString("PASSWORD"),
						rs.getString("NAME"),
						rs.getTimestamp("REGDATE").toLocalDateTime());
				member.setId(rs.getLong("ID"));
				return member;
			}
		});
		return results;
	}
	
	public int count() {
		/*
		 * List<Integer> results = jdbcTemplate.query("select count(*) from MEMBER", new
		 * RowMapper<Integer>() { public Integer mapRow(ResultSet rs, int rowNum) throws
		 * SQLException{ return rs.getInt(1); } }); return results.get(0);
		 */
		Integer count = jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class);	//쿼리 결과가 한행일 때 사용
		return count;
	}
	
}
