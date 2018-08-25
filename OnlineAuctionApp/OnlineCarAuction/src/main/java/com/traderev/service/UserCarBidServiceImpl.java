package com.traderev.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traderev.model.UserCarBid;
import com.traderev.repository.CarBidAmountRepository;
import com.traderev.repository.CarBidHistoryRepository;
import com.traderev.repository.UserCarBidRepository;
import com.traderev.vo.UserCarBidVO;

@Service
public class UserCarBidServiceImpl implements UserCarBidService {

	@Autowired
	UserCarBidRepository userCarBidRepository;
	
	@Autowired
	CarBidAmountRepository carBidAmountRepository;
	
	@Autowired
	CarBidHistoryRepository carBidHistoryRepository;
	
	@Override
	public UserCarBid findUserRelatedCar(UserCarBidVO userCarBidVO) {
		return userCarBidRepository.findByUserIdAndBidAmount(userCarBidVO.getUserId(),userCarBidVO.getBidAmount());
	}

	@Override
	public void saveUserBid(UserCarBidVO userCarBidVO) {
		List<UserCarBid> userCarBidRepo = userCarBidRepository.findAll();
		for(UserCarBid userCarBidList : userCarBidRepo) {
			if(userCarBidRepo != null && userCarBidList.getUserId().equals(userCarBidVO.getUserId()) && !userCarBidVO.isCreateNewOne()) {
				carBidAmountRepository.updateUserCarBid(userCarBidVO);
			}else if(userCarBidVO.isCreateNewOne()){
				UserCarBid userCarBid = new UserCarBid();
				userCarBid.setUserId(userCarBidVO.getUserId());
				userCarBid.setCarName(userCarBidVO.getCar());
				userCarBid.setBidAmount(userCarBidVO.getBidAmount());	
				userCarBidRepository.saveAndFlush(userCarBid);
				userCarBidVO.setCreateNewOne(false);
			}
		}
		
		if(userCarBidRepo.isEmpty()) {
			UserCarBid userCarBid = new UserCarBid();
			userCarBid.setUserId(userCarBidVO.getUserId());
			userCarBid.setCarName(userCarBidVO.getCar());
			userCarBid.setBidAmount(userCarBidVO.getBidAmount());	
			userCarBidRepository.saveAndFlush(userCarBid);
		}
	}

	@Override
	public List<UserCarBid> getCarBiddingHistory(UserCarBidVO userCarBidVO) {
		return  carBidHistoryRepository.getCarHistoryBid(userCarBidVO.getCar());
	}

	@Override
	public Map<String,Object> getWinningBid(UserCarBidVO userCarBidVO) {
		Map<String,Object> responseMap = new HashMap<>();
		int count = 0;
		List<UserCarBid> userCarBidList =carBidHistoryRepository.getCarHistoryBid(userCarBidVO.getCar());
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
			responseMap.put("header", "Winning Bid for a car");
			responseMap.put("userBidDetails", userWinningBid);
		}
		return responseMap;
	}
}
