package com.traderev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.traderev.model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
	
	UserDetails findByUserIdAndEmailAddress(String userId, String emailAddress);
	
	List<UserDetails> findByIsActive(String isActive);

}
