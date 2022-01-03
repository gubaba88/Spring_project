package hky.gamesite.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardListController {
	private static final Logger logger = LoggerFactory.getLogger(BoardListController.class);

	@RequestMapping("/BoardList")
	public String boardList() {
		logger.info("게시판 목록 페이지 이동");
		return "./boardList";
	}
}
