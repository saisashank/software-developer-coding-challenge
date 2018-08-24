package com.traderev.service;

import com.traderev.model.UserCarBid;
import com.traderev.vo.UserCarBidVO;

public interface UserCarBidService {
	
	UserCarBid findUserRelatedCar(UserCarBidVO userCarBidVO);
	
	void saveUserBid(UserCarBidVO userCarBidVO);
}
