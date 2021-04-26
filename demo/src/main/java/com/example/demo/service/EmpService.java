package com.example.demo.service;

import com.example.demo.service.dto.EmpDTO;
import com.example.demo.service.dto.EmpVO;

import java.util.List;

public interface EmpService {

	List<Object> findAll(EmpVO empVO) throws Exception;

	Object findOne(String empno) throws Exception;

	EmpDTO insert(EmpDTO empDTO) throws Exception;

	EmpDTO update(EmpDTO empDTO) throws Exception;

	public int delete(String empno) throws Exception;
}
