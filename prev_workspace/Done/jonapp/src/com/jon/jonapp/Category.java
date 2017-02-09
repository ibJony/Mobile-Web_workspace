package com.jon.jonapp;

public class Category {
	
	private int id;
	private long type_id;
	private String poll_code;
	private String poll_desc;
	private String poll_date;
	
	public Category(int id,int type_id,String poll_code,String poll_desc,String poll_date){
		this.id = id;		
		this.type_id = type_id;
		this.poll_code = poll_code;
		this.poll_desc = poll_desc;
		this.poll_date = poll_date;
	}
	public void setId(int id){
		this.id = id;
		
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setPollCode(String poll_code){
		this.poll_code = poll_code;
	}
	
	public String getPollCode(){
		return this.poll_code;
	}
	
	public void setType(int type_id){
		this.type_id = type_id;
	}
	
	public long getType(){
		return this.type_id;
	}
	public String getPoll_desc() {
		return poll_desc;
	}
	public void setPoll_desc(String poll_desc) {
		this.poll_desc = poll_desc;
	}
	public String getPoll_date() {
		return poll_date;
	}
	public void setPoll_date(String poll_date) {
		this.poll_date = poll_date;
	}

}
