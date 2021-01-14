package com.sebas.checkplace.restfulwebservices.helloworld;

public class HelloWorldBean {

	private String message;
		
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HelloWorldBean(String message) {
		this.message = message;
	}
}
