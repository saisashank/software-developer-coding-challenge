package com.traderev.service;

import java.util.Map;

import com.traderev.vo.UserCarBidVO;

public interface UserCarBidService {
	
	boolean findUserRelatedCar(UserCarBidVO userCarBidVO);
	
	Map<String,Object> saveUserBid(UserCarBidVO userCarBidVO);
	
	Map<String,Object> getCarBiddingHistory(UserCarBidVO userCarBidVO);
	
	Map<String,Object> getWinningBid(UserCarBidVO userCarBidVO);
}
