package org.jdbc.preparedstatement;

import org.jdbc.bean.Customer;
import org.jdbc.util.JDBCUtil;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * 通用的针对customer的查询
 * @author lijichen
 * @date 2020/9/20 - 18:11
 */
public class CustomerForQuery {
    public static void main(String[] args) {
        Customer customer = queryForCurstomer("select id,name,email,birth from customers where id = ?", 1);
        System.out.println(customer);
    }

    public static Customer queryForCurstomer(String sql, Object ...args) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            //执行并获取结果集
            rs = ps.executeQuery();
            //获取元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取元数据的列数
            int columnCount = rsmd.getColumnCount();
            //因为只返回一条语句，所以使用if
            if (rs.next()) {
                //创建对象
                Customer cus = new Customer();
                //对列数进行循环
                for (int i = 0; i < columnCount; i++) {
                    //获取结果集列的值
                    Object value = rs.getObject(i + 1);
                    //获取元数据列的名字getColumnName
                    //获取列的别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //通过反射对cus进行赋值操作
                    //获取customer对应的属性
                    Field field = Customer.class.getDeclaredField(columnLabel);
                    //因为属性可能是私有的所以
                    field.setAccessible(true);
                    //给cus赋值
                    field.set(cus,value);
                }
                //返回值
                return cus;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(con,ps,rs);
        }
        return null;
    }
}
