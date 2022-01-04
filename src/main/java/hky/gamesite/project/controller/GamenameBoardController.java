package hky.gamesite.project.controller;

import java.io.File;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import hky.gamesite.project.dto.BoardDTO;
import hky.gamesite.project.service.GamenameBoardService;

@Controller
public class GamenameBoardController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);

	@Autowired
	private GamenameBoardService gamenameBoardService;

	@RequestMapping("/GamenameList")
	public String selectGamenameList(String postTopic, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "keyfield", required = false) String keyfield,
			@RequestParam(value = "keyword", required = false) String keyword) {
		logger.info("게임 게시판 페이지 이동");
		logger.info("받은 정보 - postTopic : " + postTopic + ", page : " + page
				+ ", keyfield : " + keyfield + ", keyword : " + keyword);
		// 한 페이지에 보여줄 글 갯수
		int limit = 10;
		if (keyword != null && !keyword.equals("")) {
			keyword = keyword.trim();
		}
		int postCount = gamenameBoardService.countGamename(postTopic, keyfield, keyword);
		int maxpage = (int) ((double) postCount / limit + 0.99);
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		model.addAttribute("list", gamenameBoardService.selectGamenameList(page, postTopic, keyfield, keyword));
		model.addAttribute("currentPage", page);
		model.addAttribute("startPage", startpage);
		model.addAttribute("endPage", endpage);
		model.addAttribute("maxPage", maxpage);
		return "./gamenameBoard/gamenameList";
	}

	@RequestMapping("/GamenameSelect")
	public String selectGamename(int postNumber, Model model) {
		logger.info("글 선택을 위해 받은 정보 -  postNumber : " + postNumber);
		model.addAttribute("boardDTO", gamenameBoardService.selectGamename(postNumber));
		return "./gamenameBoard/gamenameSelect";
	}

	@RequestMapping(value = "/GamenameInsert", method = RequestMethod.GET)
	public String postInsert() {
		logger.info("게임 게시판 입력페이지 이동");
		return "./gamenameBoard/gamenameInsert";
	}

	@RequestMapping(value = "/GamenameInsert", method = RequestMethod.POST)
	public String postInsert(BoardDTO boardDTO, MultipartHttpServletRequest request) {
		logger.info("입력을 위해 받은 정보 - boardDTO :" + boardDTO);
		if (!boardDTO.getAttachedFile().isEmpty()) {
			String origName = boardDTO.getAttachedFile().getOriginalFilename();
			String fileName = new Date().getTime() + origName;
			String uploadPath = request.getSession().getServletContext().getRealPath("./resources/file") + "\\";
			File uploadFile = new File(uploadPath + fileName);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			if (uploadFile.exists()) {
				fileName = new Date().getTime() + origName;
				uploadFile = new File(uploadPath + fileName);
			}
			MultipartFile multipartFile = boardDTO.getAttachedFile();
			try {
				multipartFile.transferTo(uploadFile);
				boardDTO.setFileName(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String postContent = boardDTO.getPostContent().replace("\r\n", "<br/>");
		boardDTO.setPostContent(postContent);
		
		boolean result = true;
		try {
			boardDTO.setPostNumber(gamenameBoardService.insertGamename(boardDTO));
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			result = false;
		}
		request.setAttribute("result", result);
		request.setAttribute("boardDTO", boardDTO);
		return "./gamenameBoard/gamenameInsert_check";
	}

	@RequestMapping(value = "/GamenameUpdate", method = RequestMethod.GET)
	public String postUpdate(int postNumber, Model model) {
		logger.info("수정을 위해 받은 정보 - postNumber" + postNumber);
		model.addAttribute("boardDTO", gamenameBoardService.selectGamename(postNumber));
		return "./gamenameBoard/gamenameUpdate";
	}

	@RequestMapping(value = "/GamenameUpdate", method = RequestMethod.POST)
	public String postUpdate(BoardDTO boardDTO, MultipartHttpServletRequest request) {
		logger.info("수정 되는 정보 - boardDTO :" + boardDTO);
		if (!boardDTO.getAttachedFile().isEmpty()) {
			String oldFileName = boardDTO.getFileName();
			String origName = boardDTO.getAttachedFile().getOriginalFilename();
			String fileName = new Date().getTime() + origName;
			String uploadPath = request.getSession().getServletContext().getRealPath("./resources/file") + "\\";
			File uploadFile = new File(uploadPath + fileName);
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdirs();
			}
			if (uploadFile.exists()) {
				fileName = new Date().getTime() + origName;
				uploadFile = new File(uploadPath + fileName);
			}
			MultipartFile multipartFile = boardDTO.getAttachedFile();
			try {
				File delFile = new File(uploadPath + oldFileName);
				// 파일 있으면 삭제
				if (delFile.exists()) {
					delFile.delete();
				}
				multipartFile.transferTo(uploadFile);
				boardDTO.setFileName(fileName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		String postContent = boardDTO.getPostContent().replace("\r\n", "<br/>");
		boardDTO.setPostContent(postContent);
		
		gamenameBoardService.updateGamename(boardDTO);
		return "redirect:GamenameSelect?postNumber="+boardDTO.getPostNumber();
	}

	@RequestMapping("/GamenameDelete")
	public String postDelete(int postNumber, Model model) {
		logger.info("삭제를 위해 받은 정보 - postNumber : " + postNumber);
		model.addAttribute("boardDTO", gamenameBoardService.selectGamename(postNumber));
		gamenameBoardService.deleteGamename(postNumber);
		return "./gamenameBoard/gamenameDelete";
	}
}
