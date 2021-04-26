package com.example.demo.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpVO extends EmpDTO {

	private static final long serialVersionUID = -5814023024462099016L;

	private int offset = 0;

	private int limit = 10;

}
