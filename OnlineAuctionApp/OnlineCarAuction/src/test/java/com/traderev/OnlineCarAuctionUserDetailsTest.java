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
import com.traderev.repository.UserDetailsUpdateRepository;
import com.traderev.service.UserDetailsServiceImpl;
import com.traderev.vo.UserDetailsVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OnlineCarAuctionUserDetailsTest {
	
	@InjectMocks
	UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl();
	
	@Mock
	UserDetailsRepository userDetailsRepository;
	
	@Mock
	UserDetailsUpdateRepository userDetailsUpdateRepository;
	
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
	
	@Test
	public void testUpdateUserDetails() {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserId("Sashank");
		userDetailsVO.setEmailAddress("shashi@gmail.com");
		
		String message = "Successfully updated User Details";
		Mockito.when(userDetailsUpdateRepository.updateUserDetails(Mockito.any(UserDetailsVO.class))).thenReturn(message);
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.updateUserDetails(userDetailsVO);
		assertEquals(1,responseMapTest.size());
	}
	
	@Test
	public void testUpdateUserDetails_Else() {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserId("Sashak");
		userDetailsVO.setEmailAddress("sh@gmail.com");
		
		String message = "Successfully updated User Details";
		Mockito.when(userDetailsUpdateRepository.updateUserDetails(Mockito.any(UserDetailsVO.class))).thenReturn(message);
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.updateUserDetails(userDetailsVO);
		assertEquals(1,responseMapTest.size());
	}
	
	@Test
	public void testUpdateUserDetails_Exception() {
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenThrow(Exception.class);
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserId("Sashank");
		userDetailsVO.setEmailAddress("shashi@gmail.com");
		
		String message = "Successfully updated User Details";
		Mockito.when(userDetailsUpdateRepository.updateUserDetails(Mockito.any(UserDetailsVO.class))).thenReturn(message);
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.updateUserDetails(userDetailsVO);
		assertEquals(1,responseMapTest.size());
	}
	
	@Test
	public void testUpdateUserActiveStatus() {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserStatus("Y");
		
		String message = "success";
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		Mockito.when(userDetailsUpdateRepository.updateUserActiveStatus(Mockito.any(UserDetailsVO.class))).thenReturn(message);
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.updateUserActiveStatus(userDetailsVO);
		assertEquals(1,responseMapTest.size());
	}
	
	@Test
	public void testUpdateUserActiveStatus_delete() {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserStatus("N");
		
		String message = "success";
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		Mockito.when(userDetailsUpdateRepository.deleteUserDetails(Mockito.any(Long.class))).thenReturn(message);
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.updateUserActiveStatus(userDetailsVO);
		assertEquals(1,responseMapTest.size());
	}
	
	@Test
	public void testUpdateUserActiveStatus_No_Records() {
		
		UserDetails userDetails = null;
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserStatus("N");
		
		String message = "success";
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenReturn(userDetails);
		Mockito.when(userDetailsUpdateRepository.deleteUserDetails(Mockito.any(Long.class))).thenReturn(message);
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.updateUserActiveStatus(userDetailsVO);
		assertEquals(1,responseMapTest.size());
	}
	
	@Test
	public void testUpdateUserActiveStatus_Exception() {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setUserStatus("N");
		
		String message = "success";
		
		Mockito.when(userDetailsRepository.findByUserIdAndEmailAddress(Mockito.any(String.class),Mockito.any(String.class))).thenThrow(Exception.class);
		Mockito.when(userDetailsUpdateRepository.deleteUserDetails(Mockito.any(Long.class))).thenReturn(message);
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.updateUserActiveStatus(userDetailsVO);
		assertEquals(1,responseMapTest.size());
	}
	
	@Test
	public void testGetAllActiveUsers() {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		List<UserDetails> userDetailsList =new ArrayList<>();
		userDetailsList.add(userDetails);
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setIsActive("Y");
		
		Mockito.when(userDetailsRepository.findByIsActive(Mockito.any(String.class))).thenReturn(userDetailsList);
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.getAllActiveUsers(userDetailsVO);
		assertEquals(2,responseMapTest.size());
	}
	
	@Test
	public void testGetAllActiveUsers_Else() {
		
		UserDetails userDetails = new UserDetails();
		userDetails.setEmailAddress("shashi@gmail.com");
		userDetails.setFirstName("sai");
		userDetails.setIsActive("Y");
		userDetails.setLastName("ravi");
		userDetails.setPhoneNumber("134");
		userDetails.setUserId("Sashank");
		
		List<UserDetails> userDetailsList =new ArrayList<>();
		userDetailsList.add(userDetails);
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setIsActive("N");
		
		Mockito.when(userDetailsRepository.findByIsActive(Mockito.any(String.class))).thenReturn(userDetailsList);
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.getAllActiveUsers(userDetailsVO);
		assertEquals(2,responseMapTest.size());
	}
	
	@Test
	public void testGetAllActiveUsers_Empty_List() {
		
		List<UserDetails> userDetailsList =new ArrayList<>();
		
		UserDetailsVO userDetailsVO = new UserDetailsVO();
		userDetailsVO.setIsActive("N");
		
		Mockito.when(userDetailsRepository.findByIsActive(Mockito.any(String.class))).thenReturn(userDetailsList);
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.getAllActiveUsers(userDetailsVO);
		assertEquals(2,responseMapTest.size());
	}
	
	@Test
	public void testGetAllActiveUsers_Exception() {
		
		Mockito.when(userDetailsRepository.findByIsActive(Mockito.any(String.class))).thenThrow(Exception.class);
		
		
		Map<String,Object> responseMapTest = userDetailsServiceImpl.getAllActiveUsers(null);
		assertEquals(1,responseMapTest.size());
	}

}
