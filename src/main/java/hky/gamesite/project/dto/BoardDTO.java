package hky.gamesite.project.dto;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class BoardDTO {
	private int postNumber;
	private String postTopic;
	private String postTitle;
	private String memId;
	private String postContent;
	private String postDate;
	private MultipartFile attachedFile;
	private String fileName;

	public int getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(int postNumber) {
		this.postNumber = postNumber;
	}

	public String getPostTopic() {
		return postTopic;
	}

	public void setPostTopic(String postTopic) {
		this.postTopic = postTopic;
	}

	public String getPostTitle() {
		return postTitle;
	}

	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public MultipartFile getAttachedFile() {
		return attachedFile;
	}

	public void setAttachedFile(MultipartFile attachedFile) {
		this.attachedFile = attachedFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String filename) {
		this.fileName = filename;
	}

	@Override
	public String toString() {
		return "PostDTO [postNumber=" + postNumber + ", postTopic=" + postTopic
				+ ", postTitle=" + postTitle + ", memId=" + memId + ", postContent=" + postContent + ", postDate="
				+ postDate + ", attachedFile=" + attachedFile + ", fileName=" + fileName + "]";
	}
}
