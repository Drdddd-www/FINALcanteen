package dao;

import entity.Jian;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JianDao {

    //导入推荐菜品  ok
    public static boolean insert(Jian jian) {
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "INSERT INTO jian (jian_name,jian_msg,jian_niceNum,jian_userId,jian_tu,jian_date) VALUES(?,?,?,?,?,?)";
        //准备PreparedStatement
        PreparedStatement preparedStatement = null;
        try {
            //装配参数
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, jian.getName());
            preparedStatement.setString(2, jian.getMsg());
            preparedStatement.setInt(3, 0);
            preparedStatement.setLong(4,jian.getUserId());
            preparedStatement.setString(5,jian.getTu());
            preparedStatement.setDate(6,jian.getDate() );

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

    //点赞  完成了
    public static boolean addNiceNum(Jian jian) {
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "UPDATE jian SET jian_niceNum=? WHERE jian_name=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, jian.getNiceNum()+1);
            preparedStatement.setString(2, jian.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return true;
    }

    //取消点赞
    public static boolean  reduceNiceNum(Jian jian) {
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "UPDATE jian SET jian_niceNum=? WHERE jian_name=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, jian.getNiceNum()-1);
            preparedStatement.setString(2, jian.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return true;
    }


    public static List<Jian> selectByKeyWord(String keyWord) {
        Connection connection = DBUtil.getConnection();
        Statement statement=null;
        ResultSet resultSet=null;
        String sql = "SELECT * FROM jian WHERE jian_name LIKE '%"+keyWord+"%'";

        List<Jian> jianes = new ArrayList<>();
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Jian jian=new Jian();
                jian.setJianId(resultSet.getInt("jian_id"));
                jian.setName(resultSet.getString("jian_name"));        //resultSet.getString()中“”为表格列头的名称
                jian.setMsg(resultSet.getString("jian_msg"));
                jian.setNiceNum(resultSet.getInt("jian_niceNum"));
                jian.setUserId(resultSet.getLong("jian_userId"));
                jian.setTu(resultSet.getString("jian_tu"));
                jian.setDate(resultSet.getDate("jian_date"));
                jianes.add(jian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return jianes;
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
                jian.setJianId(resultSet.getInt("jian_id"));
                jian.setName(resultSet.getString("jian_name"));        //resultSet.getString()中“”为表格列头的名称
                jian.setMsg(resultSet.getString("jian_msg"));
                jian.setNiceNum(resultSet.getInt("jian_niceNum"));
                jian.setUserId(resultSet.getLong("jian_userId"));
                jian.setTu(resultSet.getString("jian_tu"));
                jian.setDate(resultSet.getDate("jian_date"));
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

    //通过菜品名称显示推荐菜品图片
    public static String showPicture(String name){
        String tuURL="";
        Connection connection = DBUtil.getConnection();
        Statement statement=null;
        ResultSet resultSet=null;
        String sql = "SELECT * FROM jian WHERE jian_name ='"+name+"'";
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                tuURL=resultSet.getString("jian_tu");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return tuURL;
    }

    //保存上传的图片的url
    public static void uploadPicture(Jian jian){
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "UPDATE jian SET jian_tu=? WHERE jian_name=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, jian.getTu());
            preparedStatement.setString(2, jian.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
    }

    //按照时间来排序 ok   修
    public static List<Jian> sortByDate(int curPage) {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM jian ORDER BY jian_date DESC LIMIT ("+(curPage-1)+")*8,8";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Jian> jians = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Jian jian=new Jian();
                jian.setJianId(resultSet.getInt("jian_id"));
                jian.setName(resultSet.getString("jian_name"));        //resultSet.getString()中“”为表格列头的名称
                jian.setMsg(resultSet.getString("jian_msg"));
                jian.setNiceNum(resultSet.getInt("jian_niceNum"));
                jian.setUserId(resultSet.getLong("jian_userId"));
                jian.setTu(resultSet.getString("jian_tu"));
                jian.setDate(resultSet.getDate("jian_date"));
                jians.add(jian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return jians;
    }



    //按照热度来排序 ok  修
    public static List<Jian> sortByNiceNum(int curPage) {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM jian ORDER BY jian_niceNum DESC LIMIT ("+(curPage-1)+")*8,8";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Jian> jians = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Jian jian=new Jian();
                jian.setJianId(resultSet.getInt("jian_id"));
                jian.setName(resultSet.getString("jian_name"));        //resultSet.getString()中“”为表格列头的名称
                jian.setMsg(resultSet.getString("jian_msg"));
                jian.setNiceNum(resultSet.getInt("jian_niceNum"));
                jian.setUserId(resultSet.getLong("jian_userId"));
                jian.setTu(resultSet.getString("jian_tu"));
                jian.setDate(resultSet.getDate("jian_date"));
                jians.add(jian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return jians;
    }

    //随机排序  ok   随机不重复找到8条   未测试
    public static List<Jian> sortRandomly(int page) {
        Connection connection = DBUtil.getConnection();
        String sql = "        SELECT * FROM jian AS t1 JOIN" +
                "                (SELECT ROUND(  (  (SELECT MAX(jian_id) FROM jian) - (SELECT MIN(jian_id) FROM jian)  ) * RAND() + (SELECT MIN(jian_id) FROM jian) ) AS id) AS t2" +
                "        WHERE t1.id >= t2.id" +
                "        ORDER BY t1.id LIMIT 8;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Jian> jians = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Jian jian=new Jian();
                jian.setJianId(resultSet.getInt("jian_id"));
                jian.setName(resultSet.getString("jian_name"));        //resultSet.getString()中“”为表格列头的名称
                jian.setMsg(resultSet.getString("jian_msg"));
                jian.setNiceNum(resultSet.getInt("jian_niceNum"));
                jian.setUserId(resultSet.getLong("jian_userId"));
                jian.setTu(resultSet.getString("jian_tu"));
                jian.setDate(resultSet.getDate("jian_date"));
                jians.add(jian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return jians;
    }

    //通过userId来得到Jian对象
    public static List<Jian> selectByUserId(long userId) {
        Connection connection = DBUtil.getConnection();
        Statement statement=null;
        ResultSet resultSet=null;
        String sql = "SELECT * FROM jian WHERE jian_userId ='"+userId+"'";
        List<Jian> jians = new ArrayList<>();


        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Jian jian =new Jian();
                jian.setJianId(resultSet.getInt("jian_id"));
                jian.setName(resultSet.getString("jian_name"));        //resultSet.getString()中“”为表格列头的名称
                jian.setMsg(resultSet.getString("jian_msg"));
                jian.setNiceNum(resultSet.getInt("jian_niceNum"));
                jian.setUserId(resultSet.getLong("jian_userId"));
                jian.setTu(resultSet.getString("jian_tu"));
                jian.setDate(resultSet.getDate("jian_date"));
                jians.add(jian);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return jians;
    }


}
