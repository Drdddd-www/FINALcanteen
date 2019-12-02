package dao;


import entity.Dish;
import entity.Opinion;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
///**********看这里*******这里需要添加dishid。。。我改了
public class OpinionDao {
    //导入意见
    public static boolean insert(Opinion opinion) {
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "INSERT INTO opinion (dish_id,user_id,msg) VALUES(?,?,?)";
        //准备PreparedStatement
        PreparedStatement preparedStatement = null;
        try {
            //装配参数
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, opinion.getDishId());
            preparedStatement.setLong(1, opinion.getUserId());
            preparedStatement.setString(2, opinion.getOpinionMsg());
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
        return true;
    }


    //默认排序///**这里我改成int dish_id了。。
    public static List<Opinion> defaultSort(int curPage,int dish_id) {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM opinion WHERE dish_id=? LIMIT ("+(curPage-1)+")*8,8";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Opinion> opinions = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,dish_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Opinion opinion=new Opinion();
                opinion.setOpinionId(resultSet.getInt("opinion_id"));
                opinion.setUserId(resultSet.getLong("user_id"));
                opinion.setOpinionMsg(resultSet.getString("msg"));
                opinions.add(opinion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return opinions;
    }


    ///通过userId得到opinion对象
    public static List<Opinion> selectByUserId(long userId){
        Connection connection=DBUtil.getConnection();
        Statement statement=null;
        ResultSet resultSet=null;
        String sql = "SELECT * FROM opinion WHERE user_id ='"+userId+"'";
        List<Opinion> opinions = new ArrayList<>();
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                Opinion opinion =new Opinion();
                opinion.setOpinionId(resultSet.getInt("opinion_id"));
                opinion.setOpinionMsg(resultSet.getString("opinion_msg"));
                opinion.setDishId(resultSet.getInt("dish_id"));
                opinion.setUserId(resultSet.getLong("user_id"));
                opinions.add(opinion);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtil.close(resultSet);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return opinions;
    }



}
