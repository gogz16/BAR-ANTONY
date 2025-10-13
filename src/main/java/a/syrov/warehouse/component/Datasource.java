package a.syrov.warehouse.component;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum Datasource {

    INSTANCE;

    private MysqlDataSource mysqlDataSource;

    private final int MAX_CONNECTIONS = 10;
    private List<Connection> poolConnections = new ArrayList<>(MAX_CONNECTIONS);
    private List<Connection> activeConnections = new ArrayList<>(MAX_CONNECTIONS);

    private Datasource() {
        mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("localhost");
        mysqlDataSource.setPort(3306);
        mysqlDataSource.setPassword("root");
        mysqlDataSource.setUser("root");
        mysqlDataSource.setDatabaseName("warehouse");

        for (int i = 0; i < MAX_CONNECTIONS; i++) {
            try {
                poolConnections.add(mysqlDataSource.getConnection());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public Connection getConnection()  throws SQLException {
        if (poolConnections.isEmpty()) {
            throw new SQLException("No more connections available");
        }
        Connection connection = poolConnections.remove(poolConnections.size() - 1);
        activeConnections.add(connection);
        System.out.println("Active connections: " + activeConnections.size());
        return connection;
    }

    public void releaseConnection(Connection connection) {
        activeConnections.remove(connection);
        poolConnections.add(connection);
        System.out.println("Pool connections: " + poolConnections.size());
    }


}
