package com.kay.jdbctemplate;

import com.kay.dao.IAcountDao;
import com.kay.domain.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * JdbcTemplate 的最基本的用法
 */
public class JdbcTemplateDemo4 {
    public static void main(String[] args) {
     //1.获取容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.获取对象
        IAcountDao accountDao = ac.getBean("accountDao",IAcountDao.class);

        Account account = accountDao.findAccountById(2);
        System.out.println(account);
        account.setMoney(30000f);
        accountDao.updateAccount(account);
    }
}
