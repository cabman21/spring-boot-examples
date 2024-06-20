package com.example.demo.service.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TagDTO implements Serializable {

	private static final long serialVersionUID = -791851499771356784L;

	private String key = null;

	private String value = null;

}
