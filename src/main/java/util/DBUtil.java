package util;

import java.sql.*;

public class DBUtil {

    public static Connection getConnection() {
        //准备Connection对象
        Connection connection = null;
        try {
            //初始化驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //准备参数
            //数据库名
            String dbname = "canteen";
            String name = "root";
            String password = "root";

            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/" + dbname + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", name, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement st){
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection con,PreparedStatement pst,ResultSet rs) {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if ( rs != null ) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
