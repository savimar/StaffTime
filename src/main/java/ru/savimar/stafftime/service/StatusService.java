package ru.savimar.stafftime.service;

import ru.savimar.stafftime.entity.Status;

import java.util.List;

public interface StatusService {

    List<Status> findAll();
    Status findById(long id);
    Status save(Status status);
    void delete(long id);

}
