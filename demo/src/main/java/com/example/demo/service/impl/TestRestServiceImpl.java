package com.example.demo.service.impl;

import com.example.demo.service.TestRestService;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class TestRestServiceImpl implements TestRestService {

	private final Logger log = LoggerFactory.getLogger(TestRestServiceImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<Object> findAll() throws Exception {
		log.debug("Request to get all vehicles");

		RestTemplate restTemplate = new RestTemplate();

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
		restTemplate.getMessageConverters().add(converter);

		HttpHeaders headers = new HttpHeaders();

		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(multiValueMap,
				headers);

		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/employees", HttpMethod.GET,
				request, String.class);

		String body = response.getBody();

		JsonParser jsonParser = JsonParserFactory.getJsonParser();

		Map<String, Object> map = jsonParser.parseMap(body);

		@SuppressWarnings("unchecked")
		List<Object> list = (List<Object>) map.get("data");

		log.debug("##### List.size() {}", list.size());

		return list;
	}

	@Override
	public void insert() throws Exception {
		log.debug("Request to save a vehicle : {}", "");
	}
}
