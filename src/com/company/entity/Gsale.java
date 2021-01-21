package com.company.entity;

public class Gsale {
    private int gid;
    private int sid;
    private int count;

    public Gsale() {
    }

    public Gsale(int gid, int sid, int count) {
        this.gid = gid;
        this.sid = sid;
        this.count = count;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Gsales{" +
                "gid=" + gid +
                ", sid=" + sid +
                ", count=" + count +
                '}';
    }
}
