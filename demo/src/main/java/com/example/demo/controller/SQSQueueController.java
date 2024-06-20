package com.example.demo.controller;

import com.example.demo.service.SQSQueueService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class SQSQueueController {

	private final Logger log = LoggerFactory.getLogger(SQSQueueController.class);

	private final SQSQueueService sqsQueueService;

	public SQSQueueController(SQSQueueService sqsQueueService) {
		this.sqsQueueService = sqsQueueService;
	}

	@GetMapping({ "/sqs/queues/{queueName}" })
	public ResponseEntity<List<String>> listQueue(@PathVariable("queueName") String queueName,
			HttpServletRequest request) throws Exception {
		log.debug("REST request to get all queues");

		List<String> list = sqsQueueService.listQueue(queueName);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<List<String>>(list, responseHeaders, HttpStatus.OK);
	}

	@GetMapping({ "/sqs/queues/{queueName}/urls" })
	public ResponseEntity<String> getQueueUrl(@PathVariable("queueName") String queueName, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to get all queues");

		String queueUrl = sqsQueueService.getQueueUrl(queueName);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<String>(queueUrl, responseHeaders, HttpStatus.OK);
	}

	@PostMapping({ "/sqs/queues/{queueName}" })
	public ResponseEntity<String> createQueue(@PathVariable("queueName") String queueName, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to save a queue : {}");

		String response = sqsQueueService.createQueue(queueName);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<String>(response, responseHeaders, HttpStatus.OK);
	}

	@PutMapping({ "/sqs/queues/{queueName}" })
	public ResponseEntity<String> purgeQueue(@PathVariable("queueName") String queueName, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to save a queue : {}");

		String response = sqsQueueService.purgeQueue(queueName);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<String>(response, responseHeaders, HttpStatus.OK);
	}

	@PutMapping({ "/sqs/queues/{queueName}/tags" })
	public ResponseEntity<String> tagQueue(@PathVariable("queueName") String queueName, @RequestBody TagDTO tagDTO, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to save a queue : {}");

		String response = sqsQueueService.tagQueue(queueName, tagDTO);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<String>(response, responseHeaders, HttpStatus.OK);
	}

	@PutMapping({ "/sqs/queues/{queueName}/untags" })
	public ResponseEntity<String> untagQueue(@PathVariable("queueName") String queueName, @RequestBody TagDTO tagDTO, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to save a queue : {}");

		String response = sqsQueueService.untagQueue(queueName, tagDTO);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<String>(response, responseHeaders, HttpStatus.OK);
	}

	@DeleteMapping({ "/sqs/queues/{queueName}" })
	public ResponseEntity<String> deleteQueue(@PathVariable("queueName") String queueName, HttpServletRequest request)
			throws Exception {
		log.debug("REST request to save a queue : {}");

		String response = sqsQueueService.deleteQueue(queueName);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "application/json; charset=UTF-8");

		return new ResponseEntity<String>(response, responseHeaders, HttpStatus.OK);
	}
}
