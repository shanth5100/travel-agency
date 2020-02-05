package com.travel.oAuth2.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.travel.oAuth2.bean.AppUser;

@Component
@Repository
public interface AppUserDao extends JpaRepository<AppUser, Long>{
	
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);

//	Optional<AppUser> findByUsername(String username);
	AppUser findByUsername(String username);
	Optional<AppUser> findByEmail(String email);
	Optional<AppUser> findByid(Long id);
//	Optional<AppUser> findByUsernameOrEmail(String username, String email);
//	Optional<AppUser> findByUsernameANdPassword(String username, String password);
}
