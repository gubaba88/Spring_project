package hky.gamesite.project.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hky.gamesite.project.dto.MemberDTO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<MemberDTO> selectMemberList(int startrow, int endrow, String keyword) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("startrow", startrow);
		hashMap.put("endrow", endrow);
		hashMap.put("keyword", keyword);
		return sqlSessionTemplate.selectList("selectMemberList", hashMap);
	}
	
	public int countMember(String keyword) {
		return sqlSessionTemplate.selectOne("countMember", keyword);
	}

	public void insertMember(MemberDTO memberDTO) {
		sqlSessionTemplate.insert("insertMember", memberDTO);
	}

	public int maxMemNumber() {
		return sqlSessionTemplate.selectOne("maxMemNumber");
	}

	public MemberDTO selectMember(String memId) {
		return sqlSessionTemplate.selectOne("selectMember", memId);
	}

	public void updateMember(MemberDTO memberDTO) {
		sqlSessionTemplate.update("updateMember", memberDTO);
	}

	public void deleteMember(String memId) {
		sqlSessionTemplate.delete("deleteMember", memId);
	}

}
