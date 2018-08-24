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
	
	@Column(name="CAR")
	private String carName;
	
	@Column(name="BID_AMNT")
	private Double bidAmount;

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
	 * @return the carName
	 */
	public String getCarName() {
		return carName;
	}

	/**
	 * @param carName the carName to set
	 */
	public void setCarName(String carName) {
		this.carName = carName;
	}

	/**
	 * @return the bidAmount
	 */
	public Double getBidAmount() {
		return bidAmount;
	}

	/**
	 * @param bidAmount the bidAmount to set
	 */
	public void setBidAmount(Double bidAmount) {
		this.bidAmount = bidAmount;
	}
}