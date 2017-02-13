package com.jon.app;

public class PollResults {
	
	private int id;
	private int poll_id;
	private int poll_code;
	private int poll_response;
	private String poll_comment;
	private String comment_by;
	
	public PollResults(int id,int poll_id,int poll_code,int poll_response,String poll_comment,String comment_by){
		
		this.id = id;		
		this.poll_id = poll_id;
		this.poll_code = poll_code;
		this.poll_response = poll_response;
		this.poll_comment = poll_comment;
		this.comment_by = comment_by;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoll_id() {
		return poll_id;
	}

	public void setPoll_id(int poll_id) {
		this.poll_id = poll_id;
	}

	public int getPoll_code() {
		return poll_code;
	}

	public void setPoll_code(int poll_code) {
		this.poll_code = poll_code;
	}

	public int getPoll_response() {
		return poll_response;
	}

	public void setPoll_response(int poll_response) {
		this.poll_response = poll_response;
	}

	public String getPoll_comment() {
		return poll_comment;
	}

	public void setPoll_comment(String poll_comment) {
		this.poll_comment = poll_comment;
	}


	public String getComment_by() {
		return comment_by;
	}


	public void setComment_by(String comment_by) {
		this.comment_by = comment_by;
	}


}
