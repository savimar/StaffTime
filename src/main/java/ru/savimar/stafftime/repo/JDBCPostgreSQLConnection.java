package ru.savimar.stafftime.repo;


import org.apache.log4j.Logger;
import ru.savimar.stafftime.entity.Status;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JDBCPostgreSQLConnection {

    private static final String DB_DRIVER = "org.postgresql.Driver";
    private static final String DB_CONNECTION = "jdbc:postgresql://localhost:5432/stafftime";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    private static final Logger LOG = Logger.getLogger(JDBCPostgreSQLConnection.class);


    private Connection getDBConnection() {

        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);

        } catch (ClassNotFoundException e) {
            LOG.error("Failed to get the driver to connect to the database", e);
        }

        try {
            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;

        } catch (SQLException e) {
            LOG.error("Error connecting to database", e);
        }

        return dbConnection;

    }

    private static java.sql.Timestamp getCurrentTimeStamp() {

        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());

    }

    public void insertRecordIntoTable(String name) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO STATUS"
                + "(NAME, TIME) VALUES"
                + "(?,?)";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
            preparedStatement.setString(1, name);
            preparedStatement.setTimestamp(2, getCurrentTimeStamp());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            LOG.error("Error writing to database", e);
        }

    }


    public  List<Status> getAll() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        List<Status> list = new ArrayList<>();

        String selectTableSQL = "SELECT * FROM status where time >= ? and time <= ?";


        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectTableSQL);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(LocalDate.now().atStartOfDay()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(LocalDate.now().atTime(23, 59, 59)));

            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                Status status = new Status();

                status.setId(result.getLong("id"));
                status.setName(result.getString("name"));
                status.setTime(result.getTimestamp("time").toLocalDateTime());

                list.add(status);
            }

        } catch (SQLException e) {
            LOG.error("Error getting data from the database", e);

        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        return list;
    }
}


