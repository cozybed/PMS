package com.system.service;

import com.common.PageBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface ProjectService {
    PageBean createQueryPage(Map param);

    String addProject(Object obj);

    String updateObject(Object obj);

    Object findObjectByid(String id);

    String deleteObject(String id);

    String approveProject(Map map);

    List getAllProjects();
}

