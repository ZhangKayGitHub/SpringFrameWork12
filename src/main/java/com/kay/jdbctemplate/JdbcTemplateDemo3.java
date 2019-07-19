package com.kay.jdbctemplate;

import com.kay.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * JdbcTemplate 的CUD操作
 */
public class JdbcTemplateDemo3 {
    public static void main(String[] args) {

        //获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //获取对象
        JdbcTemplate jt = ac.getBean("jdbcTemplate",JdbcTemplate.class);
        //执行操作
        //保存
       // jt.update("insert into account(name, money) values (?,?)","eee",3333f);
        //更新
        //jt.update("update account set name=?, money=? where id=?","test",4567,7);
        //删除
        //jt.update("delete from account where id=?",7);
        //
        //List<Account> accounts =  jt.query("select * from account where money > ?" ,new AccountRowMapper(),500f);
//        List<Account> accounts =  jt.query("select * from account where money > ?" ,new BeanPropertyRowMapper<Account>(Account.class),1000f);
//        for (Account account : accounts){
//            System.out.println(account);
//        }
        //查询一个
//        List<Account> accounts =  jt.query("select * from account where id=?" ,new BeanPropertyRowMapper<Account>(Account.class),1);
//        System.out.println(accounts.isEmpty()?"没有内容":accounts.get(0));
        //查询返回一行一列（使用聚合函数，但不加group by 子句）
        Long count = jt.queryForObject("select count(*) from account where money > ?",Long.class,500f);
        System.out.println(count);
    }
}

/**
 * 定义Account 的封装策略
 */
class AccountRowMapper implements RowMapper<Account>{
    /**
     * 把结果级中的数据封装到Account中，然后有spring把每个Account加载到集合中
     * @param resultSet
     * @param i
     * @return
     * @throws SQLException
     */
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}