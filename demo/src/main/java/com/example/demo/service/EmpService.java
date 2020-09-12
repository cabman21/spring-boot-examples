package com.example.demo.service;

import com.example.demo.service.dto.EmpDTO;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface EmpService {

	List<Object> findAll(EmpDTO empDTO) throws Exception;

	Object findOne(String empno) throws Exception;

	EmpDTO insert(EmpDTO empDTO, Map<String, MultipartFile> files) throws Exception;

	EmpDTO update(EmpDTO empDTO, Map<String, MultipartFile> files) throws Exception;

	public int delete(String empno) throws Exception;
}
