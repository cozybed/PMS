package com.common;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;

public class BaseDao extends SqlMapClientDaoSupport{
	 
	@Resource(name = "SqlServerClient")
    private SqlMapClient sqlMapClient;


	@PostConstruct
    public void initSqlMapClient(){
        super.setSqlMapClient(sqlMapClient);
	} 

}
	
	
