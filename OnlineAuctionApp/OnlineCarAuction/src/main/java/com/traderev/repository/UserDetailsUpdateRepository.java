package com.traderev.repository;

import com.traderev.vo.UserDetailsVO;

public interface UserDetailsUpdateRepository {

	String updateUserDetails(UserDetailsVO userDetailsVO);
	
	String updateUserActiveStatus(UserDetailsVO userDetailsVO);
	
	String deleteUserDetails(Long userDetailsId);
}
