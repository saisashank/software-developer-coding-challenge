package com.traderev.service;

import java.util.Map;

import com.traderev.vo.UserDetailsVO;

public interface UserDetailsService {
	
	Map<String,Object> createUserDetails(UserDetailsVO userDetailsVO);
	
}
