package com.traderev;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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
		List<UserCarBid> userCarBidTestList =userCarBidService.getCarBiddingHistory(userCarBidVO);
		assertEquals(userCarBidTestList.size(),1);
		
	}

}
