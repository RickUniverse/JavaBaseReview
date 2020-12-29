package org.jdbc.connection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 获取数据库连接
 * @author lijichen
 * @date 2020/9/19 - 17:34
 */
public class ConnectionTest {
    public static void main(String[] args) throws SQLException, Exception {
        /*//方式1；
        //创建driver对象
//        Driver driver = new com.mysql.jdbc.Driver();
        //方式2
        //使用反射
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver)clazz.newInstance();
        //连接字符串
        String url = "jdbc:mysql://localhost:3306/test";
        //用户名和密码
        Properties info = new Properties();
        info.put("user","root");
        info.put("password","root");
        //获取连接
        Connection connect = driver.connect(url, info);

        System.out.println(connect);*/

        /*//方式三
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver)clazz.newInstance();
        //注册驱动
        DriverManager.deregisterDriver(driver);
        //获取连接
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);*/

        /*//方式四
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "root";
        //直接加载驱动
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
//        Driver driver = (Driver)clazz.newInstance();
        //注册驱动
//        DriverManager.deregisterDriver(driver);
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);*/

        //方式五
        /*
        * 好处，
        * 1，实现了数据跟代码的分离，解耦
        * 2，如果需要修改配置文件可以避免重新打包
        * */
        //通过类的加载器获取流
        InputStream is = new ConnectionTest().getClass().getClassLoader().getResourceAsStream("jdbc.properties");
        //创建properties类
        Properties properties = new Properties();
        //加载流
        properties.load(is);
        //获取配置
        String driverManager = properties.getProperty("driverManager");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");

        //加载驱动
        Class.forName(driverManager);
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
