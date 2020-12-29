package org.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Collection;
import java.util.Properties;

/** 获取数据库连接的工具类
 * @author lijichen
 * @date 2020/9/20 - 16:33
 */
public class JDBCUtil {

    //获取连接
    public static Connection getConnection() throws Exception {

        Connection connection = null;
        //通过系统类加载器获得流
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        //创建properties类
        Properties properties = new Properties();
        //加载流
        properties.load(is);
        String driverManager = properties.getProperty("driverManager");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");

        //加载驱动
        Class.forName(driverManager);
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    //关闭流
    public static void closeResource(Connection con, Statement stmt) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    //查询操作关闭流
    public static void closeResource(Connection con, Statement stmt, ResultSet rs) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
