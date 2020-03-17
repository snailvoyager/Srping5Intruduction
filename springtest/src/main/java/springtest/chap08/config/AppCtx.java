package springtest.chap08.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springtest.chap08.MemberDao;

@Configuration
public class AppCtx {
	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost/spring5fs?characterEncoding=utf8");
		ds.setUsername("springtest");
		ds.setPassword("spring5");
		ds.setInitialSize(2);	//생성할 초기 커넥션 개수
		ds.setMaxActive(10);	//커넥션 풀에서 가져올 수 있는 최대 개수
		return ds;
	}
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao(dataSource());
	}
}
