package dao;

import entity.OpinionToCanteen;
import entity.User;
import util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CanteenDao {

    public static boolean judge(User user) {
        boolean x = false;
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "SELECT * FROM user WHERE user_id=?";
        //准备PreparedStatement
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //装配参数
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user.getUserId());
            while (resultSet.next()) {
                x = true;
            }
            //执行语句
            preparedStatement.executeQuery();
        } catch (SQLException e) {
            //打印错误
            e.printStackTrace();
        } finally {
            //关闭资源
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
            DBUtil.close(resultSet);
        }
        return x;
    }

    public static void insert(User user) {
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "INSERT INTO user (user_id,month) VALUES(?,?)";
        //准备PreparedStatement
        PreparedStatement preparedStatement = null;
        try {
            //装配参数
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user.getUserId());
            preparedStatement.setInt(2, user.getMonth());

            //执行语句
            preparedStatement.execute();
        } catch (SQLException e) {
            //打印错误
            e.printStackTrace();
        } finally {
            //关闭资源
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
    }

    public static double calculateScore(double newScore) {
        OpinionToCanteen x = new OpinionToCanteen();
        double oldScore = x.getScore();
        int oldCount = x.getCount();
        x.setScore((oldScore * oldCount + newScore) / (oldCount + 1));
        x.setCount(oldCount + 1);
        return x.getScore();

    }

    public static int selectById(Long userId){
        int month=0;

        Connection connection= DBUtil.getConnection();
        String sql="SELECT * FROM user WHERE user_id=?";
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try{
            preparedStatement=connection.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                month=resultSet.getInt("month");
            }
        }catch (Exception e){

        }finally {
            DBUtil.close(preparedStatement);
            DBUtil.close(resultSet);
            DBUtil.close(connection);
        }
        return month;
    }

    public static void updateById(User user){
        Connection connection= DBUtil.getConnection();
        PreparedStatement preparedStatement=null;
        String sql="UPDATE user SET month=? WHERE user_id=?";
        try{
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,user.getMonth());
            preparedStatement.setLong(2,user.getUserId());
            preparedStatement.execute();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
    }


}
