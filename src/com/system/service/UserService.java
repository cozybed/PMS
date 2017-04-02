package com.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.PageBean;
@Component//@Service
public interface UserService {
	
	PageBean createQueryPage(Map param);
	
	String addObject(Object obj);
	
	Object findObjectById(int id);
	
	String updateObject(Object obj);
	
	String deleteObject(String id);
	
	List getRoleForSelect();

	String searchYhm(String login_name);
	
	
	
}