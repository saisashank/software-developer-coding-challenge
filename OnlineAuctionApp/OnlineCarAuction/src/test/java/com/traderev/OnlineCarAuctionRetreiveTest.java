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

import com.traderev.model.UserCarBid;
import com.traderev.repository.CarBidHistoryRepository;
import com.traderev.service.UserCarBidServiceImpl;
import com.traderev.vo.UserCarBidVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineCarAuctionRetreiveTest {
	
	@Mock
	CarBidHistoryRepository carBidHistoryRepository;
	
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
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Benz");
		Map<String,Object> userCarBidTestList =userCarBidService.getCarBiddingHistory(userCarBidVO);
		assertEquals(userCarBidTestList.size(),2);
		
	}
	
	@Test
	public void testGetCarBiddingHistory_No_List() {
		List<UserCarBid> userCarBidList = new ArrayList<>();
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Benz");
		Map<String,Object> userCarBidTestList =userCarBidService.getCarBiddingHistory(userCarBidVO);
		assertEquals(userCarBidTestList.size(),2);
		
	}
	
	@Test
	public void testGetWinningBid() {
		List<UserCarBid> userCarBidList = new ArrayList<>();
		UserCarBid userCarBid = new UserCarBid();
		userCarBid.setUserCarBidId(1L);
		userCarBid.setUserId("Sashank");
		userCarBid.setBidAmount(35000.0);
		userCarBid.setCarName("Benz");
		userCarBidList.add(userCarBid);
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Benz");
		Map<String,Object> testResponseMap = userCarBidService.getWinningBid(userCarBidVO);
		assertEquals(testResponseMap.get("header"),"Winning Bid for a car");
		
	}
	
	@Test
	public void testGetWinningBid_No_List() {
		List<UserCarBid> userCarBidList = new ArrayList<>();
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Benz");
		Map<String,Object> testResponseMap = userCarBidService.getWinningBid(userCarBidVO);
		assertEquals(testResponseMap.get("header"),"No Bid has been made on the car...");
		
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
		Mockito.when(carBidHistoryRepository.getCarHistoryBid(Mockito.any(String.class))).thenReturn(userCarBidList);
		
		UserCarBidVO userCarBidVO = new UserCarBidVO();
		userCarBidVO.setCar("Benz");
		Map<String,Object> testResponseMap = userCarBidService.getWinningBid(userCarBidVO);
		assertEquals(testResponseMap.get("header"),"There is a tie in the Bid among the user's, Please check the Bidding  History for further details....");
		
	}

}
