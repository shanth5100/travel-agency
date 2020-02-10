package com.travel.agency.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.agency.bean.AppUser;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long>{
	
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);

	
	
	@Override
	Optional<AppUser> findById(Long id);
	Optional<AppUser> findByUsername(String username);
	Optional<AppUser> findByUsernameOrEmail(String username, String email);
	
	// Custom method for login request
	AppUser findByUsernameAndPassword(String username, String email);
	AppUser findUserByUsername(String username);
	
}