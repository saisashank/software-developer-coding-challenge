package com.traderev;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.traderev.model.CarDetails;
import com.traderev.repository.CarDetailsRepository;
import com.traderev.repository.CarUpdateDetailsRepository;
import com.traderev.service.CarDetailsServiceImpl;
import com.traderev.vo.CarDetailsVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineCarAuctionUpdateDetailsTest {
	
	@Mock
	CarDetailsRepository carDetailsRepository;
	
	@Mock
	CarUpdateDetailsRepository carUpdateDetailsRepository;
	
	@InjectMocks
	CarDetailsServiceImpl carDetailsService = new CarDetailsServiceImpl();
	
	@Test
	public void testUpdateCarDetails() {
		String success = "Success";
		CarDetails carDetails = new CarDetails();
		carDetails.setCarCompany("Toyota");
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		Mockito.when(carUpdateDetailsRepository.updateCarDetails(Mockito.any(CarDetailsVO.class))).thenReturn(success);
		
		
		CarDetailsVO carDetailsVO = new CarDetailsVO();
		carDetailsVO.setCarModel("2019");
		Map<String,Object> testResponseMap = carDetailsService.updateCarDetails(carDetailsVO);
		assertEquals(1,testResponseMap.size());
	}
	
	@Test
	public void testUpdateCarDetails_No_Car_Available() {
		String success = "Success";
		CarDetails carDetails = null;
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		Mockito.when(carUpdateDetailsRepository.updateCarDetails(Mockito.any(CarDetailsVO.class))).thenReturn(success);
		
		
		CarDetailsVO carDetailsVO = new CarDetailsVO();
		carDetailsVO.setCarModel("2019");
		Map<String,Object> testResponseMap = carDetailsService.updateCarDetails(carDetailsVO);
		assertEquals("Cannot find the car trying to update...",testResponseMap.get("response"));
	}
	
	@Test
	public void testUpdateCarDetails_Exception() {
		String success = "Success";
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenThrow(Exception.class);
		Mockito.when(carUpdateDetailsRepository.updateCarDetails(Mockito.any(CarDetailsVO.class))).thenReturn(success);
		
		
		CarDetailsVO carDetailsVO = null;
		Map<String,Object> testResponseMap = carDetailsService.updateCarDetails(carDetailsVO);
		assertEquals(1,testResponseMap.size());
	}

	@Test
	public void testCreateCarForBid_Existing() {
		CarDetails carDetails = new CarDetails();
		carDetails.setCarCompany("Toyota");
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		
		CarDetailsVO carDetailsVO = new CarDetailsVO();
		carDetailsVO.setCarModel("2019");
		Map<String,Object> testResponseMap = carDetailsService.createCarForBid(carDetailsVO);
		assertEquals(2,testResponseMap.size());
	}
	
	@Test
	public void testCreateCarForBid() {
		CarDetails carDetails = null;
		CarDetails carDetailsNew = new CarDetails();
		carDetailsNew.setCarAvailability("Y");
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		Mockito.when(carDetailsRepository.saveAndFlush(Mockito.any(CarDetails.class))).thenReturn(carDetailsNew);
		
		
		CarDetailsVO carDetailsVO = new CarDetailsVO();
		carDetailsVO.setCarAvailability("Y");
		Map<String,Object> testResponseMap = carDetailsService.createCarForBid(carDetailsVO);
		assertEquals(1,testResponseMap.size());
	}
	
	@Test
	public void testCreateCarForBid_Exception() {
		String success = "Success";
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenThrow(Exception.class);
		Mockito.when(carUpdateDetailsRepository.updateCarDetails(Mockito.any(CarDetailsVO.class))).thenReturn(success);
		
		
		CarDetailsVO carDetailsVO = null;
		Map<String,Object> testResponseMap = carDetailsService.createCarForBid(carDetailsVO);
		assertEquals(1,testResponseMap.size());
	}
}
