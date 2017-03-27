package com.system.vo;

public class MyProcess {
	int id;//int(11)进度表id
	String pname;//varchar(500)进度名称
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
}
