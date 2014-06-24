package com.springapp.mgr;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * User: Think
 * Date: 13-10-14
 * Time: 上午11:51
 */
public class ConnectionManager {
    private static ConnectionManager instance;

    public ComboPooledDataSource ds;
    private static String c3p0Properties = "../WEB-INF/c3p0.conf";

    private ConnectionManager() throws Exception {
//        Properties p = new Properties();
//        p.load(this.getClass().getResourceAsStream(c3p0Properties));
//        System.out.print(this.getClass());
        ds = new ComboPooledDataSource();
        ds.setUser("root");
        ds.setPassword("root");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/dudu");
        ds.setDriverClass("com.mysql.jdbc.Driver");
//        ds.setInitialPoolSize(Integer.parseInt(p.getProperty("initialPoolSize")));
//        ds.setMinPoolSize(Integer.parseInt(p.getProperty("minPoolSize")));
//        ds.setMaxPoolSize(Integer.parseInt(p.getProperty("maxPoolSize")));
//        ds.setMaxStatements(Integer.parseInt(p.getProperty("maxStatements")));
//        ds.setMaxIdleTime(Integer.parseInt(p.getProperty("maxIdleTime")));
    }

    public static final ConnectionManager getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public synchronized final Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void finalize() throws Throwable {
        DataSources.destroy(ds); // 关闭datasource
        super.finalize();
    }
}
