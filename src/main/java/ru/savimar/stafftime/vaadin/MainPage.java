package ru.savimar.stafftime.vaadin;


import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.savimar.stafftime.entity.Status;
import ru.savimar.stafftime.service.StatusService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;


@Theme("mytheme")
@SpringUI
public class MainPage extends UI {

    private static final Logger LOG = Logger.getLogger(MainPage.class);

    private StatusService statusService = new StatusService();

    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        Button comeButton = new Button("Пришел");
        comeButton.addClickListener(event -> {
            try {
                statusService.save("На работе");
            } catch (SQLException e) {
                LOG.error("Error writing to database, status \"На работе\"", e);

            }

        });
        layout.addComponent(comeButton);

        Button leaveButton = new Button("Ушел");
        leaveButton.addClickListener(event -> {
            try {
                statusService.save("Отсутсвует");
            } catch (SQLException e) {
                LOG.error("Error writing to database, status  \"Отсутсвует\"", e);
            }
        });
        layout.addComponent(leaveButton);

        Button workOutButton = new Button("Отработал");
        workOutButton.addClickListener(event -> {
            try {
                statusService.save("На работе");
            } catch (SQLException e) {
                LOG.error("Error writing to database, status \"На работе\"", e);
            }
            List<Status> list;
            try {
                list = statusService.findAll();
                long minutes = 0;

                for (int i = 1; i < list.size(); i++) {
                    Status status = list.get(i);
                    Status previousStatus = list.get(i - 1);

                    if (status.getName().equals("Отсутсвует") && previousStatus.getName().equals("На работе")) {
                        minutes += ChronoUnit.MINUTES.between(previousStatus.getTime(), status.getTime());
                    } else if (status.getName().equals("На работе") && previousStatus.getName().equals("На работе")) {//?workOutButton
                        minutes += ChronoUnit.MINUTES.between(previousStatus.getTime(), status.getTime());
                    }
                }

                URI sourceFileURL = new URI("file:///C:/myFiles/staff_time.xlsx");
                File sourceFile = new File(sourceFileURL);
                ExcelParser parser = new ExcelParser();
                parser.writeIntoExcel(sourceFile, minutes);


            } catch (SQLException e) {
                LOG.error("Error getting data from the database", e);
            } catch (FileNotFoundException e) {
                LOG.error("The file for writing was not found", e);
            } catch (URISyntaxException e) {
                LOG.error("Syntax error URI", e);
            } catch (IOException e) {
                LOG.error("Error writing to file", e);
            }

        });
        layout.addComponent(workOutButton);
    }


}
