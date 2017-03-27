package com.system.vo;

import java.util.Date;

public class Project {
    private int id;//int(11)项目id
    private String proname;//varchar(500)项目名称
    private Date startTime;//开始时间
    private Date endTime;//结束时间
    private float budget;//投资预算
    private int type1;//一级类型
    private int type2;//二级类型
    private String scale;//建设规模
    private int userId;//申请人id
    private int areaId;//区县id
    private int cityId;//市id
    private String address;//详细地址
    private int processId;//进展阶段
    private String sourceDepartment;//报送部门
    private String processDiscription;//进展说明
    private int approvalState;//审核状态
    private String approvalDiscription;//审核批复

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(float budget) {
        this.budget = budget;
    }

    public int getType1() {
        return type1;
    }

    public void setType1(int type1) {
        this.type1 = type1;
    }

    public int getType2() {
        return type2;
    }

    public void setType2(int type2) {
        this.type2 = type2;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getProcessId() {
        return processId;
    }

    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getSourceDepartment() {
        return sourceDepartment;
    }

    public void setSourceDepartment(String sourceDepartment) {
        this.sourceDepartment = sourceDepartment;
    }

    public String getProcessDiscription() {
        return processDiscription;
    }

    public void setProcessDiscription(String processDiscription) {
        this.processDiscription = processDiscription;
    }

    public int getApprovalState() {
        return approvalState;
    }

    public void setApprovalState(int approvalState) {
        this.approvalState = approvalState;
    }

    public String getApprovalDiscription() {
        return approvalDiscription;
    }

    public void setApprovalDiscription(String approvalDiscription) {
        this.approvalDiscription = approvalDiscription;
    }
}
