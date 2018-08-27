package com.traderev.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traderev.model.CarDetails;
import com.traderev.model.UserCarBid;
import com.traderev.repository.CarBidAmountRepository;
import com.traderev.repository.CarBidHistoryRepository;
import com.traderev.repository.CarDetailsRepository;
import com.traderev.repository.CarUpdateDetailsRepository;
import com.traderev.repository.UserCarBidRepository;
import com.traderev.vo.UserCarBidVO;

@Service
public class UserCarBidServiceImpl implements UserCarBidService {
	
	private final Logger logger = LoggerFactory.getLogger(UserCarBidServiceImpl.class);

	@Autowired
	UserCarBidRepository userCarBidRepository;
	
	@Autowired
	CarBidAmountRepository carBidAmountRepository;
	
	@Autowired
	CarBidHistoryRepository carBidHistoryRepository;
	
	@Autowired
	CarDetailsRepository carDetailsRepository;
	
	@Autowired
	CarUpdateDetailsRepository carUpdateDetailsRepository;
	
	@Override
	public boolean findUserRelatedCar(UserCarBidVO userCarBidVO) {
		UserCarBid userCarBid = userCarBidRepository.findByUserIdAndCarNameAndCarModel(userCarBidVO.getUserId(),userCarBidVO.getCar(),userCarBidVO.getCarModel());
		if(userCarBid != null) {
			return false;
		}
		return true;
	}

