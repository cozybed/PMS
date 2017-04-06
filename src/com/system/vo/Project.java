package com.system.vo;

public class Project {
    private int id;//int(11)项目id
    private String proname;//varchar(500)项目名称
    private String startTime;//开始时间
    private String endTime;//结束时间
    private float budget;//投资预算
    private int type1;//一级类型
    private int type2;//二级类型
    private String scale;//建设规模
    private String username;//所有人姓名
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
        if (startTime == "") {
            this.startTime = "1000-01-01";
            return;
        } else {
            this.startTime = startTime;
        }

    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        if (endTime == "") {
            this.endTime = "1000-01-01";
        } else {
            this.endTime = endTime;
        }
    }

    public float getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        if (budget == "") {
            this.budget = 0;
        } else {
            this.budget = Float.parseFloat(budget);
        }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setProcessId(String processId) {
        if (processId == "") {
            this.processId = 0;
            return;
        } else {
            this.processId = Integer.parseInt(processId);
        }

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

    public void setApprovalState(String approvalState) {
        if (approvalState == "") {
            this.approvalState = 0;
            return;
        }

        this.approvalState = Integer.parseInt(approvalState);
    }

    public String getApprovalDiscription() {

        return approvalDiscription;
    }

    public void setApprovalDiscription(String approvalDiscription) {
        this.approvalDiscription = approvalDiscription;
    }

    public String toString() {
        return "project:" + "id," + id + ";" + "proname," + proname + ";" + "startTime," + startTime + ";" + "endTime," + endTime + ";" + "budget," + budget + ";" + "type1," + type1 + ";" + "type2," + type2 + ";" + "scale," + scale + ";" + "username," + username + ";" + "contactName," + contactName + ";" + "contactPhone," + contactPhone + ";" + "area," + area + ";" + "city," + city + ";" + "province," + province + ";" + "address," + address + ";" + "processId," + processId + ";" + "sourceDepartment," + sourceDepartment + ";" + "processDiscription," + processDiscription + ";" + "approvalState," + approvalState + ";" + "approvalDiscription," + approvalDiscription + ";";
    }

    public static void main(String[] args) {
        System.out.println(Integer.parseInt(""));
    }
}
