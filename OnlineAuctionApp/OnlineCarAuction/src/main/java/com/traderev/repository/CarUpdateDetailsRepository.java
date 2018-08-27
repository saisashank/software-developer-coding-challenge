package com.traderev.repository;

import com.traderev.vo.CarDetailsVO;

public interface CarUpdateDetailsRepository {

	String updateCarAvailability(String carName,String carModel,String carAvailability);
	
	String updateCarDetails(CarDetailsVO carDetailsVO);
}
