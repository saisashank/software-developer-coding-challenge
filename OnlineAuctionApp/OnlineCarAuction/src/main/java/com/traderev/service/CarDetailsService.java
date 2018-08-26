package com.traderev.service;

import java.util.Map;

import com.traderev.vo.CarDetailsVO;

public interface CarDetailsService {

	Map<String,Object> updateCarDetails(CarDetailsVO carDetailsVO);
	
	Map<String,Object> createCarForBid(CarDetailsVO carDetailsVO);
}
