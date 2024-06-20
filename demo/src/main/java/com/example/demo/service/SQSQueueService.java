package com.example.demo.service;

import java.util.List;

import com.example.demo.service.dto.TagDTO;

public interface SQSQueueService {

	List<String> listQueue(String queueName) throws Exception;

	String getQueueUrl(String queueName) throws Exception;

	String createQueue(String queueName) throws Exception;

	String purgeQueue(String queueName) throws Exception;

	String tagQueue(String queueName, TagDTO tagDTO) throws Exception;

	String untagQueue(String queueName, TagDTO tagDTO) throws Exception;

	String deleteQueue(String queueUrl) throws Exception;
}
