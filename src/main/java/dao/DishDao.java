package dao;

import entity.Dish;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DishDao {

    //导入菜品
    public static void insert(Dish dish) {
    }

//    //通过名称显示菜品图片
//    public static String showPicture(String name){
//            String tuURL="";
//            Connection connection = DBUtil.getConnection();
//            Statement statement=null;
//            ResultSet resultSet=null;
//            String sql = "SELECT * FROM dish WHERE dish_name ='"+name+"'";
//            try {
//                statement=connection.createStatement();
//                resultSet = statement.executeQuery(sql);
//                while (resultSet.next()) {
//
//                    tuURL=resultSet.getString("dish_tu");
//
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } finally {
//                DBUtil.close(resultSet);
//                DBUtil.close(statement);
//                DBUtil.close(connection);
//            }
//            return tuURL;
//        }



    //点赞  完成了
    public static boolean addNiceNum(Dish dish) {
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "UPDATE dish SET dish_niceNum=? WHERE dish_name=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, dish.getNiceNum()+1);
            preparedStatement.setString(2, dish.getName());
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
    public static boolean reduceNiceNum(Dish dish) {
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "UPDATE dish SET dish_niceNum=? WHERE dish_name=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, dish.getNiceNum()-1);
            preparedStatement.setString(2, dish.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return  true;
    }

    //差评  完成了
    public static boolean addBadNum(Dish dish) {
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "UPDATE dish SET dish_badNum=? WHERE dish_name=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, dish.getBadNum()+1);
            preparedStatement.setString(2, dish.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return  true;
    }

    //取消差评
    public static boolean reduceBadNum(Dish dish) {
        //建立连接
        Connection connection = DBUtil.getConnection();
        //书写sql语句
        String sql = "UPDATE dish SET dish_badNum=? WHERE dish_name=?";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, dish.getBadNum()-1);
            preparedStatement.setString(2, dish.getName());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return  false;
    }

    //按照关键字来搜索   ok
    public static List<Dish> selectByKeyWord(String keyWord) {
        Connection connection = DBUtil.getConnection();
        Statement statement=null;
        ResultSet resultSet=null;
        String sql = "SELECT * FROM dish WHERE dish_name LIKE '%"+keyWord+"%'";


        List<Dish> dishes = new ArrayList<>();
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Dish dish=new Dish();
                dish.setDishId(resultSet.getInt("dish_id"));
                dish.setName(resultSet.getString("dish_name"));        //resultSet.getString()中“”为表格列头的名称
                dish.setMsg(resultSet.getString("dish_msg"));
                dish.setPrice(resultSet.getDouble("dish_price"));
                dish.setWindow(resultSet.getInt("dish_window"));
                dish.setTu(resultSet.getString("dish_tu"));
                dish.setNiceNum(resultSet.getInt("dish_niceNum"));
                dish.setBadNum(resultSet.getInt("dish_badNum"));
                dish.setDate(resultSet.getDate("dish_date"));
                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return dishes;
    }


    //通过菜品名字来得到Dish对象
    public static Dish selectByName(String name) {
        Connection connection = DBUtil.getConnection();
        Statement statement=null;
        ResultSet resultSet=null;
        String sql = "SELECT * FROM dish WHERE dish_name ='"+name+"'";
        Dish dish=new Dish();

        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                dish.setDishId(resultSet.getInt("dish_id"));
                dish.setName(resultSet.getString("dish_name"));        //resultSet.getString()中“”为表格列头的名称
                dish.setMsg(resultSet.getString("dish_msg"));
                dish.setPrice(resultSet.getDouble("dish_price"));
                dish.setWindow(resultSet.getInt("dish_window"));
                dish.setTu(resultSet.getString("dish_tu"));
                dish.setNiceNum(resultSet.getInt("dish_niceNum"));
                dish.setBadNum(resultSet.getInt("dish_badNum"));
                dish.setDate(resultSet.getDate("dish_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(statement);
            DBUtil.close(connection);
        }
        return dish;
    }

    //按照窗口来筛选     写完了
    public static List<Dish> selectByWindow(int dish_window,int curPage) {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM dish WHERE dish_window=? LIMIT ("+(curPage-1)+")*8,8";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dish> dishes = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, dish_window);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Dish dish=new Dish();
                dish.setDishId(resultSet.getInt("dish_id"));
                dish.setName(resultSet.getString("dish_name"));        //resultSet.getString()中“”为表格列头的名称
                dish.setMsg(resultSet.getString("dish_msg"));
                dish.setPrice(resultSet.getDouble("dish_price"));
                dish.setWindow(resultSet.getInt("dish_window"));
                dish.setTu(resultSet.getString("dish_tu"));
                dish.setNiceNum(resultSet.getInt("dish_niceNum"));
                dish.setBadNum(resultSet.getInt("dish_badNum"));
                dish.setDate(resultSet.getDate("dish_date"));
                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return dishes;
    }


    //按照时间来排序  ok
    public static List<Dish> sortByDate(int curPage) {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM dish ORDER BY dish_date DESC LIMIT ("+(curPage-1)+")*8,8";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dish> dishes = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Dish dish = new Dish();
                dish.setDishId(resultSet.getInt("dish_id"));
                dish.setName(resultSet.getString("dish_name"));        //resultSet.getString()中“”为表格列头的名称
                dish.setMsg(resultSet.getString("dish_msg"));
                dish.setPrice(resultSet.getDouble("dish_price"));
                dish.setWindow(resultSet.getInt("dish_window"));
                dish.setTu(resultSet.getString("dish_tu"));
                dish.setNiceNum(resultSet.getInt("dish_niceNum"));
                dish.setBadNum(resultSet.getInt("dish_badNum"));
                dish.setDate(resultSet.getDate("dish_date"));
                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return dishes;
    }



    //按照热度来排序  ok
    public static List<Dish> sortByNiceNum(int curPage) {
        Connection connection = DBUtil.getConnection();
        String sql = "SELECT * FROM dish ORDER BY dish_niceNum DESC LIMIT ("+(curPage-1)+")*8,8";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dish> dishes = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Dish dish = new Dish();
                dish.setDishId(resultSet.getInt("dish_id"));
                dish.setName(resultSet.getString("dish_name"));        //resultSet.getString()中“”为表格列头的名称
                dish.setMsg(resultSet.getString("dish_msg"));
                dish.setPrice(resultSet.getDouble("dish_price"));
                dish.setWindow(resultSet.getInt("dish_window"));
                dish.setTu(resultSet.getString("dish_tu"));
                dish.setNiceNum(resultSet.getInt("dish_niceNum"));
                dish.setBadNum(resultSet.getInt("dish_badNum"));
                dish.setDate(resultSet.getDate("dish_date"));
                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return dishes;
    }


    //随机排序  ok   随机不重复找到8条
    public static List<Dish> sortRandomly(int page) {
        Connection connection = DBUtil.getConnection();
        String sql = "        SELECT * FROM dish AS t1 JOIN" +
                "                (SELECT ROUND(  (  (SELECT MAX(dish_id) FROM dish) - (SELECT MIN(dish_id) FROM dish)  ) * RAND() + (SELECT MIN(dish_id) FROM dish) ) AS id) AS t2" +
                "        WHERE t1.id >= t2.id" +
                "        ORDER BY t1.id LIMIT 8;";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dish> dishes = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Dish dish = new Dish();
                dish.setDishId(resultSet.getInt("dish_id"));
                dish.setName(resultSet.getString("dish_name"));        //resultSet.getString()中“”为表格列头的名称
                dish.setMsg(resultSet.getString("dish_msg"));
                dish.setPrice(resultSet.getDouble("dish_price"));
                dish.setWindow(resultSet.getInt("dish_window"));
                dish.setTu(resultSet.getString("dish_tu"));
                dish.setNiceNum(resultSet.getInt("dish_niceNum"));
                dish.setBadNum(resultSet.getInt("dish_badNum"));
                dish.setDate(resultSet.getDate("dish_date"));
                dishes.add(dish);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet);
            DBUtil.close(preparedStatement);
            DBUtil.close(connection);
        }
        return dishes;
    }

}

