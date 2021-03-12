package com.jon.talii.lib;

public class AroundMe {
	public String name;
	public String abbreviation;
	public String resourceId;

	public AroundMe() {
		// TODO Auto-generated constructor stub
	}

	public AroundMe(String name, String abbreviation, String resourceFilePath) {
		this.name = name;
		this.abbreviation = abbreviation;
		this.resourceId = resourceFilePath;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
