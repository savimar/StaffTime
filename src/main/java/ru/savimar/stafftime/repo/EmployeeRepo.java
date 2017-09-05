package ru.savimar.stafftime.repo;

import ru.savimar.stafftime.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Transactional
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Employee save(Employee employee);


    Employee findById(Integer id);

    @Override
    List<Employee> findAll();

    Employee getByName(String name);

}
