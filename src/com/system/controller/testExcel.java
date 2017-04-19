package com.system.controller;

import com.google.gson.Gson;
import com.system.service.ProjectService;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by yousihan on 2017/4/6.
 */
@Component
public class testExcel {
    @Autowired
    ProjectService projectService;
    public static void main(String[] args) {
        HashMap h=new HashMap();
        h.put("1",2);
        h.put('2',2);
        Gson gson = new Gson();
        String[] str = {"abc", "123"};
        System.out.println(gson.toJson(h));
    }


}
