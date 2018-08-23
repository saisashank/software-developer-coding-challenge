package com.traderev.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.traderev.model.UserCarBid;
import com.traderev.repository.CarBidAmountRepository;
import com.traderev.repository.UserCarBidRepository;
import com.traderev.vo.UserCarBidVO;

@Service
public class UserCarBidServiceImpl implements UserCarBidService {

	@Autowired
	UserCarBidRepository userCarBidRepository;
	
	@Autowired
	CarBidAmountRepository carBidAmountRepository;
	
	@Override
	public UserCarBid findUserRelatedCar(UserCarBidVO userCarBidVO) {
		return userCarBidRepository.findByUserIdAndCarBidAmount(userCarBidVO.getUserId(),userCarBidVO.getCar());
	}

	@Override
	public void saveUserBid(UserCarBidVO userCarBidVO) {
		try {
			UserCarBid userCarBidRepo = userCarBidRepository.findByUserId(userCarBidVO.getUserId());
			ObjectMapper objectMapper = new ObjectMapper();
			String carBidAmount = objectMapper.writeValueAsString(userCarBidVO.getCarBidAmountVO());
			if(userCarBidRepo != null && userCarBidVO.getUserId().equals(userCarBidVO.getUserId())) {
				carBidAmountRepository.updateUserCarBid(userCarBidVO);
			}else if(userCarBidVO.isCreateNewOne()){
				UserCarBid userCarBid = new UserCarBid();
				userCarBid.setUserId(userCarBidVO.getUserId());
				userCarBid.setCarBidAmount(carBidAmount);	
				userCarBidRepository.saveAndFlush(userCarBid);
			}
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}
