package senla.com.connectJdbc.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import senla.com.connectJdbc.ConnectionHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionHolderImpl implements ConnectionHolder {

    private final DataSource dataSource;
    private final ThreadLocal<Connection> threadLocalConnection;

    @Autowired
    public ConnectionHolderImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        this.threadLocalConnection = ThreadLocal.withInitial(() -> {
            try {
                return dataSource.getConnection();
            } catch (SQLException e) {
                throw new RuntimeException("Failed to obtain a connection from the DataSource", e);
            }
        });
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (threadLocalConnection.get() == null || threadLocalConnection.get().isClosed()) {
            threadLocalConnection.set(dataSource.getConnection());
        }
        return threadLocalConnection.get();
    }

    @Override
    public void openTransaction() throws SQLException {
        Connection connection = getConnection();
        if (!connection.getAutoCommit()) {
            throw new IllegalStateException("Transaction already opened for this thread");
        }
        connection.setAutoCommit(false);
    }

    @Override
    public void commitTransaction() throws SQLException {
        Connection connection = getConnection();
        if (connection.getAutoCommit()) {
            throw new IllegalStateException("No transaction opened for this thread");
        }
        connection.commit();
        connection.setAutoCommit(true);
    }

    @Override
    public void rollbackTransaction() throws SQLException {
        Connection connection = getConnection();
        if (connection.getAutoCommit()) {
            throw new IllegalStateException("No transaction opened for this thread");
        }
        connection.rollback();
        connection.setAutoCommit(false);
    }

    @Override
    public void closeConnection() throws SQLException {
        Connection connection = threadLocalConnection.get();
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
        threadLocalConnection.remove();
    }
}
