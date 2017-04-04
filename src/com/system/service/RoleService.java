package com.system.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.common.PageBean;

@Component//@Service
public interface RoleService {

    PageBean createQueryPage(Map param);

    PageBean createQueryPageForProject(Map param);

    PageBean createQueryPageForNotProject(Map param);

    PageBean createQueryPageForProcess(Map param);

    String addObject(Object obj);

    Object findObjectById(String id);

    String updateObject(Object obj);

    String deleteObject(String id);

    String imCap(String cap);

    public void imCapJ(String cap);

    public List imAllCap(String cap);

    List findListforMenu(String role_id);

    List findListforMenuAll();

    String editRoleMenu(String[] menu_id, String role_id);

    public void imAllCapBatch(List<String> cap) throws SQLException;

    List getRoleMenuList(String roleId);

    void editRoleMenu(String ids, String role_id) throws Exception;
    List getAllProcess();


}