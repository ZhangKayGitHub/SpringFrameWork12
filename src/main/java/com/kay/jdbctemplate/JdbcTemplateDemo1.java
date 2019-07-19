package com.kay.jdbctemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.DriverManager;

/**
 *
 * JdbcTemplate 的最基本的用法
 */
public class JdbcTemplateDemo1 {
    public static void main(String[] args) {
        //尊卑数据源，spring 的内部数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/eesy");
        ds.setUsername("root");
        ds.setPassword("123456");

        //1.创建JdbcTemplate对象
        JdbcTemplate jt = new JdbcTemplate();

        //给gt设置数据源
        jt.setDataSource(ds);
        //2.执行操作
        jt.execute("insert into account(name,money) values ('ddd',1000)");
    }
}
