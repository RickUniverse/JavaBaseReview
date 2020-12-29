package org.dao.basedao;

import org.jdbc.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 顾客dao接口
 * @author lijichen
 * @date 2020/9/22 - 15:31
 */
public interface CustomerDAO {
    /**
     * 插入一个顾客
     * @param conn ：获取一个链接
     * @param cus ：传入顾客实例
     * @return 是否添加成功
     */
    boolean insert(Connection conn, Customer cus);

    /**
     * 按照id删除一个顾客
     * @param conn ： 传入一个数据库连接
     * @param id ：顾客id
     * @return 是否删除成功
     */
    boolean deleteById(Connection conn, int id);

    /**
     * 修改数据库中的数据
     * @param conn ：传入一个数据库连接
     * @param cus ：传入一个顾客类
     * @return 是否修改成功
     */
    boolean update(Connection conn, Customer cus);

    /**
     * 根据id获取一个customer实例
     * @param conn ：传入一个数据库连接
     * @param id ：顾客id
     * @return 返回一个customer实例
     */
    Customer getCustomerById(Connection conn, int id);

    /**
     * 获取所有数据
     * @param conn ：传入一个数据库连接
     * @return 数据集合
     */
    List<Customer> getAll(Connection conn);

    /**
     * 获取数据的数量
     * @param conn ：传入一个数据库连接
     * @return 数据的数量
     */
    long getCount(Connection conn);

    /**
     * 返回一个最大的日期
     * @param conn : 传入一个数据库连接
     * @return 返回一个最大的日期
     */
    Date getMaxBirth(Connection conn);
}
