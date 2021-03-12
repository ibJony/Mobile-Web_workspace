package com.jon.supra.db;

import java.util.*;

public class Updata {
	private int id;
	private String title,descrip,created_at,updated_at;
	
	public Updata(int id, String title, String descrip, String created_at, String updated_at){
		this.id = id;
		this.title = title;
		this.descrip = descrip;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescrip() {
		return descrip;
	}

	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	//public String toString() {
	//	return "[ title=" + title + ",descrip=" + 
		//		descrip + " , created_at=" 
		//		+ created_at +"]";
	//}

}
