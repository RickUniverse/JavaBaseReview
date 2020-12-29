package org.dao.basedao;

import org.jdbc.util.C3P0PoolUtil;
import org.jdbc.util.DruidUtil;
import org.jdbc.util.JDBCUtil;

/**
 * @author lijichen
 * @date 2020/9/22 - 16:38
 */
public class Test {

    public static void main(String[] args) throws Exception {
//        CustomerDAOImpl cImp = new CustomerDAOImpl();
//        cImp.getAll(JDBCUtil.getConnection()).forEach(System.out::println);
        //c3p0使用数据库连接池
//        CustomerDAOImpl cImp = new CustomerDAOImpl();
//        cImp.getAll(C3P0PoolUtil.getConnection()).forEach(System.out::println);
        //使用dbcp数据库连接池
//        CustomerDAOImpl cImp = new CustomerDAOImpl();
//        cImp.getAll(JDBCUtil.getConnection()).forEach(System.out::println);
        //使用druid数据库连接池
        CustomerDAOImpl cImp = new CustomerDAOImpl();
        cImp.getAll(DruidUtil.getConnection()).forEach(System.out::println);
    }
}
