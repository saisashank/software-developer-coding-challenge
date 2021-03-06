package com.traderev.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traderev.model.UserDetails;
import com.traderev.repository.UserDetailsRepository;
import com.traderev.repository.UserDetailsUpdateRepository;
import com.traderev.vo.UserDetailsVO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	UserDetailsRepository userDetailsRepository;
	
	@Autowired
	UserDetailsUpdateRepository userDetailsUpdateRepository;

	@Override
	public Map<String,Object> createUserDetails(UserDetailsVO userDetailsVO) {
		Map<String,Object> responseMap = new HashMap<>();
		try {
			UserDetails userDetails = userDetailsRepository.findByUserIdAndEmailAddress(userDetailsVO.getUserId(), userDetailsVO.getEmailAddress());
			if(userDetails != null) {
				responseMap.put("response", "User trying to create already exists...");
			}else {
				List<UserDetails> userDetailsRepo = userDetailsRepository.findAll();
				for(UserDetails userDetailsList: userDetailsRepo) {
					if(userDetailsList.getUserId().equalsIgnoreCase(userDetailsVO.getUserId()) || userDetailsList.getEmailAddress().equalsIgnoreCase(userDetailsVO.getEmailAddress())) {
						responseMap.put("response", "Please check the UserId/Email given.... they already exist...");
					}else {
						UserDetails userDetailsNew = new UserDetails();
						userDetailsNew.setUserId(userDetailsVO.getUserId());
						userDetailsNew.setFirstName(userDetailsVO.getFirstName());
						userDetailsNew.setLastName(userDetailsVO.getLastName());
						userDetailsNew.setPhoneNumber(userDetailsVO.getPhoneNumber());
						userDetailsNew.setEmailAddress(userDetailsVO.getEmailAddress());
						userDetailsNew.setIsActive("Y");
						userDetailsRepository.saveAndFlush(userDetailsNew);
						responseMap.put("response", "successfully created User");
						break;
					}
				}
				
				if(userDetailsRepo.isEmpty()) {
					UserDetails userDetailsNew = new UserDetails();
					userDetailsNew.setUserId(userDetailsVO.getUserId());
					userDetailsNew.setFirstName(userDetailsVO.getFirstName());
					userDetailsNew.setLastName(userDetailsVO.getLastName());
					userDetailsNew.setPhoneNumber(userDetailsVO.getPhoneNumber());
					userDetailsNew.setEmailAddress(userDetailsVO.getEmailAddress());
					userDetailsNew.setIsActive("Y");
					userDetailsRepository.saveAndFlush(userDetailsNew);
					responseMap.put("response", "successfully created User");
				}
			}
		}catch(Exception e) {
			logger.info("Exception in the createUserDetails "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}
	
	@Override
	public Map<String, Object> updateUserDetails(UserDetailsVO userDetailsVO) {
		Map<String,Object> responseMap = new HashMap<>();
		try {
			UserDetails userDetails = userDetailsRepository.findByUserIdAndEmailAddress(userDetailsVO.getUserId(), userDetailsVO.getEmailAddress());
			if(userDetails.getUserId().equalsIgnoreCase(userDetailsVO.getUserId()) || userDetails.getEmailAddress().equalsIgnoreCase(userDetailsVO.getEmailAddress())) {
				userDetailsUpdateRepository.updateUserDetails(userDetailsVO);
				responseMap.put("response","successful update of user details");
			}else {
				responseMap.put("response","Please check the User/Email provided for update...");
			}
		}catch(Exception e) {
			logger.info("Exception in the createUserDetails "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}

	@Override
	public Map<String, Object> updateUserActiveStatus(UserDetailsVO userDetailsVO) {
		Map<String,Object> responseMap = new HashMap<>();
		try {
			UserDetails userDetails = userDetailsRepository.findByUserIdAndEmailAddress(userDetailsVO.getUserId(), userDetailsVO.getEmailAddress());
			if(userDetails != null) {
				if(userDetailsVO.getUserStatus().equalsIgnoreCase("Y")) {
					userDetailsUpdateRepository.updateUserActiveStatus(userDetailsVO);
					responseMap.put("response", "updated the Active Status of user...");
				}else {
					userDetailsUpdateRepository.deleteUserDetails(userDetails.getUserDetailsId());
					responseMap.put("response", "User deleted from the DB permanently...");
				}
			}else {
				responseMap.put("response", "User intended to delete/update is not present...");
			}
		}catch(Exception e) {
			logger.info("Exception in the updateUserActiveStatus "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}

	@Override
	public Map<String, Object> getAllActiveUsers(UserDetailsVO userDetailsVO) {
		Map<String,Object> responseMap = new HashMap<>();
		try {
			List<UserDetails> userDetailsList = userDetailsRepository.findByIsActive(userDetailsVO.getIsActive());
			if(!userDetailsList.isEmpty()) {
				if(userDetailsVO.getIsActive().equalsIgnoreCase("Y")) {
					responseMap.put("header", "List of User who are Active...");
					responseMap.put("response", userDetailsList);
				}else {
					responseMap.put("header", "List of User who are Inactive...");
					responseMap.put("response", userDetailsList);
				}
			}else {
				responseMap.put("header", "No User's Available...");
				responseMap.put("response", userDetailsList);
			}
		}catch(Exception e) {
			logger.info("Exception in the getWinningBid "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}
}
