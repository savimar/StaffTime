package ru.savimar.stafftime.vaadin;


import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import org.springframework.util.StringUtils;

public class MainPage extends UI {

    private TextField employeeTextField;

    private Button comeButton;
    private Button leaveButton;
    private Button workOutButton;


    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        employeeTextField = new TextField("Введите свое имя");
        layout.addComponent(employeeTextField);

        comeButton = new Button("Пришел");
        comeButton.addClickListener( event ->{
            if (StringUtils.isEmpty(employeeTextField)){

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
