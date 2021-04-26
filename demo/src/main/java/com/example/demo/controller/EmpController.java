package com.example.demo.controller;

import com.example.demo.service.EmpService;
import com.example.demo.service.dto.EmpDTO;
import com.example.demo.service.dto.EmpVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
	public ResponseEntity<List<Object>> getAll(@ModelAttribute EmpVO empVO, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to get all employees");

		List<Object> list = empService.findAll(empVO);

		return new ResponseEntity<List<Object>>(list, HttpStatus.OK);
	}

	@GetMapping("/employees/{empno}")
	public ResponseEntity<Object> getOne(@PathVariable String empno, HttpServletRequest request) throws Exception {
		log.debug("REST request to get a employee : {}", empno);

		Object obj = empService.findOne(empno);

		return new ResponseEntity<Object>(obj, HttpStatus.OK);
	}

	@PostMapping({ "/employees" })
	public ResponseEntity<EmpDTO> create(@RequestBody EmpDTO empDTO, HttpServletRequest request) throws Exception {
		log.debug("REST request to save a employee : {}");

		EmpDTO result = empService.insert(empDTO);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<EmpDTO>(null, responseHeaders, HttpStatus.OK);
	}

	@PutMapping("/employees/{empno}")
	public ResponseEntity<EmpDTO> update(@PathVariable String empno, @RequestBody EmpDTO empDTO,
			HttpServletRequest request) throws Exception {
		log.debug("REST request to update a employee : {}", empno);

		empDTO.setEmpno(empno);

		EmpDTO result = empService.update(empDTO);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<EmpDTO>(null, responseHeaders, HttpStatus.OK);
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

		return new ResponseEntity<Boolean>(null, responseHeaders, HttpStatus.OK);
	}
}
