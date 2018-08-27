package com.traderev.repository;

import java.util.List;

import com.traderev.model.UserCarBid;

public interface CarBidHistoryRepository {
	
	List<UserCarBid> getCarHistoryBid(String carName,String carModel);
}
