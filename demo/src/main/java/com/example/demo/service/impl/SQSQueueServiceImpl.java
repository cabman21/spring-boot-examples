package com.example.demo.service.impl;

import com.example.demo.service.SQSQueueService;
import com.example.demo.service.dto.TagDTO;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.CreateQueueRequest;
import software.amazon.awssdk.services.sqs.model.CreateQueueResponse;
import software.amazon.awssdk.services.sqs.model.DeleteQueueRequest;
import software.amazon.awssdk.services.sqs.model.DeleteQueueResponse;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.ListQueuesRequest;
import software.amazon.awssdk.services.sqs.model.ListQueuesResponse;
import software.amazon.awssdk.services.sqs.model.PurgeQueueRequest;
import software.amazon.awssdk.services.sqs.model.PurgeQueueResponse;
import software.amazon.awssdk.services.sqs.model.TagQueueRequest;
import software.amazon.awssdk.services.sqs.model.TagQueueResponse;
import software.amazon.awssdk.services.sqs.model.UntagQueueRequest;
import software.amazon.awssdk.services.sqs.model.UntagQueueResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SQSQueueServiceImpl implements SQSQueueService {

	private final Logger log = LoggerFactory.getLogger(SQSQueueServiceImpl.class);

	@Override
	public List<String> listQueue(String queueName) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		SqsClient sqsClient = SqsClient.builder().region(Region.AP_NORTHEAST_2).build();

        ListQueuesRequest listQueuesRequest = ListQueuesRequest.builder().queueNamePrefix(queueName).build();

        ListQueuesResponse listQueuesResponse = sqsClient.listQueues(listQueuesRequest);

		return listQueuesResponse.queueUrls();
	}

	@Override
	public String getQueueUrl(String queueName) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		SqsClient sqsClient = SqsClient.builder().region(Region.AP_NORTHEAST_2).build();

		GetQueueUrlRequest getQueueUrlRequest = GetQueueUrlRequest.builder().queueName(queueName).build();

		GetQueueUrlResponse listQueuesResponse = sqsClient.getQueueUrl(getQueueUrlRequest);

		return listQueuesResponse.queueUrl();
	}

	@Override
	public String createQueue(String queueName) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		SqsClient sqsClient = SqsClient.builder().region(Region.AP_NORTHEAST_2).build();

		CreateQueueRequest createQueueRequest = CreateQueueRequest.builder().queueName(queueName).build();

		CreateQueueResponse response = sqsClient.createQueue(createQueueRequest);

		return response.queueUrl();
	}

	@Override
	public String purgeQueue(String queueName) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		SqsClient sqsClient = SqsClient.builder().region(Region.AP_NORTHEAST_2).build();
		
		String queueUrl = this.getQueueUrl(queueName);
		
		log.debug("Request to delete a queueUrl : {}", queueUrl);

		PurgeQueueRequest purgeQueueRequest = PurgeQueueRequest.builder().queueUrl(queueUrl).build();

		PurgeQueueResponse response = sqsClient.purgeQueue(purgeQueueRequest);

		return response.toString();
	}

	@Override
	public String tagQueue(String queueName, TagDTO tagDTO) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		SqsClient sqsClient = SqsClient.builder().region(Region.AP_NORTHEAST_2).build();
		
		String queueUrl = this.getQueueUrl(queueName);
		
		log.debug("Request to delete a queueUrl : {}", queueUrl);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(tagDTO.getKey(), tagDTO.getValue());

		TagQueueRequest tagQueueRequest = TagQueueRequest.builder().queueUrl(queueUrl).tags(map).build();

		TagQueueResponse response = sqsClient.tagQueue(tagQueueRequest);

		return response.toString();
	}

	@Override
	public String untagQueue(String queueName, TagDTO tagDTO) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		SqsClient sqsClient = SqsClient.builder().region(Region.AP_NORTHEAST_2).build();
		
		String queueUrl = this.getQueueUrl(queueName);
		
		log.debug("Request to delete a queueUrl : {}", queueUrl);

		UntagQueueRequest untagQueueRequest = UntagQueueRequest.builder().queueUrl(queueUrl).tagKeys(tagDTO.getKey()).build();

		UntagQueueResponse response = sqsClient.untagQueue(untagQueueRequest);

		return response.toString();
	}

	@Override
	public String deleteQueue(String queueName) throws Exception {
		log.debug("Request to delete a queueName : {}", queueName);

		SqsClient sqsClient = SqsClient.builder().region(Region.AP_NORTHEAST_2).build();
		
		String queueUrl = this.getQueueUrl(queueName);
		
		log.debug("Request to delete a queueUrl : {}", queueUrl);

		DeleteQueueRequest deleteQueueRequest = DeleteQueueRequest.builder().queueUrl(queueUrl).build();

		DeleteQueueResponse response = sqsClient.deleteQueue(deleteQueueRequest);

		return response.toString();
	}
}
