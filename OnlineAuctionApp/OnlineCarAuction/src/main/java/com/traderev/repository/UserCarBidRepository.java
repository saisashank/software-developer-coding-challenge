package com.traderev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traderev.model.UserCarBid;

@Repository
public interface UserCarBidRepository extends JpaRepository<UserCarBid, Long> {

	UserCarBid findByUserIdAndCarName(String userId, String carName);
	
	UserCarBid findByUserId(String userId);
}
