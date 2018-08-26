package com.traderev;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.traderev.model.CarDetails;
import com.traderev.model.UserCarBid;
import com.traderev.repository.CarBidAmountRepository;
import com.traderev.repository.CarBidHistoryRepository;
import com.traderev.repository.CarDetailsRepository;
import com.traderev.repository.UserCarBidRepository;
import com.traderev.service.UserCarBidService;
import com.traderev.service.UserCarBidServiceImpl;
import com.traderev.vo.UserCarBidVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineCarAuctionCreationTest {

	@Autowired
	UserCarBidRepository userCarBidRepository;
	
	@Autowired
	CarBidAmountRepository carBidAmountRepository;
	
	@Mock
	CarDetailsRepository carDetailsRepository;
	
	@Mock
	CarBidHistoryRepository carBidHistoryRepository;
	
	@Autowired
	UserCarBidService userCarBidService;
	
	@InjectMocks
	UserCarBidService userCarBidServiceMocked  = new UserCarBidServiceImpl();
	
	@Test
	public void testSaveUserBid() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Sashank");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35000.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		
		userCarBidService.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Sashank");
		assertEquals(userCarBidVO.getCar(), userCarBid.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Exception() {
		Map<String,Object> testResponseMap = userCarBidService.saveUserBid(null);
		assertEquals(testResponseMap.size(), 1);
	}
	
	@Test
	public void testSaveUserBid_Existing() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Sashank");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35000.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		
		userCarBidService.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Sashank");
		assertEquals(userCarBidVO.getCar(), userCarBid.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Existing_High_Bid() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Sashank");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35445.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(false);
		
		userCarBidService.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Sashank");
		assertEquals(userCarBidVO.getCar(), userCarBid.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Create_New() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Nani");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35000.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(true);
		
		userCarBidService.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Nani");
		assertEquals(userCarBidVO.getCar(), userCarBid.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Create_New_Highest_Bid() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Nani");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35001.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(true);
		
		userCarBidService.saveUserBid(userCarBidVO);
		userCarBidService.findUserRelatedCar(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Nani");
		assertEquals(userCarBidVO.getCar(), userCarBid.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Create_New_No_Car_For_User() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Ravi");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35002.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(true);
		
		userCarBidService.saveUserBid(userCarBidVO);
		userCarBidService.findUserRelatedCar(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Ravi");
		assertEquals(userCarBidVO.getCar(), userCarBid.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Create_New_No_Car_For_User_Else() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Ravi");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(350454.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(true);
		
		userCarBidService.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Ravi");
		assertEquals(userCarBidVO.getCar(), userCarBid.getCarName());
	}
	

	@Test
	public void testSaveUserBid_Car_Unavilable() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Ravi");
		userCarBidVO.setCar("Toyota");
		userCarBidVO.setBidAmount(350454.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(true);
		
		userCarBidService.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Ravi");
		assertEquals(null, userCarBid);
	}
	
	@Test
	public void testSaveUserBid_Car_Bid_Price_Less() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Ravi");
		userCarBidVO.setCar("Toyota");
		userCarBidVO.setBidAmount(35.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(true);
		
		CarDetails carDetails = new CarDetails();
		carDetails.setBasePrice(100.0);
		carDetails.setCarAvailability("Y");
		Mockito.when(carDetailsRepository.findByCarCompany(Mockito.any(String.class))).thenReturn(carDetails);
		
		Map<String,Object> responseMap = userCarBidServiceMocked.saveUserBid(userCarBidVO);
		
		assertEquals("Sorry, Bidding Price is less than the base price set...", responseMap.get("header"));
	}
}
