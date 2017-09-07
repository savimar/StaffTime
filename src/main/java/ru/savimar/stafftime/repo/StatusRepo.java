package ru.savimar.stafftime.repo;

import ru.savimar.stafftime.entity.Status;


import java.sql.SQLException;
import java.util.List;

import static ru.savimar.stafftime.repo.JDBCPostgreSQLConnection.*;


public class StatusRepo {

    JDBCPostgreSQLConnection connection = new JDBCPostgreSQLConnection();

    public int delete(long id) {
        return 0;
    }


    public void save(String name) throws SQLException {

        connection.insertRecordIntoTable(name);

    }


    public Status findById(long id) {
        return null;
    }


    public List<Status> findAll() {

return null;
    }

}
