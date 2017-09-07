package ru.savimar.stafftime.service;

import ru.savimar.stafftime.entity.Status;
import ru.savimar.stafftime.repo.StatusRepo;

import java.sql.SQLException;
import java.util.List;

public class StatusService {


    StatusRepo statusRepo = new StatusRepo();

    public List<Status> findAll() {
        return statusRepo.findAll();
    }


    public Status findById(long id) {
        return statusRepo.findById(id);
    }


    public void save(String name) throws SQLException {
       statusRepo.save(name);
    }


    public void delete(long id) {
        statusRepo.delete(id);

    }


}
