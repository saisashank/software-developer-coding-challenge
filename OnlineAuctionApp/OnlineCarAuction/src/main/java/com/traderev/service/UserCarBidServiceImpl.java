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
	public boolean findUserRelatedCar(UserCarBidVO userCarBidVO) {
		UserCarBid userCarBid = userCarBidRepository.findByUserIdAndCarName(userCarBidVO.getUserId(),userCarBidVO.getCar());
		if(userCarBid != null) {
			return false;
		}
		return true;
	}

	@Override
	public Map<String,Object> saveUserBid(UserCarBidVO userCarBidVO) {
		Map<String,Object> responseMap = new HashMap<>();
		List<UserCarBid> userCarBidRepo = getCarBiddingHistory(userCarBidVO);
		for(UserCarBid userCarBidList : userCarBidRepo) {
			UserCarBid carCurrentHighestBid = userCarBidRepo.get(0);
			if(userCarBidVO.getBidAmount() > carCurrentHighestBid.getBidAmount()) {
				if(userCarBidRepo != null && userCarBidList.getUserId().equals(userCarBidVO.getUserId()) && !userCarBidVO.isCreateNewOne()) {
					carBidAmountRepository.updateUserCarBid(userCarBidVO);
					responseMap.put("header", "successful updation");
				}else if(userCarBidVO.isCreateNewOne()){
					if(findUserRelatedCar(userCarBidVO)) {
						UserCarBid userCarBid = new UserCarBid();
						userCarBid.setUserId(userCarBidVO.getUserId());
						userCarBid.setCarName(userCarBidVO.getCar());
						userCarBid.setBidAmount(userCarBidVO.getBidAmount());
						userCarBidRepository.saveAndFlush(userCarBid);
						userCarBidVO.setCreateNewOne(false);
						responseMap.put("header", "successful insertion");
					}else {
						responseMap.put("header", "user has already bid for the car... Please go ahead and update your bid...");
					}
				}
			}else {
				responseMap.put("header", "unsuccessful insertion/updation as the bid is less than or equal to the highest bid");
			}
		}
		
		if(userCarBidRepo.isEmpty()) {
			UserCarBid userCarBid = new UserCarBid();
			userCarBid.setUserId(userCarBidVO.getUserId());
			userCarBid.setCarName(userCarBidVO.getCar());
			userCarBid.setBidAmount(userCarBidVO.getBidAmount());	
			userCarBidRepository.saveAndFlush(userCarBid);
			responseMap.put("header", "successful insertion");
		}
		return responseMap;
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
