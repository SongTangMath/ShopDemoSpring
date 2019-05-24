package com.zkdx;

import java.util.*;
import java.sql.*;
import java.io.*;
import org.apache.commons.dbcp2.BasicDataSource;

public class JdbcUtil {
    private static BasicDataSource dataSource = null;
    static {
        dataSource = new BasicDataSource();
        Properties prop = new Properties();
        try {
            prop.load(JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            String url = prop.getProperty("jdbc.url");
            String password = prop.getProperty("jdbc.password");
            String username = prop.getProperty("jdbc.username");
            String initialSizeStr = prop.getProperty("jdbc.initialSize");
            String driverClassName = prop.getProperty("jdbc.driverClassName");
            int initialSize = Integer.parseInt(initialSizeStr);
            String maxTotalStr = prop.getProperty("jdbc.maxTotal");
            int maxTotal = Integer.parseInt(maxTotalStr);
            String maxWaitMillisStr = prop.getProperty("jdbc.maxWaitMillis");
            int maxWaitMillis = Integer.parseInt(maxWaitMillisStr);

            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);
            dataSource.setInitialSize(initialSize);
            dataSource.setMaxTotal(maxTotal);
            dataSource.setMaxWaitMillis(maxWaitMillis);
            dataSource.setDriverClassName(driverClassName);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }

    public static void setDataSource(BasicDataSource dataSource) {
        JdbcUtil.dataSource = dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        System.out.println(getConnection());
       
    }

}
