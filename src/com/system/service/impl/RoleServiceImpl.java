package com.system.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.common.PageBean;
import com.common.Util;
import com.system.vo.Menu;

@Component
// @Repository
public class RoleServiceImpl extends BaseDao implements com.system.service.RoleService {

    @Override
    public PageBean createQueryPage(Map param) {
        // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer();
        sql.append(" select q.* from t_role q where 1=1 ");
        if (!param.get("s_role_name").toString().equals("")) {
            sql.append(" and q.rolename like '%" + param.get("s_role_name").toString() + "%' ");
        }
        sql.append("   order by q.id asc");
        List list = getSqlMapClientTemplate().queryForList("getAllUsers",
                Util.getPageSqlForMysql(
                        sql.toString(),
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
                list.size()
        );
    }


    @Override
    public PageBean createQueryPageForProcess(Map param) {
        // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer();
        sql.append(" select q.* from t_process q  where 1=1 ");
        if (!param.get("s_role_name").toString().equals("")) {
            sql.append(" and q.pname like '%" + param.get("s_role_name").toString() + "%' ");
        }
        sql.append("   order by q.id asc");
        List list = getSqlMapClientTemplate().queryForList("getAllUsers", Util.getPageSqlForMysql(sql.toString(), Integer.parseInt(param.get("pageSize").toString()), Integer.parseInt(param.get("currentPage").toString())));
        return Util.getPageBean(Integer.parseInt(param.get("pageSize").toString()),
                Integer.parseInt(param.get("currentPage").toString()), list, param,
                getSqlMapClientTemplate().queryForList("getAllUsers", sql.toString()).size());
    }

    @Override
    public PageBean createQueryPageForNotProject(Map param) {
        // TODO Auto-generated method stub
        StringBuffer sql = new StringBuffer();
        sql.append(" select q.* from t_project q  where 1=1 ");
        sql.append("   order by q.id asc");
        List list = getSqlMapClientTemplate().queryForList("getAllUsers", Util.getPageSqlForMysql(sql.toString(), Integer.parseInt(param.get("pageSize").toString()), Integer.parseInt(param.get("currentPage").toString())));
        return Util.getPageBean(Integer.parseInt(param.get("pageSize").toString()),
                Integer.parseInt(param.get("currentPage").toString()), list, param,
                getSqlMapClientTemplate().queryForList("getAllUsers", sql.toString()).size());
    }

    @Override
    public PageBean createQueryPageForProject(Map param) {
        // TODO Auto-generated method stub
        return null;
    }


    @Transactional
    public String addObject(Object obj) {
        // TODO Auto-generated method stub
        try {
            getSqlMapClientTemplate().insert("addRole", obj);
            return "ok";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "notOk";
        }
    }

    public Object findObjectById(String id) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForObject("findRoleById", id);
    }

    @Transactional
    public String updateObject(Object obj) {
        // TODO Auto-generated method stub
        try {
            getSqlMapClientTemplate().update("updateRole", obj);
            return "ok";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "notOk";
        }
    }

    @Transactional
    public String deleteObject(String id) {
        // TODO Auto-generated method stub
        try {
            getSqlMapClientTemplate().delete("deleteRole", id);
            return "ok";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "notOk";
        }
    }

    public String imCap(String cap) {
        // TODO Auto-generated method stub
        try {
            getSqlMapClientTemplate().delete("imCap", cap);
            return "ok";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "notOk";
        }
    }

    public List imAllCap(String cap) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("imAllCap", cap);
    }

    public void imAllCapBatch(List<String> cap) throws SQLException {
        // TODO Auto-generated method stub
        Connection conn = null;
        Statement pstmt = null;

        try {
            conn = getSqlMapClient().getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            pstmt = conn.createStatement();
            for (String a : cap) {
                pstmt.addBatch(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            conn.setAutoCommit(false);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        try {
            pstmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        try {
            pstmt.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void imCapJ(String cap) {
        // TODO Auto-generated method stub
        try {
            Connection conn = null;
            Statement pstmt = null;
            conn = getSqlMapClient().getDataSource().getConnection();
            pstmt = conn.createStatement();
            pstmt.executeUpdate(cap);
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List findListforMenu(String role_id) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("findListforMenu", role_id);
    }

    public List findListforMenuAll() {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("findListforMenuAll");
    }

    public String editRoleMenu(String[] menu_id, String role_id) {
        // TODO Auto-generated method stub
        List sql = new ArrayList();
        sql.add("delete from system_role_menu where role_id='" + role_id + "'");
        for (String a : menu_id) {
            sql.add("insert into system_role_menu values('" + role_id + "','" + a + "')");
        }
        try {
            this.imAllCapBatch(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据角色信息查询菜单列表
     */
    public List getRoleMenuList(String roleId) {

        List list = getSqlMapClientTemplate().queryForList("getRoleMenuList", roleId);

        return null;

    }

    /**
     * 编辑权限菜单
     */
    public void editRoleMenu(String ids, String ROLE_ID) throws Exception {
        getSqlMapClientTemplate().delete("deleteRoleMenuByRoleId", ROLE_ID);
        if ("".equals(ids)) {
            return;
        }
        String[] menu_ids = ids.split("#");
        for (String MENU_ID : menu_ids) {
            Map roleMap = new HashMap();
            roleMap.put("ROLE_ID", ROLE_ID);
            roleMap.put("MENU_ID", MENU_ID);
            getSqlMapClientTemplate().insert("inserRoleMenus", roleMap);
        }
    }

}
