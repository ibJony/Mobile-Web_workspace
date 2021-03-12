package com.jon.pollApp;

public class Category {
	
	private int id;
	private long type_id;
	private String poll_code;
	
	public Category(int id,int type_id,String name){
		this.id = id;
		this.poll_code = name;
		this.type_id = type_id;
	}
	public Category(int type_id) {
		this.type_id = type_id;
	}
	public void setId(int id){
		this.id = id;
		
	}
	
	public void setName(String name){
		this.poll_code = name;
	}
	
	public void setType(int type_id){
		this.type_id = type_id;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String getName(){
		return this.poll_code;
	}
	
	public long getType(){
		return this.type_id;
	}

}
