package hky.gamesite.project.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hky.gamesite.project.dto.BoardDTO;

@Repository
public class NoticeBoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<BoardDTO> selectNoticeList(HashMap<String, Object> hashMap) {
		return sqlSessionTemplate.selectList("selectNoticeList", hashMap);
	}

	public int countNotice(HashMap<String, Object> hashMap) {
		return sqlSessionTemplate.selectOne("countNotice", hashMap);
	}

	public BoardDTO selectNotice(int postNumber) {
		return sqlSessionTemplate.selectOne("selectNotice", postNumber);
	}

	public int maxNoticePostNumber() {
		return sqlSessionTemplate.selectOne("maxNoticePostNumber");
	}

	public void insertNotice(BoardDTO postDTO) {
		sqlSessionTemplate.insert("insertNotice", postDTO);
	}

	public void updateNotice(BoardDTO postDTO) {
		sqlSessionTemplate.update("updateNotice", postDTO);
	}

	public void deleteNotice(int postNumber) {
		sqlSessionTemplate.delete("deleteNotice", postNumber);
	}
}
