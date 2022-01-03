package hky.gamesite.project.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hky.gamesite.project.dao.NoticeBoardDAO;
import hky.gamesite.project.dto.BoardDTO;

@Service
public class NoticeBoardService {
	@Autowired
	private NoticeBoardDAO noticeBoardDAO;

	public List<BoardDTO> selectNoticeList(int page, String postTopic, String keyfield, String keyword) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + 9;
		hashMap.put("startrow", startrow);
		hashMap.put("endrow", endrow);
		hashMap.put("postTopic", postTopic);
		hashMap.put("keyfield", keyfield);		
		hashMap.put("keyword", keyword);		
		return noticeBoardDAO.selectNoticeList(hashMap);
	}
	
	public int countNotice(String postTopic, String keyfield, String keyword) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("postTopic", postTopic);
		hashMap.put("keyfield", keyfield);
		hashMap.put("keyword", keyword);
		return noticeBoardDAO.countNotice(hashMap);
	}

	public BoardDTO selectNotice(int postNumber) {
		return noticeBoardDAO.selectNotice(postNumber);
	}

	public int insertNotice(BoardDTO boardDTO) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		if (noticeBoardDAO.countNotice(hashMap) == 0) {
			boardDTO.setPostNumber(1);
		} else {
			boardDTO.setPostNumber(noticeBoardDAO.maxNoticePostNumber() + 1);
		}
		noticeBoardDAO.insertNotice(boardDTO);
		return boardDTO.getPostNumber();
	}

	public void updateNotice(BoardDTO boardDTO) {
		noticeBoardDAO.updateNotice(boardDTO);
	}

	public void deleteNotice(int postNumber) {
		noticeBoardDAO.deleteNotice(postNumber);
	}
}
