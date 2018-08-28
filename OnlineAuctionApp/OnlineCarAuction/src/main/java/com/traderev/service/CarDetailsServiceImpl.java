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
			CarDetails carDetails = carDetailsRepository.findByCarCompanyAndCarModel(carDetailsVO.getCarCompany(),carDetailsVO.getCarModel());
			if(carDetails != null) {
				if(carDetailsVO.getCarStatus().equalsIgnoreCase("Y")) {
					String success = carUpdateDetailsRepository.updateCarDetails(carDetailsVO);
					responseMap.put("response",success);
				}else {
					carUpdateDetailsRepository.deleteCarDetails(carDetails.getCarDetailsId());
					responseMap.put("response", "Car Details deleted from the DB permanently...");
				}
			}else {
				responseMap.put("response","Cannot find the car trying to update...");
			}
		}catch(Exception e) {
			logger.info("Exception in the updateCarDetails "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}

	@Override
	public Map<String, Object> createCarForBid(CarDetailsVO carDetailsVO) {
		Map<String,Object> responseMap = new HashMap<>();
		try {
			CarDetails carDetails = carDetailsRepository.findByCarCompanyAndCarModel(carDetailsVO.getCarCompany(),carDetailsVO.getCarModel());
			if(carDetails != null) {
				responseMap.put("response","Requested car is already in the Auction... check for its availability status...");
				responseMap.put("header", carDetails);
			}else {
				CarDetails carDetailsNew = new CarDetails();
				carDetailsNew.setBasePrice(carDetailsVO.getBasePrice());
				carDetailsNew.setCarAvailability(carDetailsVO.getCarAvailability());
				carDetailsNew.setCarCompany(carDetailsVO.getCarCompany());
				carDetailsNew.setCarModel(carDetailsVO.getCarModel());
				carDetailsNew.setOdometerReading(carDetailsVO.getOdometerReading());
				carDetailsRepository.saveAndFlush(carDetailsNew);
				responseMap.put("response","successfully inserted new Car for Bid...");
			}
		}catch(Exception e) {
			logger.info("Exception in the createCarForBid "+e);
			responseMap.put("Exception occured is: ", e.getMessage());
		}
		return responseMap;
	}

}
