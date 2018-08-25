package com.traderev.service;

import java.util.List;
import java.util.Map;

import com.traderev.model.UserCarBid;
import com.traderev.vo.UserCarBidVO;

public interface UserCarBidService {
	
	boolean findUserRelatedCar(UserCarBidVO userCarBidVO);
	
	Map<String,Object> saveUserBid(UserCarBidVO userCarBidVO);
	
	List<UserCarBid> getCarBiddingHistory(UserCarBidVO userCarBidVO);
	
	Map<String,Object> getWinningBid(UserCarBidVO userCarBidVO);
}
