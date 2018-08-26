package com.traderev.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.traderev.service.CarDetailsService;
import com.traderev.vo.CarDetailsVO;

@RestController
public class CreateRecordController {
	
	@Autowired
	CarDetailsService carDetailsService;
	
	@RequestMapping(value="/updateCarDetails",produces=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> updateCarDetails(@RequestBody CarDetailsVO carDetailsVO) {
		return carDetailsService.updateCarDetails(carDetailsVO);
	}

	@RequestMapping(value="/createCarForBid",produces=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> createCarForBid(@RequestBody CarDetailsVO carDetailsVO) {
		return carDetailsService.createCarForBid(carDetailsVO);
	}
}
