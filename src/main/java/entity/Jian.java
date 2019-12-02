package entity;

import java.sql.Date;

public class Jian {
    private int jianId = 0;
    private long userId = 0;
    private String name = "";
    private String msg = "";
    private int niceNum = 0;
    private String tu = "";
    private Date date = null;

    public int getJianId() {
        return jianId;
    }

    public void setJianId(int jianId) {
        this.jianId = jianId;
    }


    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getNiceNum() {
        return niceNum;
    }

    public void setNiceNum(int niceNum) {
        this.niceNum = niceNum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
