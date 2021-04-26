package com.example.demo.service.impl;

import com.example.demo.service.EmpService;
import com.example.demo.service.dto.EmpDTO;
import com.example.demo.service.dto.EmpVO;
import com.example.demo.service.mapper.EmpMapper;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

	private final Logger log = LoggerFactory.getLogger(EmpServiceImpl.class);

	private EmpMapper mapper;

	public EmpServiceImpl(EmpMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Object> findAll(EmpVO empVO) throws Exception {
		log.debug("Request to get all employees");
		
		return mapper.findAll(empVO);
	}

	@Override
	@Transactional(readOnly = true)
	public Object findOne(String empno) throws Exception {
		log.debug("Request to get a employee : {}", empno);

		Object obj = mapper.findOne(empno);

		return obj;
	}

	@Override
	public EmpDTO insert(EmpDTO empDTO) throws Exception {
		log.debug("Request to save a employee : {}", empDTO.getEmpno());

		int count = mapper.insert(empDTO);

		if (count == 0) {
			throw new Exception("No Data Inserted.");
		}

		return empDTO;
	}

	@Override
	public EmpDTO update(EmpDTO empDTO) throws Exception {
		log.debug("Request to update a employee : {}", empDTO.getEmpno());

		int count = mapper.update(empDTO);

		if (count == 0) {
			throw new Exception("No Data Updated.");
		}

		return empDTO;
	}

	@Override
	public int delete(String empno) throws Exception {
		log.debug("Request to delete a employee : {}", empno);

		int count = mapper.delete(empno);

		if (count == 0) {
			throw new Exception("No Data Deleted.");
		}

		return count;
	}
}
