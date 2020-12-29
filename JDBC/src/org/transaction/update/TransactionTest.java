package org.transaction.update;

import org.jdbc.util.JDBCUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 事务操作
 * @author lijichen
 * @date 2020/9/21 - 18:21
 */
public class TransactionTest {
    public static void main(String[] args) {
        Connection con = null;
        try {
            //获取连接
            con = JDBCUtil.getConnection();

           /* //获取数据库的隔离级别
            System.out.println(con.getTransactionIsolation());

            //是指当前连接的隔离级别
            con.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);*/
            /*
            * 使用事务时
            * 需要设置自动提交为false
            * */
            con.setAutoCommit(false);
            //转账
            update(con,"update user_table set balance = balance - 100 where user = ?","AA");
            //模拟网路异常
            new Scanner(System.in).nextInt();
//            System.out.println(10/0);
            //收账
            update(con,"update user_table set balance = balance + 100 where user = ?","BB");

            /*
            * 全部执行成功就提交
            * */
            con.commit();

            System.out.println("转账成功！");
        } catch (Exception e) {
            e.printStackTrace();
            /*
             * 如果网络出现异常，就rollback回滚
             * */
            try {
                con.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            /*
             * 数据库连接：建议恢复为默认值,自动提交
             * 主要针对与数据库连接池
             * */
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            //关闭连接
            JDBCUtil.closeResource(con,null);
        }

    }

    //通用的增删该操作
    public static int update(Connection con, String sql, Object... args) {
        PreparedStatement pstmt = null;
        try {
            pstmt = con.prepareStatement(sql);
            //替换占位符
            for (int i = 0; i < args.length; i++) {
                pstmt.setObject(i + 1,args[i]);
            }
            //启动修改
            return pstmt.executeUpdate();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            //关闭,事务不关闭数据库连接
            JDBCUtil.closeResource(null,pstmt);
        }
        return 0;
    }
}
class Test {
    public static void main(String[] args) throws Exception {
        Connection con = JDBCUtil.getConnection();
        con.setAutoCommit(false);
        TransactionTest.update(con,"update user_table set balance = balance - 100 where user = ?","AA");
        //没有提交的话自动回滚，操作全部无效
        JDBCUtil.closeResource(con,null);
    }
}