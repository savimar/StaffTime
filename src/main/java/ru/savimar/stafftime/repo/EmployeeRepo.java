package ru.savimar.stafftime.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.savimar.stafftime.entity.Employee;

import java.util.List;


@Transactional(readOnly = true)
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
