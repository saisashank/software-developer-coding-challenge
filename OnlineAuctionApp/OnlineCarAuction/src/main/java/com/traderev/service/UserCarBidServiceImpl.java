package com.traderev.service;

import java.util.List;

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
}
