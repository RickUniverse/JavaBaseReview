package org.jdbc.blob;

import org.jdbc.bean.Customer;
import org.jdbc.util.JDBCUtil;

import java.io.*;
import java.sql.*;

/**
 * 操作blob
 * @author lijichen
 * @date 2020/9/21 - 15:36
 */
public class OperationBlob {
    public static void main(String[] args) {
//        update();
        query();
    }

    public static void update() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("insert into customers values(?,?,?,?,?)");
            ps.setObject(1,123);
            ps.setObject(2,"123");
            ps.setObject(3,"asdf");
            ps.setObject(4,"2020-2-2");
            FileInputStream fis = new FileInputStream(new File("other\\1.png"));
            ps.setBlob(5,fis);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeResource(conn,ps);
        }
    }

    //查询blob字段
    public static void query() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("select * from customers where id = ?");
            ps.setInt(1,16);
            rs = ps.executeQuery();
            is = null;
            fos = null;
            if (rs.next()) {
                Customer cus = new Customer(rs.getInt("id"),rs.getString("name"),rs.getString("email"),rs.getDate("birth"));
                System.out.println(cus);
                Blob photo = rs.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream(new File("other\\zhuxi.png"));
                byte[] buffer = new byte[1024];
                int len;
                while (((len = is.read(buffer)) != -1)) {
                    fos.write(buffer,0,len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (is != null)
                    is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            JDBCUtil.closeResource(conn,ps,rs);
        }
    }
}
