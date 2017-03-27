package com.system.vo;

/**
 * ==================================
 * 实体类 - 系统管理 - 项目类型管理
 * ----------------------------------
 * <p>
 * ==================================
 */

public class Protype {
    private int id;//int(11)
    private String typename;//varchar(500)项目类型名称
    private int pid;//int(11)父类型id
    private int isfather;//int(11)1：一级类型 2：二级类型
    private String remark;//varchar(500)备注

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsfather() {
        return isfather;
    }

    public void setIsfather(int isfather) {
        this.isfather = isfather;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String toString() {
        return "ID:" + id + "typename:" + typename + "pid:" + pid + "isfather:" + isfather + "remark:" + remark;
    }
}
