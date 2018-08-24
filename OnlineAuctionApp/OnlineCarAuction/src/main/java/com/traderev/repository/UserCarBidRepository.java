package com.traderev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traderev.model.UserCarBid;

@Repository
public interface UserCarBidRepository extends JpaRepository<UserCarBid, Long> {

	UserCarBid findByUserIdAndBidAmount(String userId, Double bidAmount);
	
	UserCarBid findByUserId(String userId);
}
