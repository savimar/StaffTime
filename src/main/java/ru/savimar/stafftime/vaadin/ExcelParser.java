package ru.savimar.stafftime.vaadin;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelParser {

    public void writeIntoExcel(File file, long minutes) throws IOException {
        XSSFWorkbook book = new XSSFWorkbook();
        XSSFSheet sheet = book.createSheet();

        XSSFRow row1 = sheet.createRow(2);
        Cell label = row1.createCell(0);
        label.setCellValue("Вы отработали сегодня: ");

        XSSFRow row2 = sheet.createRow(5);
        Cell string = row2.createCell(0);
        string.setCellValue(getHoursAngMinutes(minutes));

        sheet.autoSizeColumn(1);

        book.write(new FileOutputStream(file));
        book.close();
    }

    private String getHoursAngMinutes(long minutes) {
        if (minutes > 59) {
            long hours = minutes / 60;
            minutes = minutes % 60;
            return "" + hours + " часов и " + minutes + " минут";
        } else {
            return "" + minutes + " минут";
        }
    }
}
