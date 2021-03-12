package com.jon.pollapp;

public class Options {
	private int id;
	private String poll_id,poll_code,poll_response,poll_comment,poll_option_choice;
	
	public Options(int id, String poll_id, String poll_code, String poll_response, String poll_comment,String poll_option_choice){
		this.id = id;
		this.poll_id = poll_id;
		this.poll_code = poll_code;
		this.poll_response = poll_response;
		this.poll_comment = poll_comment;
		this.poll_option_choice = poll_option_choice;
	} 


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getPollId() {
		return poll_id;
	}
	
	public String setPollId(){
		return this.poll_id = poll_id;
	}

	public String getPollCode() {
		return poll_code;
	}

	public void setPollCode(String descrip) {
		this.poll_code = poll_code;
	}
}
