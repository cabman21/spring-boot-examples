package com.example.demo.service.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MessageDTO implements Serializable {

	private static final long serialVersionUID = -5355842270086385227L;

	private String messageId;

	private String body;

	private String receiptHandle;

	private String md5OfBody;

}
