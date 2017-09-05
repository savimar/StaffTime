package ru.savimar.stafftime.vaadin;


import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import ru.savimar.stafftime.entity.Employee;
import ru.savimar.stafftime.entity.Status;
import ru.savimar.stafftime.repo.EmployeeRepo;
import ru.savimar.stafftime.repo.StatusRepo;

import java.time.LocalDateTime;
import java.util.List;


@Theme("mytheme")
@SpringUI
public class MainPage extends UI {

    private TextField employeeTextField;

    private Button comeButton;
    private Button leaveButton;
    private Button workOutButton;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    StatusRepo statusRepo;


    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        employeeTextField = new TextField("Введите свое имя");
        layout.addComponent(employeeTextField);

        comeButton = new Button("Пришел");
        comeButton.addClickListener( event ->{
            if (!StringUtils.isEmpty(employeeTextField)){
                List<Employee> employeeList = employeeRepo.findAll();
                Employee employee = null;

                if(employeeList != null && employeeList.contains(employeeRepo.getByName(employeeTextField.getValue()))){
                   employee = employeeRepo.getByName(employeeTextField.getValue());
                }
                else {
                    employee= employeeRepo.save(new Employee(employeeTextField.getValue()));
                }
                Status status= new Status();
                status.setTime(LocalDateTime.now());
                status.setName("Пришел");
                status = statusRepo.save(status);
                employee.setStatus(status);
                employeeRepo.save(employee);

            }

        });
        layout.addComponent(comeButton);

        leaveButton = new Button("Ушел");
        comeButton.addClickListener(event->{

        });
        layout.addComponent(leaveButton);

        workOutButton = new Button("Отработал");
        comeButton.addClickListener(event ->{

        });
        layout.addComponent(workOutButton);
    }
}
