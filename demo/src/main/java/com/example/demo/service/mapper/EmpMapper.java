package com.example.demo.service.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.service.dto.EmpDTO;

public interface EmpMapper {

	public List<Object> findAll(EmpDTO empDTO) throws Exception;

	public Object findOne(@Param("empno") String empno) throws Exception;

	public int insert(EmpDTO empDTO) throws Exception;

	public int update(EmpDTO empDTO) throws Exception;

	public int delete(@Param("empno") String empno) throws Exception;
}