	@Override
	public Map<String,Object> saveUserBid(UserCarBidVO userCarBidVO) {
		Map<String,Object> responseMap = new HashMap<>();
		try {
			CarDetails carDetails = carDetailsRepository.findByCarCompanyAndCarModel(userCarBidVO.getCar(),userCarBidVO.getCarModel());
			List<UserCarBid> userCarBidRepo = carBidHistoryRepository.getCarHistoryBid(userCarBidVO.getCar(),userCarBidVO.getCarModel());
			if(carDetails != null && carDetails.getCarAvailability().equals("Y")) {
				if(userCarBidVO.getBidAmount() > carDetails.getBasePrice()) {
					responseMap=initialCreationBid(userCarBidRepo,userCarBidVO);
					for(UserCarBid userCarBidList : userCarBidRepo) {
						UserCarBid carCurrentHighestBid = userCarBidRepo.get(0);
						if(userCarBidVO.getBidAmount() > carCurrentHighestBid.getBidAmount()) {
							responseMap=createUpdateBid(userCarBidList,userCarBidVO);
						}else {
							responseMap.put("header", "unsuccessful insertion/updation as the bid is less than or equal to the highest bid");
						}
					}
				}else {
					responseMap.put("header", "Sorry, Bidding Price is less than the base price set...");
					responseMap.put("userCarBidVO", userCarBidVO);
				}
				
			}else {
				responseMap.put("header", "Sorry, Requested Car is not available for Auction...");
				responseMap.put("userCarBidVO", null);
			}
		}catch(Exception e) {
			logger.info("Exception in the saveUserBid "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}
	
	private Map<String,Object> createUpdateBid(UserCarBid userCarBidList,UserCarBidVO userCarBidVO){
		Map<String,Object> responseMap = new HashMap<>();
		if(userCarBidList.getUserId().equals(userCarBidVO.getUserId()) && !userCarBidVO.isCreateNewOne()) {
			carBidAmountRepository.updateUserCarBid(userCarBidVO);
			responseMap.put("header", "successful updation");
		}else if(userCarBidVO.isCreateNewOne()){
			if(findUserRelatedCar(userCarBidVO)) {
				UserCarBid userCarBid = new UserCarBid();
				userCarBid.setUserId(userCarBidVO.getUserId());
				userCarBid.setCarName(userCarBidVO.getCar());
				userCarBid.setCarModel(userCarBidVO.getCarModel());
				userCarBid.setBidAmount(userCarBidVO.getBidAmount());
				userCarBid.setEmailAddress(userCarBidVO.getEmailAddress());
				userCarBid.setPhoneNumber(userCarBidVO.getPhoneNumber());
				userCarBid.setAuctionStatus("IN-PROGRESS");
				userCarBidRepository.saveAndFlush(userCarBid);
				userCarBidVO.setCreateNewOne(false);
				responseMap.put("header", "successful insertion");
			}else {
				responseMap.put("header", "user has already bid for the car... Please go ahead and update your bid...");
			}
		}
		return responseMap;
	}
	
	private Map<String,Object> initialCreationBid(List<UserCarBid> userCarBidRepo,UserCarBidVO userCarBidVO){
		Map<String,Object> responseMap = new HashMap<>();
		if(userCarBidRepo.isEmpty()) {
			UserCarBid userCarBid = new UserCarBid();
			userCarBid.setUserId(userCarBidVO.getUserId());
			userCarBid.setCarName(userCarBidVO.getCar());
			userCarBid.setCarModel(userCarBidVO.getCarModel());
			userCarBid.setBidAmount(userCarBidVO.getBidAmount());
			userCarBid.setEmailAddress(userCarBidVO.getEmailAddress());
			userCarBid.setPhoneNumber(userCarBidVO.getPhoneNumber());
			userCarBid.setAuctionStatus("IN-PROGRESS");
			userCarBidRepository.saveAndFlush(userCarBid);
			responseMap.put("header", "successful insertion");
		}
		return responseMap;
	}
	@Override
	public Map<String,Object> getCarBiddingHistory(UserCarBidVO userCarBidVO) {
		Map<String,Object> responseMap = new HashMap<>();
		try {
			List<UserCarBid> userCarBidList =  carBidHistoryRepository.getCarHistoryBid(userCarBidVO.getCar(),userCarBidVO.getCarModel());
			if(!userCarBidList.isEmpty()) {
				responseMap.put("header", "Car's Bidding History");
				responseMap.put("carBidHistoryList", userCarBidList);
			}else {
				responseMap.put("header", "No Bidding history registered on this car...");
				responseMap.put("carBidHistoryList", userCarBidList);
			}
		}catch(Exception e) {
			logger.info("Exception in the getCarBiddingHistory "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}

	@Override
	public Map<String,Object> getWinningBid(UserCarBidVO userCarBidVO) {
		Map<String,Object> responseMap = new HashMap<>();
		int count = 0;
		try {
			List<UserCarBid> userCarBidList =carBidHistoryRepository.getCarHistoryBid(userCarBidVO.getCar(),userCarBidVO.getCarModel());
			if(!userCarBidList.isEmpty()) {
				UserCarBid userWinningBid = userCarBidList.get(0);
				for(UserCarBid userCarBid:userCarBidList) {
					if(userCarBid.getBidAmount().equals(userWinningBid.getBidAmount())) {
						count++;
					}
				}
				if(count>1) {
					responseMap.put("header", "There is a tie in the Bid among the user's, Please check the Bidding  History for further details....");
					responseMap.put("userBidDetails", null);
				}else {
					carUpdateDetailsRepository.updateCarAvailability(userCarBidVO.getCar(),userCarBidVO.getCarModel(),userCarBidVO.getCarAvailability());
					carBidAmountRepository.updateAuctionStatus(userCarBidVO);
					responseMap.put("header", "Winning Bid for a car");
					responseMap.put("userBidDetails", userWinningBid);
				}
			}else {
				responseMap.put("header", "No Bid has been made on the car...");
				responseMap.put("userBidDetails", null);
			}
		}catch(Exception e) {
			logger.info("Exception in the getWinningBid "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}

	@Override
	public Map<String, Object> getAvailableCarForBid(UserCarBidVO userCarBidVO) {
		Map<String,Object> responseMap = new HashMap<>();
		try {
			List<CarDetails> carDetailsList = carDetailsRepository.findByCarAvailability(userCarBidVO.getCarAvailability());
			if(!carDetailsList.isEmpty()) {
				responseMap.put("header", "Available car's for Bidding...");
				responseMap.put("carBidHistoryList", carDetailsList);
			}else {
				responseMap.put("header", "Sorry, currently no car's are available for bidding...");
				responseMap.put("carBidHistoryList", carDetailsList);
			}
		}catch(Exception e) {
			logger.info("Exception in the getWinningBid "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}

	@Override
	public Map<String, Object> changeStatusOfCar(UserCarBidVO userCarBidVO) {
		Map<String,Object> responseMap = new HashMap<>();
		try {
		String status = carUpdateDetailsRepository.updateCarAvailability(userCarBidVO.getCar(),userCarBidVO.getCarModel(),userCarBidVO.getCarAvailability());
		responseMap.put("header", "Successful Updated Status of a Car...");
		responseMap.put("Status", status);
		}catch(Exception e){
			logger.info("Exception in the getWinningBid "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}
}
