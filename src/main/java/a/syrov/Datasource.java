package a.syrov;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum Datasource {

    INSTANCE;

    private MysqlDataSource mysqlDataSource;

    private Datasource() {
        mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("localhost");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setPassword("root");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setDatabaseName("warehouse");
    }


    public Connection getConnection()  throws SQLException {
        return mysqlDataSource.getConnection();
    }


}
