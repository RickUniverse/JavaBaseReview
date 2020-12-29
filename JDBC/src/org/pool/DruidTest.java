package org.pool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 德鲁伊连接池
 * @author lijichen
 * @date 2020/9/22 - 19:48
 */
public class DruidTest {
    public static void main(String[] args) throws Exception {
        //通过系统类加载器获取流
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
        //创建Properties实例
        Properties pro = new Properties();
        //加载流
        pro.load(is);
        //获取数据库连接池
        DataSource source = DruidDataSourceFactory.createDataSource(pro);
        //获取连接
        Connection conn = source.getConnection();
        //测试
        System.out.println(conn);
    }
}
