package org.jdbc.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author lijichen
 * @date 2020/9/22 - 17:58
 */
public class C3P0PoolUtil {

    /*
    * 创建数据库连接池
    * 数据库连接池一个就足够
    * */
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("testpool");

    /**
     * 使用C3P0连接池获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return cpds.getConnection();
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
