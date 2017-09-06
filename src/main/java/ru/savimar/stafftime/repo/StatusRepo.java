package ru.savimar.stafftime.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.savimar.stafftime.entity.Status;

import java.util.List;



public interface StatusRepo extends CrudRepository<Status, Long> {

    int delete(@Param("id") long id);


     Status save(Status status);


    Status findById(long id);


    List<Status> findAll();



}
