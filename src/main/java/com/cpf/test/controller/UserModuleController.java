package com.cpf.test.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cpf.test.entity.UserInfo;
import com.wordnik.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/user")
public class UserModuleController {

	
	private static Map<String, UserInfo> infoMap = new HashMap<String, UserInfo>();

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@ApiOperation(value = "添加用户", httpMethod = "POST", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, response = ResponseEntity.class)
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> add(RequestEntity<UserInfo> entity) {
		UserInfo info = entity.getBody();
		logger.debug("===params: {}", entity);
		infoMap.put(info.getName(), info);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@ApiOperation(value = "查看所有用户", httpMethod = "GET", produces = MediaType.APPLICATION_JSON_VALUE, response = ResponseEntity.class)
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<UserInfo>> list() {
		logger.debug("list all infos ");

		Collection<UserInfo> infos = infoMap.values();

		return new ResponseEntity<Collection<UserInfo>>(infos, HttpStatus.OK);
	}

	@ApiOperation(value = "删除用户", httpMethod = "DELETE", produces = MediaType.APPLICATION_JSON_VALUE, response = ResponseEntity.class)
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@RequestParam String name) {
		logger.debug("===params: {}", name);
		infoMap.remove(name);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
