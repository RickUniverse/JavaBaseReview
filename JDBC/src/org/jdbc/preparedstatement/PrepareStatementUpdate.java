package org.jdbc.preparedstatement;

import org.jdbc.util.JDBCUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 * 增删改查
 * @author lijichen
 * @date 2020/9/20 - 16:45
 */
public class PrepareStatementUpdate {
    public static void main(String[] args) {
        insertCoustem();
        int count = update("update `order` set order_name = ? where order_id = ?", "asdf", 1);
        System.out.println(count);
    }

    //通用的增删该操作
    public static int update(String sql, Object... args) {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = JDBCUtil.getConnection();
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
            //关闭
            JDBCUtil.closeResource(con,pstmt);
        }
        return 0;
    }

    public static void insertCoustem() {
        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            //获取连接
            connection = JDBCUtil.getConnection();
            //得到preparestatement
            pstmt = connection.prepareStatement("insert into customers(name,email,birth) values(?,?,?)");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date parse = sdf.parse("2020-2-2");
            //填充占位符
            pstmt.setObject(1,"wang");
            pstmt.setObject(2,"asdf@qq.com");
            pstmt.setObject(3,new Date(parse.getTime()));
            //执行查询
            pstmt.execute();
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closeResource(connection,pstmt);
        }
    }
}
