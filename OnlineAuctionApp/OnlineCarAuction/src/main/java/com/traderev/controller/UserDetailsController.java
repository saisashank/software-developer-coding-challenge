package com.traderev.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.traderev.service.UserDetailsService;
import com.traderev.vo.UserDetailsVO;

@RestController
public class UserDetailsController {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@RequestMapping(value="/createUser",produces=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> createUser(@RequestBody UserDetailsVO userDetailsVO) {
		return userDetailsService.createUserDetails(userDetailsVO);
	}
	
	@RequestMapping(value="/updateUserDetails",produces=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> updateUserDetails(@RequestBody UserDetailsVO userDetailsVO) {
		return userDetailsService.updateUserDetails(userDetailsVO);
	}
	
	@RequestMapping(value="/updateUserActiveStatus",produces=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> updateUserActiveStatus(@RequestBody UserDetailsVO userDetailsVO) {
		return userDetailsService.updateUserActiveStatus(userDetailsVO);
	}
	
	@RequestMapping(value="/getAllActiveUsers",produces=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> getAllActiveUsers(@RequestBody UserDetailsVO userDetailsVO) {
		return userDetailsService.getAllActiveUsers(userDetailsVO);
	}
}
