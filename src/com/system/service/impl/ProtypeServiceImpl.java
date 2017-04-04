package com.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.common.PageBean;
import com.common.Util;
import com.system.service.ProtypeService;

@Component
public class ProtypeServiceImpl extends BaseDao implements ProtypeService {

    /**
     * 项目类型列表
     */
    public PageBean createQueryPage(Map param) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select q.*,w.typename pname from t_protype as q left join t_protype w on q.pid=w.id WHERE 1=1 ");
        if (!param.get("s_type_name").toString().equals("")) {
            sql.append(" AND q.typename LIKE '%" + param.get("s_type_name").toString() + "%' ");
        }
        sql.append(" ORDER BY q.pid ASC");
        List list = getSqlMapClientTemplate().queryForList("getAllProtypes", Util.getPageSqlForMysql(sql.toString(), Integer.parseInt(param.get("pageSize").toString()), Integer.parseInt(param.get("currentPage").toString())));
        return Util.getPageBean(
                Integer.parseInt(param.get("pageSize").toString()),
                Integer.parseInt(param.get("currentPage").toString()),
                list,
                param,
                (int)getSqlMapClientTemplate().queryForObject("numberOfEntries","select count(*) from t_protype")
        );
    }

    /**
     * 查询一级项目类型
     */
    public List parentProtypes() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT T.* FROM t_protype T WHERE isfather = 1 ");
        List list = getSqlMapClientTemplate().queryForList("parentProtypes", sql.toString());
        return list;
    }

    @Override
    public List getAllTypes() {
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT * FROM t_protype");
        List list = getSqlMapClientTemplate().queryForList("parentProtypes", sql.toString());
        return list;
    }

    /**
     * 添加项目类型
     */
    @Transactional
    public String addObject(Object obj) {
        try {
            getSqlMapClientTemplate().insert("addProtype", obj);
            System.out.println("add obj ok");
            return "ok";

        } catch (Exception e) {
            e.printStackTrace();
            return "notOk";
        }
    }

    /**
     * 根据ID查询项目类型
     */
    public Object findObjectById(String id) {
        return getSqlMapClientTemplate().queryForObject("findProtypeById", id);
    }

    /**
     * 修改项目类型
     */
    @Transactional
    public String updateObject(Object obj) {
        try {
            getSqlMapClientTemplate().update("updateProtype", obj);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "notOk";
        }
    }

    /**
     * 删除项目类型
     */
    @Transactional
    public String deleteObject(String id) {
        try {
            getSqlMapClientTemplate().delete("deleteProtype", id);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "notOk";
        }
    }

}
