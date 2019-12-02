package entity;

import java.sql.Date;
import java.util.List;

public class Dish {
    private int dishId = 0;
    private String name = "";
    private String msg = "";
    private int type;                     //菜系是选择项 我改成int了    qwq
    private double price = 0.00;
    private int niceNum = 0;
    private int badNum = 0;
    private Date date = null;
    private int window;
    private String tu ="";                   //图片问题暂不解决
    private List<Opinion> opinionList;  //菜品的评论为一个实体，改了一下属性

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    public List<Opinion> getOpinionList() {
//        return opinionList;
//    }
//
//    public void setOpinionList(List<Opinion> opinionList) {
//        this.opinionList = opinionList;
//    }

    public int getNiceNum() {
        return niceNum;
    }

    public void setNiceNum(int niceNum) {
        this.niceNum = niceNum;
    }


    public int getBadNum() {
        return badNum;
    }

    public void setBadNum(int badNum) {
        this.badNum = badNum;
    }

    public int getWindow() {
        return window;
    }

    public void setWindow(int window) {
        this.window = window;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTu() {
        return tu;
    }

    public void setTu(String tu) {
        this.tu = tu;
    }

}
