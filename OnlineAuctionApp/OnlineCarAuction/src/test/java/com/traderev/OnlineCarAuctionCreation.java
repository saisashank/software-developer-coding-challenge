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
public class OnlineCarAuctionCreation {

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
	public void testSaveUserBid_Create_New() {
		UserCarBidVO userCarBidVO= new UserCarBidVO();
		userCarBidVO.setUserId("Sai");
		userCarBidVO.setCar("Nissan");
		userCarBidVO.setBidAmount(35000.0);
		userCarBidVO.setCreateNewOne(true);
		
		userCarBidService.saveUserBid(userCarBidVO);
		
		UserCarBid userCarBid = userCarBidRepository.findByUserId("Sai");
		assertEquals(userCarBidVO.getCar(), userCarBid.getCarName());
	}
}
