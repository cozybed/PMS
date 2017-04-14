package com.system.controller;

import com.common.PageBean;
import com.system.service.LoginService;
import com.system.service.ProtypeService;
import com.system.service.RoleService;
import com.system.vo.Project;
import com.system.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.system.service.ProtypeService;
import com.system.service.RoleService;
import com.system.service.impl.LoginServiceImpl;
import com.system.vo.Protype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.system.service.LoginService;
import com.system.vo.User;

@Component
@RequestMapping("/chart")
@Scope("prototype")
public class chartController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ProtypeService protypeService;


    @RequestMapping(value = "getChart1", method = {RequestMethod.GET, RequestMethod.POST})
    public String projectList(
            ModelMap modelMap, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") String pageSize,
            @RequestParam(value = "budget_min", required = false) Float budget_min,
            @RequestParam(value = "budget_max", required = false) Float budget_max,
            Project project
    ) {
        return "charts/area";
    }
    /*
     * 用户登录
     */


}

