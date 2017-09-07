package ru.savimar.stafftime.repo;

import ru.savimar.stafftime.entity.Status;

import java.sql.SQLException;
import java.util.List;


public class StatusRepo {

   private  JDBCPostgreSQLConnection connection = new JDBCPostgreSQLConnection();


    public void save(String name) throws SQLException {
        connection.insertRecordIntoTable(name);
    }

    public List<Status> findAll() throws SQLException {
        return connection.getAll();
    }

}
