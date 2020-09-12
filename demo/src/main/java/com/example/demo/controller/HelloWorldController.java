/**
 * 
 */
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cabma
 *
 */
@RestController
public class HelloWorldController {

	@GetMapping(value = "/helloworld")
	public String helloWorld() {
		return "Hello, world!";
	}

}
