package com.traderev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traderev.model.CarDetails;

@Repository
public interface CarDetailsRepository extends JpaRepository<CarDetails, Long>{
	
	CarDetails findByCarCompany(String carCompany);
	
	List<CarDetails> findByCarAvailability(String carAvailability);
	
	CarDetails findByCarCompanyAndCarModel(String carCompany, String carModel);

}
