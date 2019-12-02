package entity;
/**
 * 实体类Opinion
 * 这是对于菜品的评论（建议）
 */
public class Opinion {
    private long userId = 0;
    private int opinionId = 0;
    private String opinionMsg = "";    //改了一下名字
    private int dishId=0;


    public int getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(int opinionId) {
        this.opinionId = opinionId;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getOpinionMsg() {
        return opinionMsg;
    }

    public void setOpinionMsg(String opinionMsg) {
        this.opinionMsg = opinionMsg;
    }

}
