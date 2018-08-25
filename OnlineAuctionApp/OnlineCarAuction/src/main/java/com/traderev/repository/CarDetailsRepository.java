package com.traderev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traderev.model.CarDetails;

@Repository
public interface CarDetailsRepository extends JpaRepository<CarDetails, Long>{
	
	CarDetails findByCarCompany(String carCompany);

}
