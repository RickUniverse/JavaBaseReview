package org.dao.basedao;

import org.jdbc.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 具体实现类
 * @author lijichen
 * @date 2020/9/22 - 15:42
 */
public class CustomerDAOImpl extends BaseDao<Customer> implements CustomerDAO {

    @Override
    public boolean insert(Connection conn, Customer cus) {
        String sql = "insert into customers(name,email,birth) values(?,?,?)";
        return update(conn,sql,cus.getName(),cus.getEmail(),cus.getBirth()) > 0;
    }

    @Override
    public boolean deleteById(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        return update(conn,sql,id) > 0;
    }

    @Override
    public boolean update(Connection conn, Customer cus) {
        String sql = "update from customers set name = ?,email = ?,birth = ? where id = ?";
        return update(conn,sql,cus.getName(),cus.getEmail(),cus.getBirth(),cus.getId()) > 0;
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "select name,email,birth from customers where id = ?";
        return getInstance(conn,sql,id);
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select id,name,email,birth from customers";
        return getForList(conn,sql);
    }

    @Override
    public long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn,sql);//因为是泛型方法，会自动拆箱
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        return getValue(conn,sql);//因为是泛型方法，会自动拆箱
    }
}
