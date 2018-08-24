package com.traderev.model;

public class CarBidHistory {

	private String userId;
	private String carName;
	private Double bidAmount;
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
