package com.sebas.checkplace.restfulwebservices;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.checkplace.restfulwebservices.helloworld.HelloWorldBean;

@CrossOrigin(origins="*")
@RestController
public class HelloWorldController {

	@GetMapping(path = "/hello")
	public String helloworld() {
		return "Hello!";
	}
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloworldBean() {
		return new HelloWorldBean("Hello!");
	}
	
	@GetMapping(path = "/hello-world-bean/path-variable/{name}")
	public HelloWorldBean helloworldBeanPathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello! " + name );
	}
}
