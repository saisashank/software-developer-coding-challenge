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

import com.traderev.model.UserDetails;
import com.traderev.repository.UserDetailsRepository;
import com.traderev.service.UserDetailsServiceImpl;
import com.traderev.vo.UserDetailsVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineCarAuctionUserDetailsTest {
	
	@InjectMocks
	UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl();
	
	@Mock
	UserDetailsRepository userDetailsRepository;
	
	@Test
	public void testCreateUserDetails() {
		
		UserDetails userDetails = null;
		
		List<UserDetails> userDetailsList = new ArrayList<>();
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		Mockito.when(userDetailsRepository.findAll()).thenReturn(userDetailsList);
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserId("Sashank");
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.createUserDetails(userDetailsVO);
		assertEquals("successfully created User",responseMapTest.get("response"));
	}
	
	@Test
	public void testCreateUserDetails_Existing() {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		List<UserDetails> userDetailsList = new ArrayList<>();
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		Mockito.when(userDetailsRepository.findAll()).thenReturn(userDetailsList);
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserId("Sashank");
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.createUserDetails(userDetailsVO);
		assertEquals("User trying to create already exists...",responseMapTest.get("response"));
	}
	
	@Test
	public void testCreateUserDetails_No_Record() {
		
		UserDetails userDetails = null;
		
		List<UserDetails> userDetailsList = new ArrayList<>();
		UserDetails userDetailsNew = new UserDetails();
		userDetailsNew.setEmailAddress("sai@gmail.com");
		userDetailsNew.setFirstName("sai");
		userDetailsNew.setIsActive("Y");
		userDetailsNew.setLastName("ravi");
		userDetailsNew.setPhoneNumber("134");
		userDetailsNew.setUserId("sai");
		userDetailsList.add(userDetailsNew);
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		Mockito.when(userDetailsRepository.findAll()).thenReturn(userDetailsList);
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserId("Sashank");
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.createUserDetails(userDetailsVO);
		assertEquals("successfully created User",responseMapTest.get("response"));
	}
	
	@Test
	public void testCreateUserDetails_No_Record_User_Exist() {
		
		UserDetails userDetails = null;
		
		List<UserDetails> userDetailsList = new ArrayList<>();
		UserDetails userDetailsNew = new UserDetails();
		userDetailsNew.setEmailAddress("sai@gmail.com");
		userDetailsNew.setFirstName("sai");
		userDetailsNew.setIsActive("Y");
		userDetailsNew.setLastName("ravi");
		userDetailsNew.setPhoneNumber("134");
		userDetailsNew.setUserId("Sashank");
		userDetailsList.add(userDetailsNew);
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		Mockito.when(userDetailsRepository.findAll()).thenReturn(userDetailsList);
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserId("Sashank");
		userDetailsVO.setEmailAddress("sai@gmail.com");
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.createUserDetails(userDetailsVO);
		assertEquals("Please check the UserId/Email given.... they already exist...",responseMapTest.get("response"));
	}
	
	@Test
	public void testCreateUserDetails_Exception() {
		
		List<UserDetails> userDetailsList = new ArrayList<>();
		UserDetails userDetailsNew = new UserDetails();
		userDetailsNew.setEmailAddress("sai@gmail.com");
		userDetailsNew.setFirstName("sai");
		userDetailsNew.setIsActive("Y");
		userDetailsNew.setLastName("ravi");
		userDetailsNew.setPhoneNumber("134");
		userDetailsNew.setUserId("Sashank");
		userDetailsList.add(userDetailsNew);
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenThrow(Exception.class);
		Mockito.when(userDetailsRepository.findAll()).thenReturn(userDetailsList);
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.createUserDetails(userDetailsVO);
		assertEquals(1,responseMapTest.size());
	}

}
