package com.traderev.repository;

import com.traderev.vo.CarDetailsVO;

public interface CarUpdateDetailsRepository {

	String updateCarAvailability(String carName);
	
	String updateCarDetails(CarDetailsVO carDetailsVO);
}
