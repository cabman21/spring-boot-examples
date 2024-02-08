/**
 * 
 */
package com.example.demo.controller;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
		log.debug("REST request to get all vehicles");

		List<Object> list = testRestService.findAll();

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("data", list);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@PostMapping({ "/vehicles" })
	public ResponseEntity<Object> create(@ModelAttribute EmpDTO empDTO, MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) throws Exception {
		log.debug("REST request to save a vehicle : {}");

		Map<String, MultipartFile> files = multipartRequest.getFileMap();

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		MultipartFile file;

		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();

			file = entry.getValue();

			file.getName();
			file.getOriginalFilename();
			file.getSize();

			log.debug("File {} {} {} {} {} {} {}", file.getName(), file.getOriginalFilename(), file.getContentType(),
					file.isEmpty(), file.getSize(), file.getInputStream(), file.getResource());
		}

		testRestService.insert();

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<Object>(files.size(), responseHeaders, HttpStatus.OK);
	}

}
