package ru.savimar.stafftime.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.savimar.stafftime.entity.Status;

import java.util.List;


@Transactional(readOnly = true)
public interface StatusRepo extends JpaRepository<Status, Long> {
    @Transactional
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Status save(Status status);


    Status findById(Integer id);

    @Override
    List<Status> findAll();

}
