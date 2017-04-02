package com.system.service.impl;

import java.util.List;
import java.util.Map;

import com.system.service.ProjectService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.common.PageBean;
import com.common.Util;

@Component
public class ProjectServiceImpl extends BaseDao implements ProjectService {
    public PageBean createQueryPage(Map param) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select q.*,w.rolename from t_user q left join t_role w on q.role_id=w.id where 1=1 ");
        if (!param.get("s_user_name").toString().equals("")) {
            sql.append(" and q.username like '%" + param.get("s_user_name").toString() + "%' ");
        }
        sql.append("  order by w.id ");
        System.out.println(sql);
        List list = getSqlMapClientTemplate().queryForList(
                "getAllUsers",
                Util.getPageSqlForMysql(sql.toString(),
                        Integer.parseInt(param.get("pageSize").toString()),
                        Integer.parseInt(param.get("currentPage").toString()
                        )
                )
        );
        return Util.getPageBean(
                Integer.parseInt(param.get("pageSize").toString()),
                Integer.parseInt(param.get("currentPage").toString()),
                list,
                param,
                list.size());
    }

    @Transactional
    public String addProject(Object obj) {
        try {
            getSqlMapClientTemplate().insert("addProject", obj);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "notOk";
        }
    }

    @Override
    public String updateObject(Object obj) {
        return null;
    }

}
