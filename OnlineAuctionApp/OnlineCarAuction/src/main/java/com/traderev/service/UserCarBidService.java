package com.traderev.service;

import java.util.List;

import com.traderev.model.UserCarBid;
import com.traderev.vo.UserCarBidVO;

public interface UserCarBidService {
	
	UserCarBid findUserRelatedCar(UserCarBidVO userCarBidVO);
	
	void saveUserBid(UserCarBidVO userCarBidVO);
	
	List<UserCarBid> getCarBiddingHistory(UserCarBidVO userCarBidVO);
}
