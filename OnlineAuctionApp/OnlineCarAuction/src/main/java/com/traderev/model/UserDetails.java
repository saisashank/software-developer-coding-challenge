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
@Table(name="USR_DTLS")
@Component
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="USR_DTLS_ID")
	private Long userDetailsId;
	
	@OneToMany(mappedBy="userDetails",fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<UserCarBid> userCarBidList;
	
	@Column(name="USR_ID")
	private String userId;
	
	@Column(name="FRST_NM")
	private String firstName;
	
	@Column(name="LST_NM")
	private String lastName;
	
	@Column(name="EMAIL_ADD")
	private String emailAddress;
	
	@Column(name="PHNE_NUM")
	private String phoneNumber;
	
	@Column(name="IS_ACTV")
	private String isActive;

	/**
	 * @return the userDetailsId
	 */
	public Long getUserDetailsId() {
		return userDetailsId;
	}

	/**
	 * @param userDetailsId the userDetailsId to set
	 */
	public void setUserDetailsId(Long userDetailsId) {
		this.userDetailsId = userDetailsId;
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
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	 * @return the isActive
	 */
	public String getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(String isActive) {
		this.isActive = isActive;
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