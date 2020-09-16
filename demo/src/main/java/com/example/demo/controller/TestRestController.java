/**
 * 
 */
package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TestRestService;
import com.example.demo.service.dto.EmpDTO;

/**
 * @author cabma
 *
 */
@RestController
public class TestRestController {

	private final Logger log = LoggerFactory.getLogger(EmpController.class);

	private final TestRestService testRestService;

	public TestRestController(TestRestService testRestService) {
		this.testRestService = testRestService;
	}

	@GetMapping("/vehicles")
	public ResponseEntity<Map<String, Object>> getAll(@ModelAttribute EmpDTO empDTO, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to get all employees");

		List<Object> list = testRestService.findAll();

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("data", list);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

}
