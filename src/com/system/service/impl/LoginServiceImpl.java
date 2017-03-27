package com.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.common.BaseDao;
import com.common.PageBean;
import com.common.Util;
import com.system.vo.User;

@Component
public class LoginServiceImpl extends BaseDao implements com.system.service.LoginService {
    public User fnUser(String username) {
        //System.out.println("Call fnUser");
        Map map = new HashMap();
        map.put("USERNAME", username);
        List list = getSqlMapClientTemplate().queryForList("fnUser", map);
        if (list.size() == 0 || list == null) {
            return null;
        } else {
            return (User) list.get(0);
        }
    }

    public User login(String username, String userpass) {
        //System.out.println("Call login");
        Map map = new HashMap();
        map.put("USERNAME", username);
        map.put("USERPASS", userpass);
        List list = getSqlMapClientTemplate().queryForList("login", map);
        if (list.size() == 0 || list == null) {
            return null;
        } else {
            return (User) list.get(0);
        }
    }

    public List get_menu(String user_id) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("get_menu", "");
    }

    public List get_list_menu_my(String user_id) {
        // TODO Auto-generated method stub
        return getSqlMapClientTemplate().queryForList("get_menu_my", user_id);
    }

}
