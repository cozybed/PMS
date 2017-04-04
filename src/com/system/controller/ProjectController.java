package com.system.controller;

import com.common.PageBean;
import com.sun.beans.editors.DoubleEditor;
import com.sun.beans.editors.FloatEditor;
import com.sun.beans.editors.LongEditor;
import com.system.service.ProjectService;
import com.system.service.ProtypeService;
import com.system.service.RoleService;
import com.system.vo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yousihan on 2017/3/28.
 */
@Component
@RequestMapping("/project")
@Scope("prototype")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    RoleService roleService;

    @RequestMapping(value = "projectList", method = {RequestMethod.GET, RequestMethod.POST})
    public String projectList(
            ModelMap modelMap, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") String pageSize,
            @RequestParam(value = "budget_min", required = false) Float budget_min,
            @RequestParam(value = "budget_max", required = false) Float budget_max,
            Project project
    ) {
        Map param = new HashMap();
        param.put("pageSize", pageSize);
        param.put("currentPage", currentPage);
        param.put("budget_min",budget_min);
        param.put("budget_max",budget_max);
        param.put("project",project);
        System.out.println(project.toString());
        System.out.println(budget_max);
        System.out.println(budget_min);
        PageBean pageBean = projectService.createQueryPage(param);
        System.out.println(pageBean.getList().size());
        modelMap.put("pageBean", pageBean);
        return "system/project/showProject";
    }

    @RequestMapping(value = "toAddProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAddProject(
            HttpServletRequest request
    ) {

        return "system/project/add";
    }
    @RequestMapping(value = "toUpdateProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String toUpdateProject(
            HttpServletRequest request,
            @RequestParam(value = "id")String id
    ) {
        Project project= (Project) projectService.findObjectByid(id);
        request.setAttribute("map",project);
        return "system/project/add";
    }
    @RequestMapping(value = "editProject", method = {RequestMethod.GET, RequestMethod.POST})
    public void editProject(
            HttpServletResponse response,
            Project project
    ) throws IOException {
        System.out.println("editproject");

        System.out.println(project.getArea() + project.getId() + project.getProname());
        if (project.getId() == 0) {
            response.getWriter().print(projectService.addProject(project));
//            projectService.addProject(project);
        } else if (project.getId() != 0) {
            response.getWriter().print(projectService.updateObject(project));
        }
    }

    @RequestMapping(value = "delProject", method = {RequestMethod.GET, RequestMethod.POST})
    public void delProtype(
            ModelMap modelMap,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "delCheckBox", defaultValue = "") String[] delCheckBox)
            throws Exception {
        String status = "ok";
        if (delCheckBox.length > 0) {
            for (String id : delCheckBox) {
                if (projectService.deleteObject("" + id)=="notOk"){
                status = "notOk";}
            }
        }
        PrintWriter writer = response.getWriter();
        writer.write(status);
        writer.flush();

    }

}
