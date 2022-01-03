package hky.gamesite.project.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hky.gamesite.project.dto.MemberDTO;
import hky.gamesite.project.service.LoginService;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	public String login() {
		return "./login/login";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String login(String memId, String memPasswd, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		if (loginService.login(memId) == null) {
			result = "noId";
		} else {
			MemberDTO memberDTO = loginService.login(memId);
			if (memberDTO.getMemPasswd().equals(memPasswd)) {
				HttpSession httpSession = request.getSession();
				httpSession.setAttribute("memId", memberDTO.getMemId());
				Cookie cookie = new Cookie("memId", memberDTO.getMemId());
				cookie.setMaxAge(60 * 60 * 24);
				cookie.setPath("/");
				result = "success";
				response.addCookie(cookie);
				request.setAttribute("memId", memId);

			} else {
				result = "fail";
			}
		}
		request.setAttribute("result", result);
		return "./login/login_check";
	}

	@RequestMapping("/Logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession httpSession = request.getSession();
		String memId = (String) httpSession.getAttribute("memId");
		request.setAttribute("memId", memId);

		httpSession.invalidate();
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("memId")) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		return "./login/logout";
	}

	@RequestMapping("/FileDownload.do")
	public String fileDownload(String fileName, HttpServletResponse response) throws Exception {
		String attachedFile = URLEncoder.encode(fileName, "UTF-8").replace("\\+", "%20");
		logger.info("다운로드 데이터 확인 - " + attachedFile);
		response.setContentType("application/octet-stream");
		return "redirect:/resources/file/" + attachedFile;
	}

}
