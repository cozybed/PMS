package com.system.controller;

import com.system.service.ProjectService;
import com.system.service.ProtypeService;
import com.system.vo.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by yousihan on 2017/3/28.
 */
@Component
@RequestMapping("/project")
@Scope("prototype")
public class ProjectController {
    @Autowired
    private ProtypeService protypeService;
    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "toAddProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAddProject(
            HttpServletRequest request
    ) {
        List parentProtypes = protypeService.parentProtypes();
        request.setAttribute("parentProtypes", parentProtypes);
        return "system/project/add";
    }

    @RequestMapping(value = "editProject", method = {RequestMethod.GET, RequestMethod.POST})
    public void editProject(
            HttpServletResponse response,
            Project project
    ) throws IOException {
        System.out.println("editproject");
        response.getWriter().print("notOk");
        System.out.println(project.getArea()+project.getId()+project.getProname());
        if (project.getId()==0){
//            response.getWriter().print(projectService.addProject(project));
            projectService.addProject(project);
        } else if (project.getId()!=0){
            response.getWriter().print(projectService.updateObject(project));
        }
    }

}
