package ru.savimar.stafftime.vaadin;


import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import ru.savimar.stafftime.entity.Status;
import ru.savimar.stafftime.service.StatusService;

import java.sql.SQLException;
import java.time.LocalDateTime;


@Theme("mytheme")
@SpringUI
public class MainPage extends UI {



    private Button comeButton;
    private Button leaveButton;
    private Button workOutButton;




    StatusService statusService = new StatusService();


    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        comeButton = new Button("Пришел");
        comeButton.addClickListener( event ->{
            try {
                statusService.save("На работе");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        layout.addComponent(comeButton);

        leaveButton = new Button("Отсутсвует");
        comeButton.addClickListener(event->{
            try {
                statusService.save("");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        layout.addComponent(leaveButton);

        workOutButton = new Button("Отработал");
        comeButton.addClickListener(event ->{

        });
        layout.addComponent(workOutButton);
    }
}
