package com.example.demo.service;

import software.amazon.awssdk.services.sqs.model.Message;

import java.util.List;

import com.example.demo.service.dto.GpsDTO;
import com.example.demo.service.dto.MessageDTO;

public interface SQSMessageService {

	MessageDTO sendMessage(String queueName, GpsDTO gpsDTO) throws Exception;

	List<MessageDTO> receiveMessage(String queueName) throws Exception;

	List<Message> receive(String queueName) throws Exception;

	String deleteMessage(String queueName, Message message) throws Exception;
}
