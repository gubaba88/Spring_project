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
import hky.gamesite.project.service.NoticeBoardService;

@Controller
public class NoticeBoardController {
	private static final Logger logger = LoggerFactory.getLogger(NoticeBoardController.class);

	@Autowired
	private NoticeBoardService noticeBoardService;

	@RequestMapping("/NoticeList")
	public String selectNoticeList(String postTopic, Model model,
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "keyfield", required = false) String keyfield,
			@RequestParam(value = "keyword", required = false) String keyword) {
		logger.info("공지사항 게시판 페이지 이동");
		logger.info("받은 데이터 - postTopic : " + postTopic + ", page : " + page + ", keyfield : " + keyfield
				+ ", keyword : " + keyword);
		// 한 페이지에 보여줄 글 갯수
		int limit = 10;
		// 검색어가 있다면 좌우 공백을 제거한다.
		if (keyword != null && !keyword.equals("")) {
			keyword = keyword.trim();
		}
		// 조건에 맞는 공지사항의 글 갯수를 구한다.
		int postCount = noticeBoardService.countNotice(postTopic, keyfield, keyword);
		// 한 페이지에 10개씩 글을 보여줄때 최대 몇 페이지인지 구한다.
		int maxpage = (int) ((double) postCount / limit + 0.99);
		// 현재페이지가 속한 10단위의 페이지 목록의 시작페이지를 구한다.
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		// 한 화면에 페이지 목록을 10개만 띄우도록 시작페이지에 9를 더해 마지막 페이지를 구한다.
		int endpage = startpage + 9;
		// 마지막 페이지가 최대 페이지보다 클 경우 최대 페이지의 값을 마지막 페이지의 값으로 한다.
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		model.addAttribute("list", noticeBoardService.selectNoticeList(page, postTopic, keyfield, keyword));
		model.addAttribute("currentPage", page);
		model.addAttribute("startPage", startpage);
		model.addAttribute("endPage", endpage);
		model.addAttribute("maxPage", maxpage);
		return "./noticeBoard/noticeList";
	}

	@RequestMapping("/NoticeSelect")
	public String selectNotice(int postNumber, Model model) {
		logger.info("글 선택을 위해 받은 정보 - postNumber : " + postNumber);
		model.addAttribute("boardDTO", noticeBoardService.selectNotice(postNumber));
		return "./noticeBoard/noticeSelect";
	}

	@RequestMapping(value = "/NoticeInsert", method = RequestMethod.GET)
	public String insertNotice() {
		logger.info("공지 게시판 입력 페이지 이동");
		return "./noticeBoard/noticeInsert";
	}

	@RequestMapping(value = "/NoticeInsert", method = RequestMethod.POST)
	public String insertNotice(BoardDTO boardDTO, MultipartHttpServletRequest request) {
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
		//회원가입을 시도하고 중복된 PK오류 발생시 예외처리하고 결과를 저장한다.
		try {
			boardDTO.setPostNumber(noticeBoardService.insertNotice(boardDTO));
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			result = false;
		}
		request.setAttribute("result", result);
		request.setAttribute("boardDTO", boardDTO);
		return "./noticeBoard/noticeInsert_check";
	}

	@RequestMapping(value = "/NoticeUpdate", method = RequestMethod.GET)
	public String postUpdate(int postNumber, Model model) {
		logger.info("수정을 위해 받은 정보 - postNumber" + postNumber);
		model.addAttribute("boardDTO", noticeBoardService.selectNotice(postNumber));
		return "./noticeBoard/noticeUpdate";
	}

	@RequestMapping(value = "/NoticeUpdate", method = RequestMethod.POST)
	public String updateNotice(BoardDTO boardDTO, MultipartHttpServletRequest request) {
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
		
		noticeBoardService.updateNotice(boardDTO);
		return "redirect:NoticeSelect?postNumber="+boardDTO.getPostNumber();
	}

	@RequestMapping("/NoticeDelete")
	public String deleteNotice(int postNumber, Model model) {
		logger.info("삭제를 위해 받은 정보 - postNumber :" + postNumber);
		model.addAttribute("boardDTO", noticeBoardService.selectNotice(postNumber));
		noticeBoardService.deleteNotice(postNumber);
		return "./noticeBoard/noticeDelete";
	}
}
