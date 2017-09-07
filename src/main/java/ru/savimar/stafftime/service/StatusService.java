package ru.savimar.stafftime.service;

import ru.savimar.stafftime.entity.Status;
import ru.savimar.stafftime.repo.StatusRepo;

import java.sql.SQLException;
import java.util.List;

public class StatusService {


    StatusRepo statusRepo = new StatusRepo();

    public List<Status> findAll() throws SQLException {
        return statusRepo.findAll();
    }



    public void save(String name) throws SQLException {
       statusRepo.save(name);
    }



}
