package hky.gamesite.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hky.gamesite.project.dao.MemberDAO;
import hky.gamesite.project.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberDAO memberDAO;

	public List<MemberDTO> selectMemberList(int page, int limit, String keyword) {
		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;
		return memberDAO.selectMemberList(startrow, endrow, keyword);
	}

	public int countMember(String keyword) {
		return memberDAO.countMember(keyword);
	}

	public void insertMember(MemberDTO memberDTO) {
		String keyword = null;
		if (memberDAO.countMember(keyword) == 0) {
			memberDTO.setMemNumber(1);
		} else {
			memberDTO.setMemNumber(memberDAO.maxMemNumber() + 1);
		}
		if (memberDTO.getMemGender().equals("1")) {
			memberDTO.setMemBirth(19 + memberDTO.getMemBirth());
			memberDTO.setMemGender("M");
		}
		if (memberDTO.getMemGender().equals("2")) {
			memberDTO.setMemBirth(19 + memberDTO.getMemBirth());
			memberDTO.setMemGender("F");
		}
		if (memberDTO.getMemGender().equals("3")) {
			memberDTO.setMemBirth(20 + memberDTO.getMemBirth());
			memberDTO.setMemGender("M");
		}
		if (memberDTO.getMemGender().equals("4")) {
			memberDTO.setMemBirth(20 + memberDTO.getMemBirth());
			memberDTO.setMemGender("F");
		}
		memberDAO.insertMember(memberDTO);
	}

	public boolean checkMember(String memId) {
		boolean result = false;
		if (memberDAO.selectMember(memId) != null) {
			result = true;
		}
		return result;
	}

	public MemberDTO selectMember(String memId) {
		return memberDAO.selectMember(memId);
	}

	public void updateMember(MemberDTO memberDTO) {
		memberDAO.updateMember(memberDTO);
	}

	public void deleteMember(String memId) {
		memberDAO.deleteMember(memId);
	}

}
