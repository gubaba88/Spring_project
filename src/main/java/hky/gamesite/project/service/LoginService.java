package hky.gamesite.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hky.gamesite.project.dao.LoginDAO;
import hky.gamesite.project.dto.MemberDTO;

@Service
public class LoginService {
	@Autowired
	private LoginDAO loginDAO;

	public MemberDTO login(String memId) {
		return loginDAO.login(memId);
	}
}
