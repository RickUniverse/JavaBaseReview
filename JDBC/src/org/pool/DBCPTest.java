package org.pool;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * dbcp:数据库连接池技术
 * @author lijichen
 * @date 2020/9/22 - 18:50
 */
public class DBCPTest {
    public static void main(String[] args) throws Exception {
        //第一种方式
        /*BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName();
        bds.setUrl();
        bds.setUsername();
        bds.setPassword();*/

        //第二种方式
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        Properties pro = new Properties();
        pro.load(is);
        DataSource ds = BasicDataSourceFactory.createDataSource(pro);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
