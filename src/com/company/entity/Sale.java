package com.company.entity;

public class Sale {
    private int sid;
    private String sname;
    private String spassword;

    public Sale() {
    }

    public Sale(int sid, String sname, String spassword) {
        this.sid = sid;
        this.sname = sname;
        this.spassword = spassword;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        this.spassword = spassword;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", spassword='" + spassword + '\'' +
                '}';
    }
}
