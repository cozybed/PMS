package com.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.deploy.net.HttpResponse;
import com.system.vo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.PageBean;
import com.common.Util;
import com.system.service.ProtypeService;
import com.system.vo.Protype;

/**
 * ==================================
 * 控制器 - 系统管理 - 菜单管理
 * ----------------------------------
 * <p>
 * ==================================
 */

@Component
@RequestMapping("/protype")
@Scope("prototype")
public class ProtypeController {

    @Autowired
    private ProtypeService protypeService;

    /**
     * 项目类型列表查询
     */
    @RequestMapping(value = "protypeList", method = {RequestMethod.GET, RequestMethod.POST})
    public String protypeList(
            ModelMap modelMap,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "s_type_name", defaultValue = "") String s_type_name,
            @RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") String pageSize) {
        Map param = new HashMap();
        param.put("pageSize", pageSize);
        param.put("currentPage", currentPage);
        param.put("s_type_name", s_type_name);
        PageBean pageBean = protypeService.createQueryPage(param);
        modelMap.put("pageBean", pageBean);
        return "system/protype/showProtype";
    }

    /**
     * 项目类型编辑
     */
    @RequestMapping(value = "editProtype", method = {RequestMethod.GET, RequestMethod.POST})
    public void editProtype(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Protype protype) throws IOException {
        System.out.println("add pro");
//        System.out.println(protype.toString());
        if (protype.getIsfather() == 1) {
            protype.setPid(0);
        }
        if (protype.getId() == 0) {
            System.out.println("add obj");
            response.getWriter().print(protypeService.addObject(protype));
        } else {
            System.out.println("update obj");
            response.getWriter().print(protypeService.updateObject(protype));
        }
    }

    /**
     * 添加项目类型页面
     */
    @RequestMapping(value = "toAddProtype", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAddProtype(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Protype protype, String sta) throws IOException {
        List parentProtypes = protypeService.parentProtypes();
        request.setAttribute("parentProtypes", parentProtypes);
        return "system/protype/editProtype";
    }

    /**
     * 编辑项目类型页面
     */

    @RequestMapping(value = "toUpdateProtype", method = {RequestMethod.GET, RequestMethod.POST})
    public String toUpdateProtype(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") String id) throws IOException {
        System.out.println("manually output:--------" + id);
//        Map map = (Map) (
//                (HashMap<String, Object>) protypeService.findObjectById(protype.getId() + "")).get(0);
        Protype newPro = (Protype) protypeService.findObjectById(id);
//        request.setAttribute("map", protype);
        request.setAttribute("map", newPro);
        List parentProtypes = protypeService.parentProtypes();
        request.setAttribute("parentProtypes", parentProtypes);
//        request.setAttribute("sta", sta);
//        return "1";
        return "system/protype/editProtype";
    }

    /**
     * 删除项目类型
     */
    @RequestMapping(value = "delProtype", method = {RequestMethod.GET, RequestMethod.POST})
    public void delProtype(
            ModelMap modelMap,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "delCheckBox", defaultValue = "") String[] delCheckBox)
            throws Exception {
        String status = "ok";
        if (delCheckBox.length > 0) {
            for (String id : delCheckBox) {
                if (protypeService.deleteObject("" + id) == "notOk") {
                    status = "notOk";
                }


            }
        }
        PrintWriter pw = response.getWriter();
        pw.write(status);
        pw.flush();

    }

}
