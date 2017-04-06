package com.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.system.service.ProjectService;
import com.system.vo.Project;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.common.PageBean;
import com.common.Util;

@Component
public class ProjectServiceImpl extends BaseDao implements ProjectService {

    public PageBean createQueryPage(Map param) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select q.* from t_project q where 1=1 ");
        Project project = (Project) param.get("project");
//        if (project.getProname() != null) {
//            sql.append(" and q.username like '%" + param.get("username").toString() + "%' ");uckjnme
//
//        }
//        if (param.get("approveProject") != null) {
//            sql.append(" and q.approvalState = 0");
//
//        }
//        sql.append("   order by q.id desc");
//        String sql2 = Util.getPageSqlForMysql(
//                sql.toString(),
//                Integer.parseInt(param.get("pageSize").toString()),
//                Integer.parseInt(param.get("currentPage").toString()
//                )
//        );
        Integer offset = Integer.parseInt(param.get("pageSize").toString()) * (Integer.parseInt(param.get("currentPage").toString()) - 1);
        param.put("offset",offset);
        List list = getSqlMapClientTemplate().queryForList("getProjects", param);
//        List list = getSqlMapClientTemplate().queryForList("getAllUsers",
//                Util.getPageSqlForMysql(
//                        sql.toString(),
//                        Integer.parseInt(param.get("pageSize").toString()),
//                        Integer.parseInt(param.get("currentPage").toString()
//                        )
//                )
//
//        );

        return Util.getPageBean(
                Integer.parseInt(param.get("pageSize").toString()),
                Integer.parseInt(param.get("currentPage").toString()),
                list,
                param,
                (int) getSqlMapClientTemplate().queryForObject("numberOfProjects",param)
        );
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
        try {
            getSqlMapClientTemplate().update("updateProject", obj);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "notOk";
        }
    }

    @Override
    public Object findObjectByid(String id) {
        return getSqlMapClientTemplate().queryForObject("findProjectById", id);
    }

    @Override
    public String deleteObject(String id) {
        try {
            getSqlMapClientTemplate().delete("deleteProject", id);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "notOk";
        }
    }

    @Override
    public String approveProject(Map map) {
        try {
            getSqlMapClientTemplate().update("approveProject", map);
            return "ok";

        } catch (Exception e) {
            e.printStackTrace();
            return "notOk";
        }
    }

}
