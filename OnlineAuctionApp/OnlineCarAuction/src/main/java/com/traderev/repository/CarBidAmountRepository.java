package com.traderev.repository;

import com.traderev.vo.UserCarBidVO;

public interface CarBidAmountRepository {

	String updateUserCarBid(UserCarBidVO userCarBidVO);
	
	String updateAuctionStatus(UserCarBidVO userCarBidVO);
}
