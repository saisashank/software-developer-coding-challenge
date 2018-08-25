package com.traderev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="CAR_DTLS")
@Component
public class CarDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CAR_DTLS_ID")
	private Long carDetailsId;
	
	@Column(name="CAR_CMPNY")
	private String carCompany;
	
	@Column(name="CAR_MDL")
	private String carModel;
	
	@Column(name="CAR_AVLBTY")
	private String carAvailability;
	
	@Column(name="BASE_PRCE")
	private Double basePrice;

	/**
	 * @return the carDetailsId
	 */
	public Long getCarDetailsId() {
		return carDetailsId;
	}

	/**
	 * @param carDetailsId the carDetailsId to set
	 */
	public void setCarDetailsId(Long carDetailsId) {
		this.carDetailsId = carDetailsId;
	}

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
}