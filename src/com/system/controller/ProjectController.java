package com.system.controller;

import com.common.ExportExcel;
import com.common.PageBean;
import com.sun.beans.editors.DoubleEditor;
import com.sun.beans.editors.FloatEditor;
import com.sun.beans.editors.LongEditor;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.system.service.ProjectService;
import com.system.service.ProtypeService;
import com.system.service.RoleService;
import com.system.vo.MyProcess;
import com.system.vo.Project;
import com.system.vo.Protype;
import com.system.vo.User;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Number;
import org.h2.util.New;
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

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import jxl.write.*;
import jxl.format.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    ProtypeService protypeService;

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
        if (project.getProname() == null) {
            project.setApprovalState("100");
        }
        param.put("pageSize", pageSize);
        param.put("currentPage", currentPage);
        param.put("budget_min", budget_min);
        param.put("budget_max", budget_max);
        param.put("project", project);
        System.out.println(project.toString());
        System.out.println(budget_max);
        System.out.println(budget_min);
        PageBean pageBean = projectService.createQueryPage(param);
        System.out.println(pageBean.getList().size());
        modelMap.put("pageBean", pageBean);
        return "system/project/showProject";
    }

    /**
     * 状态0未审批
     * 状态1审批通过
     * 状态2未通过
     *
     * @param modelMap
     * @param request
     * @param response
     * @param currentPage
     * @param pageSize
     * @param budget_min
     * @param budget_max
     * @param project
     * @return
     */
    @RequestMapping(value = "approvalProjectList", method = {RequestMethod.GET, RequestMethod.POST})
    public String approvalProjectList(
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
        param.put("budget_min", budget_min);
        param.put("budget_max", budget_max);
        param.put("project", project);
        project.setApprovalState("0");
        System.out.println(project.toString());
        System.out.println(budget_max);
        System.out.println(budget_min);
        PageBean pageBean = projectService.createQueryPage(param);
        System.out.println(pageBean.getList().size());
        modelMap.put("pageBean", pageBean);
        return "system/project/projectToApprovelList";
    }

    @RequestMapping(value = "notProjectList", method = {RequestMethod.GET, RequestMethod.POST})
    public String projectStock(
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
        param.put("budget_min", budget_min);
        param.put("budget_max", budget_max);
        param.put("project", project);
        project.setApprovalState("100");
        param.put("getStock", "1");
        System.out.println(project.toString());
        System.out.println(budget_max);
        System.out.println(budget_min);
        PageBean pageBean = projectService.createQueryPage(param);
        System.out.println(pageBean.getList().size());
        modelMap.put("pageBean", pageBean);
        return "system/project/projectStock";
    }

    @RequestMapping(value = "myProjectList", method = {RequestMethod.GET, RequestMethod.POST})
    public String myProjectList(
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
        param.put("budget_min", budget_min);
        param.put("budget_max", budget_max);
        param.put("project", project);
        User currentUser = (User) request.getSession().getAttribute("user");
        project.setUsername(currentUser.getLoginname());
        System.out.println(project.toString());
        System.out.println(budget_max);
        System.out.println(budget_min);
        PageBean pageBean = projectService.createQueryPage(param);
        System.out.println(pageBean.getList().size());
        modelMap.put("pageBean", pageBean);
        return "system/project/myProject";
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
            @RequestParam(value = "id") String id
    ) {
        Project project = (Project) projectService.findObjectByid(id);
        request.setAttribute("map", project);
        return "system/project/add";
    }

    @RequestMapping(value = "toApproveProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String approveProject(
            HttpServletRequest request,
            @RequestParam(value = "id") String id
    ) {
        Project project = (Project) projectService.findObjectByid(id);
        request.setAttribute("map", project);
        return "system/project/approveProject";
    }

    @RequestMapping(value = "approveProject", method = {RequestMethod.GET, RequestMethod.POST})
    public void approveProject(
            HttpServletResponse response,
            @RequestParam(value = "approvalState") String approvalState,
            @RequestParam(value = "approvalDiscription") String approvalDiscription,
            @RequestParam(value = "id") String id

    ) throws IOException {

        Map map = new HashMap();
        map.put("approvalState", approvalState);
        map.put("approvalDiscription", approvalDiscription);
        map.put("id", id);
        response.getWriter().print(projectService.approveProject(map));

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
        if (request.getSession().getAttribute("user") == null) {
            status = "notOk";
            PrintWriter writer = response.getWriter();
            writer.write(status);
            writer.flush();
            return;
        }
        if (delCheckBox.length > 0) {
            for (String id : delCheckBox) {
                if (projectService.deleteObject("" + id) == "notOk") {
                    status = "notOk";
                }
            }
        }
        PrintWriter writer = response.getWriter();
        writer.write(status);
        writer.flush();

    }

    @RequestMapping(value = "importProjects", method = {RequestMethod.GET, RequestMethod.POST})
    public String importProjects(
            ModelMap modelMap, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") String pageSize,
            @RequestParam(value = "budget_min", required = false) Float budget_min,
            @RequestParam(value = "budget_max", required = false) Float budget_max,
            @RequestParam(value = "file", required = false) MultipartFile file,
            Project project
    ) throws Exception {

        //文件名
        String name = file.getOriginalFilename();
        String filePath = request.getSession().getServletContext().getRealPath("/") + "upload" + "\\"
                + file.getOriginalFilename();
        long size = file.getSize();
        HashMap<String, Integer> protypesSet = new <String, Integer>HashMap();
        HashMap<String, Integer> processSet = new <String, Integer>HashMap();
        try {
            InputStream in = file.getInputStream();
            Workbook workbook = Workbook.getWorkbook(in);
            Sheet sheets = workbook.getSheet(0);
            // 获取Sheet表中所包含的总列数
            int sheetColumns = sheets.getColumns();
            // 获取Sheet表中所包含的总行数
            int sheetRows = sheets.getRows();
            // 获取指这下单元格的对象引用
            List<HashMap> process = (List<HashMap>) roleService.getAllProcess();
            List<HashMap> protypes = (List<HashMap>) protypeService.getAllTypes();

            for (HashMap hashMap : protypes) {
                System.out.println(hashMap.get("typename"));
                System.out.println(hashMap.get("id"));
                protypesSet.put((String) hashMap.get("typename"), (Integer) hashMap.get("id"));

            }

            for (HashMap hashMap : process) {
                processSet.put((String) hashMap.get("pname"), (Integer) hashMap.get("id"));
            }
            processSet.put("无进展阶段", 0);
            for (int i = 1; i < sheetRows; i++) {
                Project projectAdd = new Project();

                projectAdd.setProname(sheets.getCell(1, i).getContents() + "batth");
                projectAdd.setStartTime(sheets.getCell(2, i).getContents());
                projectAdd.setEndTime(sheets.getCell(3, i).getContents());
                projectAdd.setBudget(sheets.getCell(4, i).getContents());
                String cellValue = sheets.getCell(5, i).getContents();
                Protype protype = new Protype();
                Integer maxId;
                if (!protypesSet.containsKey(cellValue)) {
                    if (cellValue == "无一级类型") {
                        projectAdd.setType1(0);
                        projectAdd.setType2(0);
                        break;
                    }
                    protype.setTypename(cellValue);
                    protype.setIsfather(1);
                    protype.setPid(0);
                    protype.setRemark("");
                    protypeService.addObject(protype);
                    maxId = protypeService.findMaxId();
                    protypesSet.put(cellValue, maxId);
                    projectAdd.setType1(maxId);

                    cellValue = sheets.getCell(6, i).getContents();
                    protype.setTypename(cellValue);
                    protype.setIsfather(2);

                    protype.setPid(maxId);
                    protype.setRemark("");
                    protypeService.addObject(protype);
                    maxId = protypeService.findMaxId();
                    protypesSet.put(cellValue, maxId);
                    projectAdd.setType2(maxId);

                } else {


                    projectAdd.setType1(protypesSet.get(cellValue));
                    cellValue = sheets.getCell(6, i).getContents();
                    if (!protypesSet.containsKey(cellValue)) {
                        if (cellValue == "无二级类型") {
                            projectAdd.setType2(0);
                            break;
                        }
                        protype.setTypename(cellValue);
                        protype.setIsfather(2);
                        protype.setPid(protypesSet.get(sheets.getCell(5, i).getContents()));
                        protype.setRemark("");
                        protypeService.addObject(protype);
                        maxId = protypeService.findMaxId();
                        protypesSet.put(cellValue, maxId);
                        projectAdd.setType2(maxId);
                    } else {
                        cellValue = sheets.getCell(6, i).getContents();
                        projectAdd.setType2(protypesSet.get(cellValue));
                    }
                }
                projectAdd.setScale(sheets.getCell(7, i).getContents());
                projectAdd.setUsername(sheets.getCell(8, i).getContents());
                projectAdd.setContactName(sheets.getCell(9, i).getContents());
                projectAdd.setContactPhone(sheets.getCell(10, i).getContents());
                projectAdd.setProvince(sheets.getCell(11, i).getContents());
                projectAdd.setCity(sheets.getCell(12, i).getContents());
                projectAdd.setArea(sheets.getCell(13, i).getContents());
                projectAdd.setAddress(sheets.getCell(14, i).getContents());
                cellValue = sheets.getCell(15, i).getContents();
                projectAdd.setProcessId(String.valueOf(processSet.get(cellValue)));
                projectAdd.setSourceDepartment(sheets.getCell(16, i).getContents());
                projectAdd.setProcessDiscription(sheets.getCell(17, i).getContents());
                switch (sheets.getCell(18, i).getContents()) {
                    case "未审批":
                        projectAdd.setApprovalState("0");
                        break;
                    case "审批通过 ":
                        projectAdd.setApprovalState("1");
                        break;
                    case "审批未通过":
                        projectAdd.setApprovalState("2");
                        break;
                }
                projectAdd.setApprovalDiscription(sheets.getCell(19, i).getContents());
                projectService.addProject(projectAdd);

                // 注意在读取时 i和j的位置。i代表行 j代表列。且列在前 行在后
//                    project.setType1();


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        Map param = new HashMap();
        if (project.getProname() == null) {
            project.setApprovalState("100");
        }
        param.put("pageSize", pageSize);
        param.put("currentPage", currentPage);
        param.put("budget_min", budget_min);
        param.put("budget_max", budget_max);
        param.put("project", project);
        System.out.println(project.toString());
        System.out.println(budget_max);
        System.out.println(budget_min);
        PageBean pageBean = projectService.createQueryPage(param);
        System.out.println(pageBean.getList().size());
        modelMap.put("pageBean", pageBean);

        List<HashMap> process = (List<HashMap>) roleService.getAllProcess();
        List<HashMap> protypes = (List<HashMap>) protypeService.getAllTypes();

        request.getSession().setAttribute("process", roleService.getAllProcess());
        request.getSession().setAttribute("protypes", protypeService.getAllTypes());
        request.getSession().setAttribute("protypesMap", protypesSet);
        request.getSession().setAttribute("processMap", processSet);


        return "system/project/showProject";
    }

    @RequestMapping(value = "exportProjects", method = {RequestMethod.GET, RequestMethod.POST})
    public void exportProjects(
            ModelMap modelMap,
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "delCheckBox", defaultValue = "") String[] delCheckBox)
            throws Exception {
        try {
            System.out.println("123");
            System.out.println(request.getSession().getServletContext().getRealPath("/upload"));
            String path = request.getSession().getServletContext().getRealPath("/upload") + "/temp.xls";
            //创建一个Excel文件
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            WritableWorkbook book = Workbook.createWorkbook(os);

            //创建Excel中的页面，设置页面名称，页面号由0开始，页面会按页面号从小到大的顺序在Excel中从左向右排列
            WritableSheet sheet1 = book.createSheet("第一页", 0);
            //设置要合并单元格的下标


            WritableCellFormat cellFormat = new WritableCellFormat();
            //设置水平居中
            cellFormat.setAlignment(jxl.format.Alignment.CENTRE);
            //设置垂直居中
            cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            //设置自动换行
            cellFormat.setWrap(true);
            cellFormat.setFont(new WritableFont(WritableFont.createFont("楷体_GB2312"), 12, WritableFont.NO_BOLD, false,
                    UnderlineStyle.NO_UNDERLINE, Colour.BLACK));

            //创建一个单元格，并按行列坐标进行指定的内容写入 ，最后加入显示的样式
            sheet1.addCell(new Label(0, 0, "id", cellFormat));
            sheet1.addCell(new Label(1, 0, "项目名称", cellFormat));
            sheet1.addCell(new Label(2, 0, "开始时间", cellFormat));
            sheet1.addCell(new Label(3, 0, "结束时间", cellFormat));
            sheet1.addCell(new Label(4, 0, "预算", cellFormat));
            sheet1.addCell(new Label(5, 0, "一级类型", cellFormat));
            sheet1.addCell(new Label(6, 0, "二级类型", cellFormat));
            sheet1.addCell(new Label(7, 0, "规模", cellFormat));
            sheet1.addCell(new Label(8, 0, "项目所有人", cellFormat));
            sheet1.addCell(new Label(9, 0, "联系人姓名", cellFormat));
            sheet1.addCell(new Label(10, 0, "联系人电话", cellFormat));
            sheet1.addCell(new Label(11, 0, "省", cellFormat));
            sheet1.addCell(new Label(12, 0, "市", cellFormat));
            sheet1.addCell(new Label(13, 0, "地区", cellFormat));
            sheet1.addCell(new Label(14, 0, "地址", cellFormat));
            sheet1.addCell(new Label(15, 0, "项目进展阶段", cellFormat));
            sheet1.addCell(new Label(16, 0, "来源部门", cellFormat));
            sheet1.addCell(new Label(17, 0, "进展描述", cellFormat));
            sheet1.addCell(new Label(18, 0, "审核状态", cellFormat));
            sheet1.addCell(new Label(19, 0, "审核意见", cellFormat));
            int i = 1;
            HashMap<Integer, String> protypesId2Typename = new HashMap<>();

            HashMap<Integer, String> processId2Processname = new HashMap<>();
            List<HashMap> process = (List<HashMap>) roleService.getAllProcess();
            List<HashMap> protypes = (List<HashMap>) protypeService.getAllTypes();
            for (HashMap hashMap : protypes) {
                System.out.println(hashMap.get("typename"));
                System.out.println(hashMap.get("id"));
                protypesId2Typename.put((Integer) hashMap.get("id"), (String) hashMap.get("typename"));

            }

            for (HashMap hashMap : process) {
                processId2Processname.put((Integer) hashMap.get("id"), (String) hashMap.get("pname"));
            }
            List approveStateName = new ArrayList<String>();
            approveStateName.add("未审批");
            approveStateName.add("审批通过");
            approveStateName.add("审批未通过");

            while (i <= delCheckBox.length) {

                Project project = (Project) projectService.findObjectByid(delCheckBox[i - 1]);
                approveStateName.get(project.getApprovalState());
                sheet1.addCell(new Label(0, i, String.valueOf(project.getId()), cellFormat));
                sheet1.addCell(new Label(1, i, project.getProname(), cellFormat));
                sheet1.addCell(new Label(2, i, project.getStartTime(), cellFormat));
                sheet1.addCell(new Label(3, i, project.getEndTime(), cellFormat));
                sheet1.addCell(new Label(4, i, String.valueOf(project.getBudget()), cellFormat));
                if (project.getType1() == 0) {
                    sheet1.addCell(new Label(5, i, "无一级类型", cellFormat));
                } else {

                    sheet1.addCell(new Label(5, i, protypesId2Typename.get(project.getType1()), cellFormat));
                }
                if (project.getType2() == 0) {
                    sheet1.addCell(new Label(6, i, "无二级类型", cellFormat));
                } else {
                    sheet1.addCell(new Label(6, i, protypesId2Typename.get(project.getType2()), cellFormat));
                }
                sheet1.addCell(new Label(7, i, project.getScale(), cellFormat));
                sheet1.addCell(new Label(8, i, project.getUsername(), cellFormat));
                sheet1.addCell(new Label(9, i, project.getContactName(), cellFormat));
                sheet1.addCell(new Label(10, i, project.getContactPhone(), cellFormat));
                sheet1.addCell(new Label(11, i, project.getProvince(), cellFormat));
                sheet1.addCell(new Label(12, i, project.getCity(), cellFormat));
                sheet1.addCell(new Label(13, i, project.getArea(), cellFormat));
                sheet1.addCell(new Label(14, i, project.getAddress(), cellFormat));
                if (project.getProcessId() == 0) {
                    sheet1.addCell(new Label(15, i, "无进展阶段", cellFormat));
                } else {
                    sheet1.addCell(new Label(15, i, (String) processId2Processname.get(project.getProcessId()), cellFormat));
                }

                sheet1.addCell(new Label(16, i, project.getSourceDepartment(), cellFormat));
                sheet1.addCell(new Label(17, i, project.getProcessDiscription(), cellFormat));
                sheet1.addCell(new Label(18, i, (String) approveStateName.get(project.getApprovalState()), cellFormat));
                sheet1.addCell(new Label(19, i, project.getApprovalDiscription(), cellFormat));
                i++;
            }

            //将数字类型的行列值插入指定的页面
            //开始执行写入操作
            book.write();
            //关闭流
            book.close();
            byte[] content = os.toByteArray();
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=project.xls");
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setContentLength(content.length);
            response.getOutputStream().write(content);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

        }


    }

}
