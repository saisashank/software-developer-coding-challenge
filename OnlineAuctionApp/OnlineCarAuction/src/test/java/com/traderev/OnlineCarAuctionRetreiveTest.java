package com.traderev;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.traderev.model.CarDetails;
import com.traderev.model.UserCarBid;
import com.traderev.repository.CarBidAmountRepository;
import com.traderev.repository.CarBidHistoryRepository;
import com.traderev.repository.CarDetailsRepository;
import com.traderev.repository.CarUpdateDetailsRepository;
import com.traderev.service.UserCarBidServiceImpl;
import com.traderev.vo.UserCarBidVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineCarAuctionRetreiveTest {
	
	@Mock
	CarBidHistoryRepository carBidHistoryRepository;
	
	@Mock
	CarUpdateDetailsRepository carUpdateDetailsRepository;
	
	@Mock
	CarDetailsRepository carDetailsRepository;
	
	@Mock
	CarBidAmountRepository carBidAmountRepository;
	
	@InjectMocks
	UserCarBidServiceImpl userCarBidService = new UserCarBidServiceImpl();
	
	@Test
	public void testGetCarBiddingHistory() {
		List<UserCarBid> userCarBidList = new ArrayList<>();
		UserCarBid userCarBid = new UserCarBid();
		userCarBid.setUserCarBidId(1L);
		userCarBid.setUserId("Sashank");
		userCarBid.setBidAmount(35000.0);
		userCarBid.setCarName("Benz");
		userCarBidList.add(userCarBid);
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Benz");
		Map<String,Object> userCarBidTestList =userCarBidService.getCarBiddingHistory(userCarBidVO);
		assertEquals(2,userCarBidTestList.size());
	}
	
	@Test
	public void testGetCarBiddingHistory_No_List() {
		List<UserCarBid> userCarBidList = new ArrayList<>();
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Benz");
		Map<String,Object> userCarBidTestList =userCarBidService.getCarBiddingHistory(userCarBidVO);
		assertEquals(2,userCarBidTestList.size());
		
	}
	
	@Test
	public void testGetCarBiddingHistory_Exception() {
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenThrow(Exception.class);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar(null);
		Map<String,Object> userCarBidTestList =userCarBidService.getCarBiddingHistory(userCarBidVO);
		assertEquals(1,userCarBidTestList.size());
		
	}
	
	@Test
	public void testGetWinningBid() {
		List<UserCarBid> userCarBidList = new ArrayList<>();
		String message = "Success";
		UserCarBid userCarBid = new UserCarBid();
		userCarBid.setUserCarBidId(1L);
		userCarBid.setUserId("Sashank");
		userCarBid.setBidAmount(35000.0);
		userCarBid.setCarName("Nissan");
		userCarBid.setEmailAddress("shashank@gmail.com");
		userCarBid.setPhoneNumber("354-645-4536");
		userCarBidList.add(userCarBid);
		
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		Mockito.when(carUpdateDetailsRepository.updateCarAvailability(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class))).thenReturn(message);
		Mockito.when(carBidAmountRepository.updateAuctionStatus(Mockito.any(UserCarBidVO.class))).thenReturn(message);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Nissan");
		userCarBidVO.setUserId("Sashank");
		userCarBidVO.setBidAmount(35000.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		Map<String,Object> testResponseMap = userCarBidService.getWinningBid(userCarBidVO);
		assertEquals("Winning Bid for a car",testResponseMap.get("header"));
		
	}
	
	@Test
	public void testGetWinningBid_No_List() {
		List<UserCarBid> userCarBidList = new ArrayList<>();
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Benz");
		Map<String,Object> testResponseMap = userCarBidService.getWinningBid(userCarBidVO);
		assertEquals("No Bid has been made on the car...",testResponseMap.get("header"));
		
	}
	
	@Test
	public void testGetWinningBid_Exception() {
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenThrow(Exception.class);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar(null);
		Map<String,Object> testResponseMap = userCarBidService.getWinningBid(userCarBidVO);
		assertEquals(1,testResponseMap.size());
		
	}
	
	@Test
	public void testGetWinningBid_Same_Bid() {
		List<UserCarBid> userCarBidList = new ArrayList<>();
		UserCarBid userCarBid1 = new UserCarBid();
		UserCarBid userCarBid2 = new UserCarBid();
		userCarBid1.setUserCarBidId(1L);
		userCarBid1.setUserId("Sashank");
		userCarBid1.setBidAmount(35000.0);
		userCarBid1.setCarName("Benz");
		userCarBid2.setUserCarBidId(2L);
		userCarBid2.setUserId("Sai");
		userCarBid2.setBidAmount(35000.0);
		userCarBid2.setCarName("Benz");
		userCarBidList.add(userCarBid1);
		userCarBidList.add(userCarBid2);
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Benz");
		Map<String,Object> testResponseMap = userCarBidService.getWinningBid(userCarBidVO);
		assertEquals("There is a tie in the Bid among the user's, Please check the Bidding  History for further details....",testResponseMap.get("header"));
		
	}
	
	@Test
	public void testGetAvailableCarForBid() {
		List<CarDetails> carDetailsList = new ArrayList<>();
		CarDetails carDetails = new CarDetails();
		carDetails.setBasePrice(4599.0);
		carDetails.setCarAvailability("Y");
		carDetails.setCarCompany("Benz");
		carDetails.setCarDetailsId(1L);
		carDetails.setCarModel("2019");
		carDetailsList.add(carDetails);
		Mockito.when(carDetailsRepository.findByCarAvailability(Mockito.any(String.class))).thenReturn(carDetailsList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCarAvailability("Y");
		Map<String,Object> testResponseMap = userCarBidService.getAvailableCarForBid(userCarBidVO);
		assertEquals("Available car's for Bidding...",testResponseMap.get("header"));
		
	}
	
	@Test
	public void testGetAvailableCarForBid_Else() {
		List<CarDetails> carDetailsList = new ArrayList<>();
		CarDetails carDetails = new CarDetails();
		carDetails.setBasePrice(4599.0);
		carDetails.setCarAvailability("Y");
		carDetails.setCarCompany("Benz");
		carDetails.setCarDetailsId(1L);
		carDetails.setCarModel("2019");
		carDetailsList.add(carDetails);
		Mockito.when(carDetailsRepository.findByCarAvailability(Mockito.any(String.class))).thenReturn(carDetailsList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCarAvailability("N");
		Map<String,Object> testResponseMap = userCarBidService.getAvailableCarForBid(userCarBidVO);
		assertEquals("Unavailable car's for Bidding...",testResponseMap.get("header"));
		
	}
	
	@Test
	public void testGetAvailableCarForBid_No_List() {
		List<CarDetails> carDetailsList = new ArrayList<>();
		Mockito.when(carDetailsRepository.findByCarAvailability(Mockito.any(String.class))).thenReturn(carDetailsList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCarAvailability("Y");
		Map<String,Object> testResponseMap = userCarBidService.getAvailableCarForBid(userCarBidVO);
		assertEquals("Sorry, currently no car's are available for bidding...",testResponseMap.get("header"));
		
	}
	
	@Test
	public void testGetAvailableCarForBid_Exception() {
		Mockito.when(carDetailsRepository.findByCarAvailability(Mockito.any(String.class))).thenThrow(Exception.class);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCarAvailability(null);
		Map<String,Object> testResponseMap = userCarBidService.getAvailableCarForBid(userCarBidVO);
		assertEquals(1,testResponseMap.size());
		
	}
	
	@Test
	public void testChangeStatusOfCar() {
		String message = "success";
		Mockito.when(carUpdateDetailsRepository.updateCarAvailability(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class))).thenReturn(message);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Toyota");
		Map<String,Object> testResponseMap = userCarBidService.changeStatusOfCar(userCarBidVO);
		assertEquals(2,testResponseMap.size());
		
	}
	
	@Test
	public void testChangeStatusOfCar_Exception() {
		Mockito.when(carUpdateDetailsRepository.updateCarAvailability(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class))).thenThrow(Exception.class);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar(null);
		Map<String,Object> testResponseMap = userCarBidService.changeStatusOfCar(userCarBidVO);
		assertEquals(1,testResponseMap.size());
		
	}

}
