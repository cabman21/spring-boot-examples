package com.example.demo.controller;

import com.example.demo.service.SQSMessageService;
import com.example.demo.service.dto.GpsDTO;
import com.example.demo.service.dto.MessageDTO;
import com.example.demo.service.dto.TagDTO;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import software.amazon.awssdk.services.sqs.model.Message;

@RestController
@RequestMapping("/")
public class SQSMessageController {

	private final Logger log = LoggerFactory.getLogger(SQSMessageController.class);

	private final SQSMessageService sqsMessageService;

	public SQSMessageController(SQSMessageService sqsMessageService) {
		this.sqsMessageService = sqsMessageService;
	}

	@PostMapping({ "/sqs/queues/{queueName}/messages" })
	public ResponseEntity<MessageDTO> sendMessage(@PathVariable("queueName") String queueName, @RequestBody GpsDTO gpsDTO, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to save a  : {}");

		MessageDTO response = sqsMessageService.sendMessage(queueName, gpsDTO);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<MessageDTO>(response, responseHeaders, HttpStatus.OK);
	}

	@GetMapping({ "/sqs/queues/{queueName}/messages" })
	public ResponseEntity<List<MessageDTO>> receiveMessage(@PathVariable("queueName") String queueName,
			HttpServletRequest request) throws Exception {
		log.debug("REST request to get all queues");

		List<MessageDTO> response = sqsMessageService.receiveMessage(queueName);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<List<MessageDTO>>(response, responseHeaders, HttpStatus.OK);
	}

	@DeleteMapping({ "/sqs/queues/{queueName}/messages" })
	public ResponseEntity<String> deleteMessage(@PathVariable("queueName") String queueName, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to save a queue : {}");
		
		Message message = null;

		String response = sqsMessageService.deleteMessage(queueName, message);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<String>(response, responseHeaders, HttpStatus.OK);
	}
}
