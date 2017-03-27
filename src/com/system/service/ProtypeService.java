package com.system.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.PageBean;

@Component
public interface ProtypeService {
PageBean createQueryPage(Map param);
	
	List parentProtypes();
	
	String addObject(Object obj);
	
	Object findObjectById(String string);
	
	String updateObject(Object obj);
	
	String deleteObject(String id);
}
