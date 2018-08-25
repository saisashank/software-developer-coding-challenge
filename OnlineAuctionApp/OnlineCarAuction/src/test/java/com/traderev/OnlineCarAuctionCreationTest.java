package com.traderev;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.traderev.model.UserCarBid;
import com.traderev.repository.CarBidAmountRepository;
import com.traderev.repository.UserCarBidRepository;
import com.traderev.service.UserCarBidService;
import com.traderev.vo.UserCarBidVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineCarAuctionCreationTest {

	@Autowired
	UserCarBidRepository userCarBidRepository;
	
	@Autowired
	CarBidAmountRepository carBidAmountRepository;
	
	@Autowired
	UserCarBidService userCarBidService;
	
	@Test
	public void testSaveUserBid() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Sashank");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35000.0);
		
		userCarBidService.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Sashank");
		assertEquals(userCarBidVO.getCar(), userCarBid.getCarName());
	}
	
	@Test
	public void testSaveUserBid_Existing() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Sashank");
		userCarBidVO.setCar("Benz");
		userCarBidVO.setBidAmount(35000.0);
		
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
		userCarBidVO.setCreateNewOne(true);
		
		userCarBidService.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Ravi");
		assertEquals(null, userCarBid);
	}
}
