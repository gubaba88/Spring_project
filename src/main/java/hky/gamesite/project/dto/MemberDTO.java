package hky.gamesite.project.dto;

import org.springframework.stereotype.Component;

@Component
public class MemberDTO {
	private String memId;
	private String memPasswd;
	private String memEmail;
	private String memName;
	private String memPhone;
	private String memBirth;
	private String memGender;
	private String memAddress;
	private String memComment;
	private int memPoint;
	private String memDate;
	private int memNumber;

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemPasswd() {
		return memPasswd;
	}

	public void setMemPasswd(String memPasswd) {
		this.memPasswd = memPasswd;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemPhone() {
		return memPhone;
	}

	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}

	public String getMemBirth() {
		return memBirth;
	}

	public void setMemBirth(String memBirth) {
		this.memBirth = memBirth;
	}

	public String getMemGender() {
		return memGender;
	}

	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

	public String getMemComment() {
		return memComment;
	}

	public void setMemComment(String memComment) {
		this.memComment = memComment;
	}

	public int getMemPoint() {
		return memPoint;
	}

	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}

	public String getMemDate() {
		return memDate;
	}

	public void setMemDate(String memDate) {
		this.memDate = memDate;
	}

	public int getMemNumber() {
		return memNumber;
	}

	public void setMemNumber(int memNumber) {
		this.memNumber = memNumber;
	}

	@Override
	public String toString() {
		return "MemberDTO [memId=" + memId + ", memPasswd=" + memPasswd + ", memEmail=" + memEmail + ", memName="
				+ memName + ", memPhone=" + memPhone + ", memBirth=" + memBirth + ", memGender=" + memGender
				+ ", memAddress=" + memAddress + ", memComment=" + memComment + ", memPoint=" + memPoint + ", memDate="
				+ memDate + ", memNumber=" + memNumber + "]";
	}

}
