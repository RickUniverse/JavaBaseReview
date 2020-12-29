package org.jdbc.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * BDCP连接池获取连接
 * @author lijichen
 * @date 2020/9/22 - 19:01
 */
public class BDCPUtil {

    //维护一个数据源
    private static DataSource source = null;
    //因为只需要创建一个连接池，所以使用static代码块
    static {
        try {
            //通过系统类加载器获取配置文件流
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
            Properties pro = new Properties();
            //加载流
            pro.load(is);
            //创建数据库连接池
            source = BasicDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return source.getConnection();
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
