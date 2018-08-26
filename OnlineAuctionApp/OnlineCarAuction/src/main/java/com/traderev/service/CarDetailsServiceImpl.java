package com.traderev.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.traderev.model.CarDetails;
import com.traderev.repository.CarDetailsRepository;
import com.traderev.repository.CarUpdateDetailsRepository;
import com.traderev.vo.CarDetailsVO;

@Service
public class CarDetailsServiceImpl implements CarDetailsService{
	
	private final Logger logger = LoggerFactory.getLogger(CarDetailsServiceImpl.class);
	
	@Autowired
	CarUpdateDetailsRepository carUpdateDetailsRepository;
	
	@Autowired
	CarDetailsRepository carDetailsRepository;

	@Override
	public Map<String, Object> updateCarDetails(CarDetailsVO carDetailsVO) {
		Map<String,Object> responseMap = new HashMap<>();
		try {
			CarDetails carDetails = carDetailsRepository.findByCarCompany(carDetailsVO.getCarCompany());
			if(carDetails != null) {
				String success = carUpdateDetailsRepository.updateCarDetails(carDetailsVO);
				responseMap.put("response",success);
			}else {
				responseMap.put("response","Cannot find the car trying to update...");
			}
		}catch(Exception e) {
			logger.info("Exception in the updateCarDetails "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}

}
