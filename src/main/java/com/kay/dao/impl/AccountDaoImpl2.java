package com.kay.dao.impl;

import com.kay.dao.IAcountDao;
import com.kay.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户的持久层实现类
 */
//以后是有注解开发可以在当前类的前面添加Repository注解,该类的JdbcTemplate属性添加按自动类型注入的注解Autowired
//在AccountDaoImpl中由于AccountDaoImpl类继承了JdbcDaoSupport类，JdbcDaoSupport类是jar包中的类，其中属性jdbcTemplate是只读属性
//因此无法采用采用自动注入的方式进行注入，因此该类（也就是自己写的）可以基于注解和xml文件进行配置，
// 但是，继承Spring定义的JdbcDaoSupport类的类（也就是AccountDaoImpl类）再想利用注解进行配置就会变的比较麻烦了
@Repository
public class AccountDaoImpl2 implements IAcountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Account findAccountById(Integer accountId) {
        List<Account> accounts = jdbcTemplate.query("select * from account where id=?",new BeanPropertyRowMapper<Account>(Account.class),accountId);
        return accounts.isEmpty()? null : accounts.get(0);
    }

    public Account findAccountByName(String name) {
        List<Account> accounts = jdbcTemplate.query("select * from account where name=?",new BeanPropertyRowMapper<Account>(Account.class),name);
        if(accounts.isEmpty()){
            return null;
        }
        if(accounts.size() > 1){
            throw new RuntimeException("结果集不唯一");
        }
        return accounts.get(0);
    }

    public void updateAccount(Account account) {
        jdbcTemplate.update("update account set name=?,money=? where id=?",account.getName(),account.getMoney(),account.getId());
    }
}
