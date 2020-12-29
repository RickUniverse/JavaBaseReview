package org.dao.basedao;

import org.jdbc.util.JDBCUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 不能实例化的
 * 通用的数据操作类
 * @author lijichen
 * @date 2020/9/22 - 14:52
 */
public abstract class BaseDao<T> {

    /**
     * 用于指定查询方法的返回类型
     * 必须加泛型<T>
     */
    private Class<T> clazz = null;

    /**
     * 获取BaseDao子类的泛型类型，并将泛型类型赋值给clazz
     */
    {
        /*
        * --this的指向是实现了BaseDao的子类，因为BaseDao自始至终没有实例化过，
        *       所以，this指向的一直是具体的子类例如：CustomerDAOImpl
        * --this.getClass().getGenericSuperclass();相当于获取了：BaseDao<Customer>
        * */
        Type genericSuperclass = this.getClass().getGenericSuperclass();
//        System.out.println(genericSuperclass.getTypeName());//org.dao.basedao.BaseDao<org.jdbc.bean.Customer>
        /*
        * (ParameterizedType) genericSuperclass;
        * parameterizedType.getActualTypeArguments();
        *    相当于是BaseDao<Customer>的：Customer...
        *
        * actualTypeArguments[0] ： 就是具体的泛型
        * */
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
//        System.out.println(parameterizedType.getTypeName());//org.dao.basedao.BaseDao<org.jdbc.bean.Customer>

        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
//        System.out.println(actualTypeArguments[0]);//class org.jdbc.bean.Customer

        //将获取到的第一个泛型类型给clazz
        clazz = (Class) actualTypeArguments[0];

    }

    /**
     * 针对返回一条记录的查询操作的方法
     * @param conn ：考虑到事务，传入一个数据库连接
     * @param sql ：执行的sql语句
     * @param args ：占位符对应的参数
     * @return 返回将数据库中数据封装好的实例
     */
    public T getInstance(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            JDBCUtil.closeResource(null,ps,rs);
        }
        return null;
    }

    /**
     * 通用的针对返回多条查询记录的方法，如果有数据则返回一个数组
     * @param conn ：考虑到事务，传入一个数据库连接
     * @param sql ：执行的sql语句
     * @param args ：占位符对应的参数
     * @return 返回一个集合
     */
    public List<T> getForList(Connection conn, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
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
            JDBCUtil.closeResource(null,ps,rs);
        }
        return null;
    }

    /**
     * 通用的增删该操作的方法
     * @param con : 考虑到事务，传入一个数据库连接
     * @param sql ：执行的sql语句
     * @param args ：占位符对应的参数
     * @return ：受影响的行数
     */
    public int update(Connection con, String sql, Object... args) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            //替换占位符
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1,args[i]);
            }
            //启动查询
            return pstmt.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭,不关闭数据库连接
            JDBCUtil.closeResource(null,pstmt);
        }
        return 0;
    }

    /**
     * 返回只有一行一列数据的通用方法
     * @param con ：考虑到事务，传入一个数据库连接
     * @param sql ：查询语句
     * @param args ：占位符对应的参数
     * @param <E> ：泛型方法
     * @return ：返回一个<E> 类型的参数
     */
    public <E> E getValue(Connection con, String sql, Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                return (E) rs.getObject(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closeResource(null,ps,rs);
        }
        return null;
    }
}
