package dao;

import entity.Jian;
import entity.User;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    //默认排序
    public static List<Jian> defaultSort(User user) {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM jian WHERE jian_userId=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Jian> jians = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,user.getUserId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Jian jian=new Jian();
                jian.setTu(resultSet.getString("jian_tu"));
                jian.setMsg(resultSet.getString("jian_msg"));
                jian.setName(resultSet.getString("jian_name"));
                jian.setNiceNum(resultSet.getInt("jian_niceNum"));
                jians.add(jian);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return jians;
    }

    //通过菜品名字来得到Jian对象
    public static Jian selectByName(String name) {
        Connection connection = DBUtil.getConnection();
        Statement statement=null;
        ResultSet resultSet=null;
        String sql = "SELECT * FROM jian WHERE jian_name ='"+name+"'";
        Jian jian=new Jian();

        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                jian.setName(resultSet.getString("jian_name"));        //resultSet.getString()中“”为表格列头的名称
                jian.setMsg(resultSet.getString("jian_ingredients"));
                jian.setNiceNum(resultSet.getInt("jian_niceNum"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return jian;
    }
}
