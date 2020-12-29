package org.pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author lijichen
 * @date 2020/9/22 - 17:30
 */
public class C3P0Test {
    public static void main(String[] args) throws SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource("testpool");
        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }
}
