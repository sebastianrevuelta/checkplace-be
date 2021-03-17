package com.sebas.checkplace.restfulwebservices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		for (int i = 0; i < 10; i++) {
		  String encode = encoder.encode("fischer");
		  System.out.println(encode);
		}
	}

}
