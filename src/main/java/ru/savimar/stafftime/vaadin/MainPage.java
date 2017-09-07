package ru.savimar.stafftime.vaadin;


import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.savimar.stafftime.entity.Status;
import ru.savimar.stafftime.service.StatusService;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.logging.Logger;


@Theme("mytheme")
@SpringUI
public class MainPage extends UI {

    private static final Logger LOG = Logger.getLogger(MainPage.class.toString());

    StatusService statusService = new StatusService();

    @Override
    public void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();
        setContent(layout);

        Button comeButton = new Button("Пришел");
        comeButton.addClickListener(event -> {
            try {
                statusService.save("На работе");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        });
        layout.addComponent(comeButton);

        Button leaveButton = new Button("Ушел");
        leaveButton.addClickListener(event -> {
            try {
                statusService.save("Отсутсвует");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        layout.addComponent(leaveButton);

        Button workOutButton = new Button("Отработал");
        workOutButton.addClickListener(event -> {
            List<Status> list = null;
            try {
                list = statusService.findAll();

                long minutes = 0;

                for (int i = 1; i < list.size(); i++) {
                    Status status = list.get(i);

                        Status previousStatus = list.get(i - 1);

                        if (status.getName().equals("Отсутсвует") && previousStatus.getName().equals("На работе")) {
                            minutes += ChronoUnit.MINUTES.between(previousStatus.getTime(), status.getTime());
                        }
                    }

                    URI sourceFileURL = new URI("file:///C:/myFiles/staff_time.xlsx");
                    File sourceFile = new File(sourceFileURL);
                    writeIntoExcel(sourceFile, minutes);


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        layout.addComponent(workOutButton);
    }

    public void writeIntoExcel(File file, long minutes) throws FileNotFoundException, IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet();

        XSSFRow row1 = sheet.createRow(2);

        Cell label = row1.createCell(0);
        label.setCellValue("Вы отработали сегодня ");

        XSSFRow row2 = sheet.createRow(5);


        Cell string = row2.createCell(0);
        string.setCellValue(getHoursAngMinutes(minutes));

        sheet.autoSizeColumn(1);

        // Записываем всё в файл
        book.write(new FileOutputStream(file));
        book.close();
    }

    private String getHoursAngMinutes(long minutes){
        if(minutes>59){
            long hours = minutes/60;
            minutes = minutes%60;
            return ""+ hours +" часов и "+ minutes + " минут";
        }
        else {
            return ""+minutes+" минут";
        }
    }
}
