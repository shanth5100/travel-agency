package com.travel.oAuth2.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travel.oAuth2.bean.Profile;

@Repository
public interface ProfileDAO extends JpaRepository<Profile, Long>{
	
	Profile findById(int frofileId);
//	find
}
