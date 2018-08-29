package com.traderev.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CAR_DTLS")
@Component
public class CarDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CAR_DTLS_ID")
	private Long carDetailsId;
	
	@OneToMany(mappedBy="carDetails",fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<UserCarBid> userCarBidList;
	
	@Column(name="CAR_CMPNY")
	private String carCompany;
	
	@Column(name="CAR_MDL")
	private String carModel;
	
	@Column(name="CAR_AVLBTY")
	private String carAvailability;
	
	@Column(name="BASE_PRCE")
	private Double basePrice;
	
	@Column(name="ODOMTR_RDNG")
	private String odometerReading;

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

	/**
	 * @return the userCarBidList
	 */
	public List<UserCarBid> getUserCarBidList() {
		return userCarBidList;
	}

	/**
	 * @param userCarBidList the userCarBidList to set
	 */
	public void setUserCarBidList(List<UserCarBid> userCarBidList) {
		this.userCarBidList = userCarBidList;
	}
}