package ru.savimar.stafftime.vaadin;


import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import ru.savimar.stafftime.entity.Status;
import ru.savimar.stafftime.service.StatusService;

import java.time.LocalDateTime;


@Theme("mytheme")
@SpringUI
public class MainPage extends UI {



    private Button comeButton;
    private Button leaveButton;
    private Button workOutButton;



    @Autowired
    StatusService statusService;


    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        comeButton = new Button("Пришел");
        comeButton.addClickListener( event ->{
                Status status= new Status();
                status.setTime(LocalDateTime.now());
                status.setName("Пришел");
                statusService.save(status);

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
