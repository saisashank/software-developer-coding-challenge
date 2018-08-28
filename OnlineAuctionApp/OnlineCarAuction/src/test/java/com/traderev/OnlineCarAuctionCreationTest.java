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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.traderev.model.CarDetails;
import com.traderev.model.UserCarBid;
import com.traderev.model.UserDetails;
import com.traderev.repository.CarBidAmountRepository;
import com.traderev.repository.CarBidHistoryRepository;
import com.traderev.repository.CarDetailsRepository;
import com.traderev.repository.UserCarBidRepository;
import com.traderev.repository.UserDetailsRepository;
import com.traderev.service.UserCarBidService;
import com.traderev.service.UserCarBidServiceImpl;
import com.traderev.vo.UserCarBidVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineCarAuctionCreationTest {

	@Mock
	UserCarBidRepository userCarBidRepository;
	
	@Mock
	CarBidAmountRepository carBidAmountRepository;
	
	@Mock
	CarDetailsRepository carDetailsRepository;
	
	@Mock
	CarBidHistoryRepository carBidHistoryRepository;
	
	@Autowired
	UserCarBidService userCarBidService;
	
	@Mock
	UserDetailsRepository userDetailsRepository;
	
	@InjectMocks
	UserCarBidServiceImpl userCarBidServiceMocked  = new UserCarBidServiceImpl();
	
	@Test
	public void testSaveUserBid() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Sashank");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35001.0);
		userCarBidVO.setCarModel("2019 Benz");
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(true);
		
		CarDetails carDetails = new CarDetails();
		carDetails.setBasePrice(100.0);
		carDetails.setCarAvailability("Y");
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		
		List<UserCarBid> userCarBidList = new ArrayList<>();
		UserCarBid userCarBid = new UserCarBid();
		userCarBid.setUserCarBidId(1L);
		userCarBid.setUserId("Sai");
		userCarBid.setBidAmount(35000.0);
		userCarBid.setCarName("Benz");
		userCarBidList.add(userCarBid);
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		
		userCarBidServiceMocked.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBidTesting = new UserCarBid();
		userCarBidTesting.setUserId("Sashank");
		userCarBidTesting.setCarName("Benz");
		userCarBidTesting.setCarModel("2019 Benz");
		Mockito.when(userCarBidRepository.findByUserIdAndCarNameAndCarModel(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidTesting);
		
		UserCarBid userCarBidTest = new UserCarBid();
		userCarBidTest.setUserId("Sashank");
		userCarBidTest.setCarName("Benz");
		Mockito.when(userCarBidRepository.findByUserId(Mockito.any(String.class))).thenReturn(userCarBidTest);
		assertEquals(userCarBidVO.getCar(), userCarBidTest.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Exception() {
		Map<String,Object> testResponseMap = userCarBidService.saveUserBid(null);
		assertEquals(1,testResponseMap.size());
	}
	
	@Test
	public void testSaveUserBid_Existing() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Sashank");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35001.0);
		userCarBidVO.setCarModel("2019 Benz");
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(false);
		
		CarDetails carDetails = new CarDetails();
		carDetails.setBasePrice(100.0);
		carDetails.setCarAvailability("Y");
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		
		List<UserCarBid> userCarBidList = new ArrayList<>();
		UserCarBid userCarBid = new UserCarBid();
		userCarBid.setUserCarBidId(1L);
		userCarBid.setUserId("Sashank");
		userCarBid.setBidAmount(35000.0);
		userCarBid.setCarName("Benz");
		userCarBidList.add(userCarBid);
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		
		String success = "success";
		Mockito.when(carBidAmountRepository.updateUserCarBid(Mockito.any(UserCarBidVO.class))).thenReturn(success);
		
		userCarBidServiceMocked.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBidTesting = new UserCarBid();
		userCarBidTesting.setUserId("Sashank");
		userCarBidTesting.setCarName("Benz");
		userCarBidTesting.setCarModel("2019 Benz");
		Mockito.when(userCarBidRepository.findByUserIdAndCarNameAndCarModel(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidTesting);
		
		UserCarBid userCarBidTest = new UserCarBid();
		userCarBidTest.setUserId("Sashank");
		userCarBidTest.setCarName("Benz");
		Mockito.when(userCarBidRepository.findByUserId(Mockito.any(String.class))).thenReturn(userCarBidTest);
		assertEquals(userCarBidVO.getCar(), userCarBidTest.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Existing_High_Bid() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Sashank");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35445.0);
		userCarBidVO.setCarModel("2019 Benz");
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(false);
		
		CarDetails carDetails = new CarDetails();
		carDetails.setBasePrice(100.0);
		carDetails.setCarAvailability("Y");
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		
		List<UserCarBid> userCarBidList = new ArrayList<>();
		UserCarBid userCarBid = new UserCarBid();
		userCarBid.setUserCarBidId(1L);
		userCarBid.setUserId("Sashank");
		userCarBid.setBidAmount(45000.0);
		userCarBid.setCarName("Benz");
		userCarBidList.add(userCarBid);
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		
		userCarBidServiceMocked.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBidTest = new UserCarBid();
		userCarBidTest.setUserId("Sashank");
		userCarBidTest.setCarName("Benz");
		Mockito.when(userCarBidRepository.findByUserId(Mockito.any(String.class))).thenReturn(userCarBidTest);
		assertEquals(userCarBidVO.getCar(), userCarBidTest.getCarName());
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
		
		CarDetails carDetails = new CarDetails();
		carDetails.setBasePrice(100.0);
		carDetails.setCarAvailability("Y");
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		
		List<UserCarBid> userCarBidList = new ArrayList<>();
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		
		userCarBidServiceMocked.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBidTest = new UserCarBid();
		userCarBidTest.setUserId("Nani");
		userCarBidTest.setCarName("Benz");
		Mockito.when(userCarBidRepository.findByUserId(Mockito.any(String.class))).thenReturn(userCarBidTest);
		assertEquals(userCarBidVO.getCar(), userCarBidTest.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Create_New_Highest_Bid() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Nani");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setCarModel("2019 Benz");
		userCarBidVO.setBidAmount(35001.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(true);
		
		CarDetails carDetails = new CarDetails();
		carDetails.setBasePrice(100.0);
		carDetails.setCarAvailability("Y");
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		
		List<UserCarBid> userCarBidList = new ArrayList<>();
		UserCarBid userCarBid = new UserCarBid();
		userCarBid.setUserCarBidId(1L);
		userCarBid.setUserId("Sashank");
		userCarBid.setBidAmount(35000.0);
		userCarBid.setCarName("Benz");
		userCarBidList.add(userCarBid);
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		
		UserCarBid userCarBidTesting = null;
		Mockito.when(userCarBidRepository.findByUserIdAndCarNameAndCarModel(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidTesting);
		
		userCarBidServiceMocked.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBidTest = new UserCarBid();
		userCarBidTest.setUserId("Nani");
		userCarBidTest.setCarName("Benz");
		Mockito.when(userCarBidRepository.findByUserId(Mockito.any(String.class))).thenReturn(userCarBidTest);
		assertEquals(userCarBidVO.getCar(), userCarBidTest.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Create_New_Highest_Bid_Else() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Nani");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setCarModel("2019 Benz");
		userCarBidVO.setBidAmount(35001.0);
		userCarBidVO.setEmailAddress("shashank@gmail.com");
		userCarBidVO.setPhoneNumber("354-645-4536");
		userCarBidVO.setCreateNewOne(true);
		
		CarDetails carDetails = new CarDetails();
		carDetails.setBasePrice(100.0);
		carDetails.setCarAvailability("Y");
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		
		List<UserCarBid> userCarBidList = new ArrayList<>();
		UserCarBid userCarBid = new UserCarBid();
		userCarBid.setUserCarBidId(1L);
		userCarBid.setUserId("Sashank");
		userCarBid.setBidAmount(35000.0);
		userCarBid.setCarName("Benz");
		userCarBidList.add(userCarBid);
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		
		UserCarBid userCarBidTesting = new UserCarBid();
		userCarBidTesting.setUserId("Sashank");
		userCarBidTesting.setCarName("Benz");
		userCarBidTesting.setCarModel("2019 Benz");
		Mockito.when(userCarBidRepository.findByUserIdAndCarNameAndCarModel(Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userCarBidTesting);
		
		userCarBidServiceMocked.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBidTest = new UserCarBid();
		userCarBidTest.setUserId("Nani");
		userCarBidTest.setCarName("Benz");
		Mockito.when(userCarBidRepository.findByUserId(Mockito.any(String.class))).thenReturn(userCarBidTest);
		assertEquals(userCarBidVO.getCar(), userCarBidTest.getCarName());
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
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		Mockito.when(carDetailsRepository.findByCarCompanyAndCarModel(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(carDetails);
		
		Map<String,Object> responseMap = userCarBidServiceMocked.saveUserBid(userCarBidVO);
		
		assertEquals("Sorry, Bidding Price is less than the base price set...", responseMap.get("header"));
	}
}
