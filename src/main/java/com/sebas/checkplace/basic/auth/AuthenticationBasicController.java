package com.sebas.checkplace.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="*")
@RestController
public class AuthenticationBasicController {

	@GetMapping(path = "/basicauth")
	public AuthenticationBean authentication() {
	  return new AuthenticationBean("You are authenticated");
	}
}
