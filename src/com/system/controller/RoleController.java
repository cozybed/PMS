package com.system.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.common.ImportExcel;
import com.common.ExportExcel;
import com.common.PageBean;
import com.system.service.RoleService;
import com.system.vo.MyProcess;
import com.system.vo.Project;
import com.system.vo.Role;

@Component
//@Constroller
@RequestMapping("/role")
//角色信息管理
@Scope("prototype")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 角色列表查询
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Rolename
     * @param currentPage
     * @param pageSize
     * @param value
     * @param values
     * @return
     */
    @RequestMapping(value = "roleList", method = {RequestMethod.GET, RequestMethod.POST})
    public String roleList(
            ModelMap modelMap, HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "s_role_name", defaultValue = "") String s_role_name,
            @RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
            @RequestParam(value = "pageSize", defaultValue = "20") String pageSize,
            @RequestParam(value = "value", defaultValue = "") String value,
            @RequestParam(value = "value", defaultValue = "") String[] values) {

        Map param = new HashMap();
        param.put("pageSize", pageSize);
        param.put("currentPage", currentPage);
        param.put("s_role_name", s_role_name);
        PageBean pageBean = roleService.createQueryPage(param);
        modelMap.put("pageBean", pageBean);
        return "system/role/showRole";
    }

    /**
     * 角色编辑
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Role
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "editRole", method = {RequestMethod.GET, RequestMethod.POST})
    public void editRole(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Role Role) throws IOException {
        if (Role.getId() == 0) {
            response.getWriter().print(roleService.addObject(Role));
        } else {
            response.getWriter().print(roleService.updateObject(Role));
        }

    }

    /**
     * 跳到编辑角色
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Role
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "toUpdateRole", method = {RequestMethod.GET, RequestMethod.POST})
    public String toUpdateRole(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Role Role) throws IOException {
        Map map = (Map) roleService.imAllCap("select * from t_role where id=" + Role.getId()).get(0);
        request.setAttribute("map", map);
        return "system/role/editRole";
    }

    /**
     * 跳到查看角色
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Role
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "toViewRole", method = {RequestMethod.GET, RequestMethod.POST})
    public String toViewRole(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Role Role) throws IOException {
        //Map map = (Map) roleService.findObjectById(Role.getId()());
        //request.setAttribute("map", map);
        return "system/role/viewRole";
    }


    /**
     * 删除角色
     *
     * @param modelMap
     * @param request
     * @param response
     * @param delCheckBox
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "delRole", method = {RequestMethod.GET, RequestMethod.POST})
    public void delRole(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "delCheckBox", defaultValue = "")
            String[] delCheckBox) throws IOException {
        String staus = "";
        if (delCheckBox.length > 0) {
            for (String id : delCheckBox) {
                String flag = roleService.imCap("delete from t_role where id=" + id);
                if (flag.equals("notOk")) {
                    staus = flag;
                    break;
                } else {
                    staus = "ok";
                }

            }
        }
        PrintWriter pw = response.getWriter();
        pw.write(staus);
        pw.flush();

    }

    /**
     * 导出角色
     *
     * @param modelMap
     * @param request
     * @param response
     * @param delCheckBox
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "exportRole", method = {RequestMethod.GET, RequestMethod.POST})
    public void exportRole(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "delCheckBox", defaultValue = "")
            String[] delCheckBox) throws IOException {
        List<Role> rolelist = new ArrayList();
        if (delCheckBox.length > 0) {
            for (String id : delCheckBox) {
                HashMap map = (HashMap) roleService.findObjectById(id);
                /*Iterator iter = map.keySet().iterator();
                while (iter.hasNext()) {
						Object key = iter.next();
						Object val = map.get(key);
						System.out.println("------------"+key+"---------------------"+val+"----------------------");
				}*/
                if (map != null) {
                    Role tmpRole = new Role();
                    tmpRole.setId(Integer.parseInt(map.get("id").toString()));
                    tmpRole.setRolename(map.get("rolename").toString());
                    tmpRole.setRemark(map.get("remark").toString());
                    rolelist.add(tmpRole);
                }
            }
        }
        String[] headers = {"编号", "角色", "备注"};
        ExportExcel<Role> ex = new ExportExcel<Role>();
        OutputStream out = new FileOutputStream("/home/luojiarui/role.xls");
        ex.exportExcel(headers, rolelist, out);
        System.out.println("excel导出成功！");
        PrintWriter pw = response.getWriter();
        pw.write("ok");
        pw.flush();
    }

    /**
     * 导入角色excel
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Role
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "importRole", method = {RequestMethod.GET, RequestMethod.POST})
    public void importRole(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImportExcel em = new ImportExcel("/home/luojiarui/role.xls");
        String staus = "ok";
        Role role = new Role();
        String[] headers = {"id", "rolename", "remark"};
        List list = em.readFromExcel(role, headers);
        for (int i = 0; i < list.size(); i++) {
            Role newRole = (Role) list.get(i);
            System.out.println(newRole.getId() + " " + newRole.getRolename() + " " + newRole.getRemark());
            if (roleService.addObject(newRole).equals("notOk")) {
                staus = "notOk";
            }
        }
        PrintWriter pw = response.getWriter();
        pw.write(staus);
        pw.flush();
    }

    /**
     * 编辑菜单权限页面
     */
    @RequestMapping(value = "toEditRoleMenu", method = {RequestMethod.GET, RequestMethod.POST})
    public String toEditRoleMenu(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Role Role) throws IOException {
        String roleId = request.getParameter("roleId"); // 权限ID
        request.setAttribute("list", roleService.imAllCap("select * from t_role_menu where  role_id=" + roleId));
        request.setAttribute("roleId", roleId);
        request.setAttribute("allmenu", roleService.imAllCap("select * from t_menu"));
        return "system/role/showRoleMenu";
    }

    /**
     * 编辑菜单权限
     */
    @RequestMapping(value = "editRoleMenu", method = {RequestMethod.GET, RequestMethod.POST})
    public void editRoleMenu(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, String[] menu_id, Role role) throws IOException {

        String role_id = request.getParameter("roleId");
        roleService.imCap("delete from t_role_menu where role_id=" + role_id);
        System.out.println(role_id);
        for (String a : menu_id) {
            roleService.imCap("insert into t_role_menu values(" + role_id + ",\"" + a + "\")");
        }
        response.getWriter().print("ok");

    }


    /**
     * 项目进度列表查询
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Rolename
     * @param currentPage
     * @param pageSize
     * @param value
     * @param values
     * @return
     */
    @RequestMapping(value = "processList", method = {RequestMethod.GET, RequestMethod.POST})
    public String processList(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "s_role_name", defaultValue = "") String s_role_name,
                              @RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
                              @RequestParam(value = "pageSize", defaultValue = "20") String pageSize,
                              @RequestParam(value = "value", defaultValue = "") String value,
                              @RequestParam(value = "value", defaultValue = "") String[] values
    ) {

        Map param = new HashMap();
        param.put("pageSize", pageSize);
        param.put("currentPage", currentPage);
        param.put("s_role_name", s_role_name);
        PageBean pageBean = roleService.createQueryPageForProcess(param);
        //System.out.println("+++++++++++++++" + pageBean.getAllRow());
        modelMap.put("pageBean", pageBean);
        return "system/process/show";


    }


    /**
     * 删除进度信息
     *
     * @param modelMap
     * @param request
     * @param response
     * @param delCheckBox
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "delProcess", method = {RequestMethod.GET, RequestMethod.POST})
    public void delProcess(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "delCheckBox", defaultValue = "")
            String[] delCheckBox) throws IOException {
        String staus = "";
        if (delCheckBox.length > 0) {
            for (String id : delCheckBox) {
                String flag = roleService.imCap("delete from t_process where id=" + id);
                if (flag.equals("notOk")) {
                    staus = flag;
                    break;
                } else {
                    staus = "ok";
                }

            }
        }
        PrintWriter pw = response.getWriter();
        pw.write(staus);
        pw.flush();
    }

    /**
     * 跳到编辑进度信息
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Role
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "toUpdateProcess", method = {RequestMethod.GET, RequestMethod.POST})
    public String toUpdateProcess(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, MyProcess Role) throws IOException {
        Map map = (Map) roleService.imAllCap("select * from t_process where id=" + Role.getId()).get(0);
        request.setAttribute("map", map);
        return "system/process/edit";
    }

    /**
     * 进度信息编辑
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Role
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "editProcess", method = {RequestMethod.GET, RequestMethod.POST})
    public void editProcess(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, MyProcess v) throws IOException {
        if (v.getId() == 0) {
            roleService.imCap("insert into t_process (pname) values (  '" + v.getPname() + "' ) ");
        } else {
            roleService.imCap("update t_process set p ='" + v.getPname() + "'   where id ='" + v.getId() + "'  ");
        }
        response.getWriter().print("ok");
    }


    /**
     * 储备项目列表查询
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Rolename
     * @param currentPage
     * @param pageSize
     * @param value
     * @param values
     * @return
     */
    @RequestMapping(value = "notProjectList", method = {RequestMethod.GET, RequestMethod.POST})
    public String notProjectList(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
                                 @RequestParam(value = "pageSize", defaultValue = "20") String pageSize,
                                 @RequestParam(value = "value", defaultValue = "") String value,
                                 @RequestParam(value = "value", defaultValue = "") String[] values) {

        Map param = new HashMap();
        param.put("pageSize", pageSize);
        param.put("currentPage", currentPage);
        PageBean pageBean = roleService.createQueryPageForNotProject(param);
        modelMap.put("pageBean", pageBean);
        return "system/project/showNot";
    }


    /**
     * 项目列表查询
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Rolename
     * @param currentPage
     * @param pageSize
     * @param value
     * @param values
     * @return
     */
    @RequestMapping(value = "projectList", method = {RequestMethod.GET, RequestMethod.POST})
    public String projectList(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(value = "currentPage", defaultValue = "1") String currentPage,
                              @RequestParam(value = "pageSize", defaultValue = "20") String pageSize,
                              @RequestParam(value = "value", defaultValue = "") String value,
                              @RequestParam(value = "value", defaultValue = "") String[] values) {

        Map param = new HashMap();
        param.put("pageSize", pageSize);
        param.put("currentPage", currentPage);
        PageBean pageBean = roleService.createQueryPageForProject(param);
        modelMap.put("pageBean", pageBean);
        return "system/project/show";
    }


    /**
     * 删除项目信息
     *
     * @param modelMap
     * @param request
     * @param response
     * @param delCheckBox
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "delProject", method = {RequestMethod.GET, RequestMethod.POST})
    public void delProject(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "delCheckBox", defaultValue = "") String[] delCheckBox) throws IOException {
        String staus = "";
        if (delCheckBox.length > 0) {
            for (String id : delCheckBox) {
                String flag = roleService.imCap("delete from t_project where id=" + id);
                if (flag.equals("notOk")) {
                    staus = flag;
                    break;
                } else {
                    staus = "ok";
                }
            }
        }
        PrintWriter pw = response.getWriter();
        pw.write(staus);
        pw.flush();
    }

    /**
     * 跳到编辑项目信息
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Role
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "toUpdateProject", method = {RequestMethod.GET, RequestMethod.POST})
    public String toUpdateProject(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Project p) throws IOException {
        Map map = (Map) roleService.imAllCap("select * from t_project where id=" + p.getId()).get(0);
        request.setAttribute("map", map);
        return "system/project/edit";
    }

    /**
     * 项目信息编辑
     *
     * @param modelMap
     * @param request
     * @param response
     * @param Role
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "editProject", method = {RequestMethod.GET, RequestMethod.POST})
    public void editProject(ModelMap modelMap, HttpServletRequest request, HttpServletResponse response, Project v) throws IOException {
        if (v.getId() == 0) {
            roleService.imCap("insert into t_process (pname) values (  '" + v.getProname() + "' ) ");
        } else {
            roleService.imCap("update t_process set jgmc ='" + v.getProname() + "'   where id ='" + v.getId() + "'  ");
        }
    }


}