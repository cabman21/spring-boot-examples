package com.example.demo.service.impl;

import com.example.demo.service.SQSMessageService;
import com.example.demo.service.SQSQueueService;
import com.example.demo.service.dto.GpsDTO;
import com.example.demo.service.dto.MessageDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.DeleteMessageResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
import software.amazon.awssdk.services.sqs.model.SqsException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SQSMessageServiceImpl implements SQSMessageService {

	private final Logger log = LoggerFactory.getLogger(SQSMessageServiceImpl.class);

	private SQSQueueService sqsQueueService;

	private SqsClient sqsClient = SqsClient.builder().region(Region.AP_NORTHEAST_2).build();

	public SQSMessageServiceImpl(SQSQueueService sqsQueueService) {
		this.sqsQueueService = sqsQueueService;
	}

	@Override
	public MessageDTO sendMessage(String queueName, GpsDTO gpsDTO) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		String queueUrl = sqsQueueService.getQueueUrl(queueName);

		log.debug("Request to delete a queueUrl : {}", queueUrl);
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(gpsDTO);

		SendMessageRequest sendMessageRequest = SendMessageRequest.builder().queueUrl(queueUrl)
				.messageBody(json).delaySeconds(10).build();

		SendMessageResponse message = sqsClient.sendMessage(sendMessageRequest);
		
		MessageDTO messageDTO = new MessageDTO();
		
		messageDTO.setMessageId(message.messageId());
		messageDTO.setMd5OfBody(message.md5OfMessageBody());

		return messageDTO;
	}

	@Override
	public List<MessageDTO> receiveMessage(String queueName) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		String queueUrl = sqsQueueService.getQueueUrl(queueName);

		log.debug("Request to delete a queueUrl : {}", queueUrl);

		List<Message> messages = this.receive(queueName);

		List<MessageDTO> list = new ArrayList<MessageDTO>();

		for (Message message : messages) {
			MessageDTO messageDTO = new MessageDTO();

			messageDTO.setMessageId(message.messageId());
			messageDTO.setBody(message.body());
			messageDTO.setMd5OfBody(message.md5OfBody());
			messageDTO.setReceiptHandle(message.receiptHandle());

			list.add(messageDTO);
		}

		return list;
	}

	@Override
	public List<Message> receive(String queueName) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		String queueUrl = sqsQueueService.getQueueUrl(queueName);

		log.debug("Request to delete a queueUrl : {}", queueUrl);

		ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder().queueUrl(queueUrl)
				.maxNumberOfMessages(5).build();

		List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();

		return messages;
	}

	@Override
	public String deleteMessage(String queueName, Message _message) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		String queueUrl = sqsQueueService.getQueueUrl(queueName);

		log.debug("Request to delete a queueUrl : {}", queueUrl);

		List<Message> messages = this.receive(queueName);

		try {
			for (Message message : messages) {
				DeleteMessageRequest deleteMessageRequest = DeleteMessageRequest.builder().queueUrl(queueUrl)
						.receiptHandle(message.receiptHandle()).build();

				DeleteMessageResponse response = sqsClient.deleteMessage(deleteMessageRequest);
			}
		} catch (SqsException e) {
			System.err.println(e.awsErrorDetails().errorMessage());
			System.exit(1);
		}

		return null;
	}
}
