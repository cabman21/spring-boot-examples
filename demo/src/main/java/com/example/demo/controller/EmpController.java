package com.example.demo.controller;

import com.example.demo.service.EmpService;
import com.example.demo.service.dto.EmpDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class EmpController {

	private final Logger log = LoggerFactory.getLogger(EmpController.class);

	private final EmpService empService;

	public EmpController(EmpService empService) {
		this.empService = empService;
	}

	@GetMapping("/employees")
	public ResponseEntity<Map<String, Object>> getAll(@ModelAttribute EmpDTO empDTO, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to get all employees");

		List<Object> list = empService.findAll(empDTO);

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("data", list);

		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}

	@GetMapping("/employees/{empno}")
	public ResponseEntity<Object> getOne(@PathVariable String empno, HttpServletRequest request) throws Exception {
		log.debug("REST request to get a employee : {}", empno);

		Object obj = empService.findOne(empno);

		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}

	@PostMapping({ "/employees" })
	public ResponseEntity<EmpDTO> create(@ModelAttribute EmpDTO empDTO, MultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) throws Exception {
		log.debug("REST request to save a employee : {}");

		Map<String, MultipartFile> files = multipartRequest.getFileMap();

		EmpDTO result = empService.insert(empDTO, files);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<EmpDTO>(result, responseHeaders, HttpStatus.OK);
	}

	@PutMapping("/employees/{empno}")
	public ResponseEntity<EmpDTO> update(@PathVariable String empno, @ModelAttribute EmpDTO empDTO,
			MultipartHttpServletRequest multipartRequest, HttpServletRequest request) throws Exception {
		log.debug("REST request to update a employee : {}", empno);

		empDTO.setEmpno(empno);

		Map<String, MultipartFile> files = multipartRequest.getFileMap();

		EmpDTO result = empService.update(empDTO, files);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<EmpDTO>(result, responseHeaders, HttpStatus.OK);
	}

	@DeleteMapping("/employees/{empno}")
	public ResponseEntity<Boolean> delete(@PathVariable String empno, HttpServletRequest request) throws Exception {
		log.debug("REST request to delete a employee : {}", empno);

		boolean success = false;

		int count = empService.delete(empno);

		if (count > 0) {
			success = true;
		}

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<Boolean>(success, responseHeaders, HttpStatus.OK);
	}
}
