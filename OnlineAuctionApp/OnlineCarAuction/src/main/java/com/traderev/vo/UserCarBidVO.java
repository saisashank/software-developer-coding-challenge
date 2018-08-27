package com.traderev.vo;

public class UserCarBidVO {

	String userId;
	String car;
	String carModel;
	String emailAddress;
	String phoneNumber;
	Double bidAmount;
	boolean createNewOne;
	String carAvailability;
	
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
	/**
	 * @return the carAvailability
	 */
	public String getCarAvailability() {
		return carAvailability;
	}
	/**
	 * @param carAvailability the carAvailability to set
	 */
	public void setCarAvailability(String carAvailability) {
		this.carAvailability = carAvailability;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the carModel
	 */
	public String getCarModel() {
		return carModel;
	}
	/**
	 * @param carModel the carModel to set
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
}