package org.jdbc.preparedstatement;

import org.jdbc.bean.Customer;
import org.jdbc.bean.Order;
import org.jdbc.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 通用的表查询
 * @author lijichen
 * @date 2020/9/20 - 19:36
 */
public class PreparedStatementForQuery {
    public static void main(String[] args) {
        Customer customer = getInstance(Customer.class, "select id,name from customers where id = ?", 1);
        System.out.println(customer);
        Order order = getInstance(Order.class, "select order_id orderId,order_name orderName from `order` where order_id = ?", 1);
        System.out.println(order);

        System.out.println("______________________");
        //获取集合
        List<Customer> customerList = getForList(Customer.class, "select id,name from customers where id <> ?", 100);
        customerList.forEach(System.out::println);
        List<Order> orderList = getForList(Order.class, "select order_id orderId,order_name orderName from `order` where order_id <= ?", 100);
        orderList.forEach(System.out::println);
    }

    public static <T> List<T> getForList(Class<T> clazz, String sql, Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            ArrayList<T> list = new ArrayList<>();

            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object value = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,value);
                }

                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }
        return null;
    }

    public static <T> T getInstance(Class<T> clazz, String sql, Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object value = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,value);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn,ps,rs);
        }
        return null;
    }
}
