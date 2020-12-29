package org.jdbc.batch;

import org.jdbc.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 高效的批量插入
 * @author lijichen
 * @date 2020/9/21 - 16:55
 */
public class BatchInsert {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtil.getConnection();
            /*
            * 优化效率
            * 禁止自动提交（commit）
            * 到最后统一commit
            * */
            conn.setAutoCommit(false);
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 1000000; i++) {
                ps.setObject(1,"name_"+i+"");

                /*
                * 优化：1，赞代码，攒够一定数量执行一次
                * 使用batch（）需要修改url的最后改为：url=jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true
                *   mysql-connector-java-5.1.37-bin.jar之前的版本不支持
                *  */
                ps.addBatch();
                //赞够500次，提交,不是赞的越多越好
                if (i % 500 == 0) {
                    ps.executeBatch();//2，提交

                    ps.clearBatch();//3，清空
                }

            }
            /*
            * 最后执行完毕后提交
            * */
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println((end - start));
        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn,ps);
        }
    }
}
