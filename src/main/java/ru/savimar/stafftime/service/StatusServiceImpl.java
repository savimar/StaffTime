package ru.savimar.stafftime.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ru.savimar.stafftime.entity.Status;
import ru.savimar.stafftime.repo.StatusRepo;

import java.util.List;

@Service("statusService")
@Repository
@Lazy
public class StatusServiceImpl implements StatusService {

    @Autowired
    StatusRepo statusRepo;
    @Override
    public List<Status> findAll() {
        return statusRepo.findAll();
    }

    @Override
    public Status findById(long id) {
        return statusRepo.findById(id);
    }

    @Override
    public Status save(Status status) {
        return statusRepo.save(status);
    }

    @Override
    public void delete(long id) {
        statusRepo.delete(id);

    }


}
