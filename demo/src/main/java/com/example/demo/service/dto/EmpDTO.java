package com.example.demo.service.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class EmpDTO implements Serializable {

	private static final long serialVersionUID = 334152017587974132L;

	private String empno = null;

	private String ename = null;

	private String job = null;

	private String mgr = null;

	private String hiredate = null;

	private Float sal = null;

	private Float comm = null;

	private String deptno = null;

}
