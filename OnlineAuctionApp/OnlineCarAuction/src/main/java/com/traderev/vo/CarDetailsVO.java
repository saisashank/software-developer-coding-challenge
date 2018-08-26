package com.traderev.vo;

public class CarDetailsVO {

	private String carCompany;
	private String carModel;
	private Double basePrice;
	private String odometerReading;
	/**
	 * @return the carCompany
	 */
	public String getCarCompany() {
		return carCompany;
	}
	/**
	 * @param carCompany the carCompany to set
	 */
	public void setCarCompany(String carCompany) {
		this.carCompany = carCompany;
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
	/**
	 * @return the basePrice
	 */
	public Double getBasePrice() {
		return basePrice;
	}
	/**
	 * @param basePrice the basePrice to set
	 */
	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}
	/**
	 * @return the odometerReading
	 */
	public String getOdometerReading() {
		return odometerReading;
	}
	/**
	 * @param odometerReading the odometerReading to set
	 */
	public void setOdometerReading(String odometerReading) {
		this.odometerReading = odometerReading;
	}
}