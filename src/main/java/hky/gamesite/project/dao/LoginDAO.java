package hky.gamesite.project.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hky.gamesite.project.dto.MemberDTO;

@Repository
public class LoginDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public MemberDTO login(String memId) {
		return sqlSessionTemplate.selectOne("login", memId);
	}

}
