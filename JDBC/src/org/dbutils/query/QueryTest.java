package org.dbutils.query;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.jdbc.bean.Customer;
import org.jdbc.util.DruidUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author lijichen
 * @date 2020/9/22 - 20:30
 */
public class QueryTest {
    public static void main(String[] args) throws SQLException {
        Connection conn = DruidUtil.getConnection();
        //创建QueryRunner
        QueryRunner qr = new QueryRunner();
        BeanHandler<Customer> handler = new BeanHandler(Customer.class);
        Customer customer = qr.query(conn, "select id,name,email,birth from customers where id = ?", handler,123);
        System.out.println(customer);

        //返回多个值
        BeanListHandler<Customer> blhandler = new BeanListHandler<Customer>(Customer.class);
        //list
        List<Customer> customerList = qr.query(conn, "select id,name,email,birth from customers", blhandler);
        customerList.forEach(System.out::println);

        //map
        MapHandler mapHandler = new MapHandler();
        Map<String, Object> map = qr.query(conn, "select id,name,email,birth from customers", mapHandler);
        System.out.println(map+"=====");

        //list+map
        MapListHandler mapListHandler = new MapListHandler();
        List<Map<String, Object>> mapList = qr.query(conn, "select id,name,email,birth from customers", mapListHandler);
        mapList.forEach(System.out::println);

        /*
        * 查询特殊值
        * */
        ScalarHandler scalarHandler = new ScalarHandler();
        Date date = (Date) qr.query(conn, "select max(birth) from customers", scalarHandler);
        System.out.println(date);

        /*
        * 自定义handler
        * */
        String email = qr.query(conn, "select id,name,email,birth from customers where id = ?", new ResultSetHandler<String>() {
            @Override
            public String handle(ResultSet resultSet) throws SQLException {
                if (resultSet.next()) {
                    return (String) resultSet.getObject("email");
                }
                return null;
            }
        },123);
        System.out.println(email);

        //资源关闭
        //还需要处理异常
        DbUtils.close(conn);
        //已经处理掉异常的
//        DbUtils.closeQuietly(conn);
    }
}
