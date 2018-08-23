package com.traderev.vo;

import java.util.List;

public class UserCarBidVO {

	String userId;
	String car;
	String bidAmount;
	List<CarBidAmountVO> carBidAmountVO;
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
	public String getBidAmount() {
		return bidAmount;
	}
	/**
	 * @param bidAmount the bidAmount to set
	 */
	public void setBidAmount(String bidAmount) {
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
	/**
	 * @return the carBidAmountVO
	 */
	public List<CarBidAmountVO> getCarBidAmountVO() {
		return carBidAmountVO;
	}
	/**
	 * @param carBidAmountVO the carBidAmountVO to set
	 */
	public void setCarBidAmountVO(List<CarBidAmountVO> carBidAmountVO) {
		this.carBidAmountVO = carBidAmountVO;
	}
}