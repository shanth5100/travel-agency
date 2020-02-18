package com.travel.service.serviceDAO;

import java.util.List;

import org.springframework.stereotype.Service;

import com.travel.service.bean.ProfileSrevice;

@Service
public interface ServiceDAO {

	List<ProfileSrevice> profileList();
	ProfileSrevice profileById();	
	
}
