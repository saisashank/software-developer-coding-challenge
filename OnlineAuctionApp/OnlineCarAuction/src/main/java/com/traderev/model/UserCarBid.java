package com.traderev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="USR_CAR_BID")
@Component
public class UserCarBid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USR_CAR_BID_ID")
	private Long userCarBidId;
	
	@Column(name="USR_ID")
	private String userId;
	
	@Column(name="CAR_BID_AMNT")
	private String carBidAmount;

	/**
	 * @return the userCarBidId
	 */
	public Long getUserCarBidId() {
		return userCarBidId;
	}

	/**
	 * @param userCarBidId the userCarBidId to set
	 */
	public void setUserCarBidId(Long userCarBidId) {
		this.userCarBidId = userCarBidId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the carBidAmount
	 */
	public String getCarBidAmount() {
		return carBidAmount;
	}

	/**
	 * @param carBidAmount the carBidAmount to set
	 */
	public void setCarBidAmount(String carBidAmount) {
		this.carBidAmount = carBidAmount;
	}
}