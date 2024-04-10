package org.example;

import org.flywaydb.core.Flyway;
import props.PropertyReader;

import java.sql.*;

public class PostgresConnection {
    private static final PostgresConnection INSTANCE = new PostgresConnection();

    private Connection PostgresConnection;
    private PostgresConnection() {

        try {
            String postgresConnectionUrl = PropertyReader.getConnectionUrlForPostgres();
            String username = PropertyReader.getUserForPostgres();
            String password = PropertyReader.getPasswordForPostgres();
            this.PostgresConnection = DriverManager.getConnection(postgresConnectionUrl, username, password);
            flywayMigration(postgresConnectionUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("Create connection exception");
        }
    }

    public static PostgresConnection getInstance() {

        return INSTANCE;
    }

    public Connection getPostgresConnection() {

        return PostgresConnection;
    }

    public int executeUpdate(String query) {
        try(Statement statement = INSTANCE.getPostgresConnection().createStatement()) {
            return statement.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            PostgresConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void flywayMigration(String connectionUrl, String username, String password) {
        Flyway flyway = Flyway.configure().dataSource(connectionUrl, username, password).schemas("public").load();
        flyway.migrate();
    }
}

