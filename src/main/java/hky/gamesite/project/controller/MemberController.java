package hky.gamesite.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import hky.gamesite.project.dto.MemberDTO;
import hky.gamesite.project.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService memberService;

	@RequestMapping("/MemberList")
	public String selectMemberList(Model model, @RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "10") int limit,
			@RequestParam(value = "keyword", required = false) String keyword) {
		logger.info("회원 목록 페이지 이동");
		logger.info("받은 데이터 - page : " + page + ", limit : " + limit + ", keyword : " + keyword);
		// 검색어가 있다면 좌우 공백을 제거한다.
		if (keyword != null && keyword.equals("")) {
			keyword = keyword.trim();
		}
		//조건에 맞는 회원 수를 구한다.
		int memberCount = memberService.countMember(keyword);
		//한 페이지당 limit 수의 회원을 보여주고 최대 몇 페이지인지 구한다.
		int maxpage = (int) ((double) memberCount / limit + 0.99);
		//현재 페이지가 속한 10단위의 페이지 목록의 시작 페이지를 구한다.
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		//한 화면에 시작 페이지부터 9페이지까지 보여주도록 마지막 페이지를 구한다.
		int endpage = startpage + 9;
		//마지막 페이지의 숫자가 가 최대 페이지 숫자보다 클 경우 마지막 페이지의 숫자를 최대페이지의 숫자에 맞춘다. 
		if (endpage > maxpage) {
			endpage = maxpage;
		}
		model.addAttribute("list", memberService.selectMemberList(page, limit, keyword));
		model.addAttribute("memberCount", memberCount);
		model.addAttribute("currentPage", page);
		model.addAttribute("startPage", startpage);
		model.addAttribute("endPage", endpage);
		model.addAttribute("maxPage", maxpage);
		return "./member/memberList";
	}

	@RequestMapping(value = "/MemberInsert", method = RequestMethod.GET)
	public String insertMember() {
		logger.info("회원 가입 페이지 이동");
		return "./member/memberInsert";
	}

	@RequestMapping(value = "/MemberInsert", method = RequestMethod.POST)
	public String insertMember(MemberDTO memberDTO, Model model) {
		logger.info("가입하는 회원 정보 - " + memberDTO);
		boolean result = true;
		//회원가입을 시도하고 중복된 PK오류 발생시 예외처리하고 결과를 저장한다.
		try {
			memberService.insertMember(memberDTO);
		} catch (DuplicateKeyException e) {
			e.printStackTrace();
			result = false;
		}
		model.addAttribute("memId", memberDTO.getMemId());
		model.addAttribute("result", result);
		return "./member/memberInsert_check";
	}

	@RequestMapping("/MemberSelect")
	public String selectMember(String memId, Model model) {
		logger.info("확인 하려는 아이디 - " + memId);
		model.addAttribute("memberDTO", memberService.selectMember(memId));
		return "./member/memberSelect";
	}

	@RequestMapping(value = "/MemberUpdate", method = RequestMethod.GET)
	public String memUpdate(String memId, Model model) {
		logger.info("수정 하려는 아이디 - " + memId);
		model.addAttribute("memberDTO", memberService.selectMember(memId));
		return "./member/memberUpdate";
	}

	@RequestMapping(value = "/MemberUpdate", method = RequestMethod.POST)
	public String memUpdate(MemberDTO memberDTO) {
		logger.info("수정된 회원 정보 - " + memberDTO);
		memberService.updateMember(memberDTO);
		return "redirect:MemberSelect?memId=" + memberDTO.getMemId();
	}

	@RequestMapping("/MemberDelete")
	public String memDelete(String memId) {
		logger.info("삭제 하려는 아이디 - " + memId);
		memberService.deleteMember(memId);
		return "./member/memberDelete";
	}

}
