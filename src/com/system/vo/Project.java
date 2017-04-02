package com.system.vo;

import java.util.Date;

public class Project {
    private int id;//int(11)项目id
    private String proname;//varchar(500)项目名称
    private String startTime;//开始时间
    private String endTime;//结束时间
    private float budget;//投资预算
    private int type1;//一级类型
    private int type2;//二级类型
    private String scale;//建设规模
    private String userName;//所有人姓名
    private String contactName;
    private String contactPhone;
    private String area;
    private String city;
    private String province;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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
