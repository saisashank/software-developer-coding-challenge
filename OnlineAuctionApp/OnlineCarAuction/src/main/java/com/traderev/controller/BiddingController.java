package com.traderev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.traderev.model.UserCarBid;
import com.traderev.service.UserCarBidService;
import com.traderev.vo.UserCarBidVO;

@RestController
@RequestMapping(value ="/biddingService")
public class BiddingController {

	@Autowired
	UserCarBidService userCarBidService;
	
	@RequestMapping(value="/getUserRelatedCar",produces=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public UserCarBid getUserRelatedCar(@RequestBody UserCarBidVO userCarBidVO) {
		return userCarBidService.findUserRelatedCar(userCarBidVO);
	}
	
	@RequestMapping(value="/saveUserBid",produces=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.PUT,consumes=MediaType.APPLICATION_JSON_VALUE)
	public void saveUserBid(@RequestBody UserCarBidVO userCarBidVO) {
		userCarBidService.saveUserBid(userCarBidVO);
	}
	
	@RequestMapping(value="/getCarBidHistory",produces=MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<UserCarBid> getCarBidHistory(@RequestBody UserCarBidVO userCarBidVO) {
		return userCarBidService.getCarBiddingHistory(userCarBidVO);
	}
}
