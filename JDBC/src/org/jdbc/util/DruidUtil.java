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
 * 德鲁伊连接池工具类
 * @author lijichen
 * @date 2020/9/22 - 19:52
 */
public class DruidUtil {

    private static DataSource source;

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
