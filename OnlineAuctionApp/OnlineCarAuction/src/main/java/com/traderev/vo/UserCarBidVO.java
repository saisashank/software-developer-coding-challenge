package com.traderev.vo;

public class UserCarBidVO {

	String userId;
	String car;
	Double bidAmount;
	boolean createNewOne;
	
	/**
	 * @return the createNewOne
	 */
	public boolean isCreateNewOne() {
		return createNewOne;
	}
	/**
	 * @param createNewOne the createNewOne to set
	 */
	public void setCreateNewOne(boolean createNewOne) {
		this.createNewOne = createNewOne;
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
	 * @return the car
	 */
	public String getCar() {
		return car;
	}
	/**
	 * @param car the car to set
	 */
	public void setCar(String car) {
		this.car = car;
	}
}