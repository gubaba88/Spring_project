package hky.gamesite.project.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hky.gamesite.project.dto.BoardDTO;

@Repository
public class GamenameBoardDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<BoardDTO> selectGamenameList(HashMap<String, Object> hashMap) {
		return sqlSessionTemplate.selectList("selectGamenameList", hashMap);
	}

	public int countGamename(HashMap<String, Object> hashMap) {
		return sqlSessionTemplate.selectOne("countGamename", hashMap);
	}

	public BoardDTO selectGamename(int postNumber) {
		return sqlSessionTemplate.selectOne("selectGamename", postNumber);
	}

	public int maxGamenamePostNumber() {
		return sqlSessionTemplate.selectOne("maxGamenamePostNumber");
	}

	public void insertGamename(BoardDTO boardDTO) {
		sqlSessionTemplate.insert("insertGamename", boardDTO);
	}

	public void updateGamename(BoardDTO boardDTO) {
		sqlSessionTemplate.update("updateGamename", boardDTO);
	}

	public void deleteGamename(int postNumber) {
		sqlSessionTemplate.delete("deleteGamename", postNumber);
	}
}
