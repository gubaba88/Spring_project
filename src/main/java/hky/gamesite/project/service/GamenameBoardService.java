package hky.gamesite.project.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hky.gamesite.project.dao.GamenameBoardDAO;
import hky.gamesite.project.dto.BoardDTO;

@Service
public class GamenameBoardService {
	@Autowired
	private GamenameBoardDAO gamenameBoardDAO;

	public List<BoardDTO> selectGamenameList(int page, String postTopic, String keyfield, String keyword) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + 9;
		hashMap.put("startrow", startrow);
		hashMap.put("endrow", endrow);
		hashMap.put("postTopic", postTopic);
		hashMap.put("keyfield", keyfield);		
		hashMap.put("keyword", keyword);		
		return gamenameBoardDAO.selectGamenameList(hashMap);
	}
	
	public int countGamename(String postTopic, String keyfield, String keyword) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("postTopic", postTopic);
		hashMap.put("keyfield", keyfield);
		hashMap.put("keyword", keyword);
		return gamenameBoardDAO.countGamename(hashMap);
	}

	public BoardDTO selectGamename(int postNumber) {
		return gamenameBoardDAO.selectGamename(postNumber);
	}

	public int insertGamename(BoardDTO boardDTO) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if (gamenameBoardDAO.countGamename(hashMap) == 0) {
			boardDTO.setPostNumber(1);
		} else {
			boardDTO.setPostNumber(gamenameBoardDAO.maxGamenamePostNumber() + 1);
		}
		gamenameBoardDAO.insertGamename(boardDTO);
		return boardDTO.getPostNumber();
	}

	public void updateGamename(BoardDTO boardDTO) {
		gamenameBoardDAO.updateGamename(boardDTO);
	}

	public void deleteGamename(int postNumber) {
		gamenameBoardDAO.deleteGamename(postNumber);
	}
}
