package entity;
/**
 * 实体类OpinionToCanteen
 * 这是对于食堂的评论（建议）
 * 意见是选择题！！！！
 * 但事实具体的还没有想好
 * 先留着
 */
public class OpinionToCanteen {
    private static double score = 0;
    private static int count = 0;

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
