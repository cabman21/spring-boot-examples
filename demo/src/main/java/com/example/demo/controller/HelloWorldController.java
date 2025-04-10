/**
 * 
 */
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping(value = "/helloworld/v1")
	public String helloWorldV1(@RequestParam("name") String name) {
		return "Hello, world! " + name;
	}

	@GetMapping(value = "/helloworld/v2/{name}")
	public String helloWorldV2(@PathVariable("name") String name) {
		return "Hello, world! " + name;
	}

}
