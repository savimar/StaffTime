package ru.savimar.stafftime.vaadin;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

public class ExcelParserTest {

    @Test
    public void writeIntoExcel() throws Exception {
        try {
            ExcelParser parser = new ExcelParser();
            String testFileName = "excel_test.xlsx";
            URL sourceFileURL = getClass().getClassLoader().getResource(testFileName);
            if(sourceFileURL != null) {
                File writeFile = new File(sourceFileURL.toURI());
                parser.writeIntoExcel(writeFile, 58);

                String result = "";
                File sourceFile = new File(sourceFileURL.toURI());
                FileInputStream readFile = new FileInputStream(sourceFile);
                XSSFWorkbook workbook = new XSSFWorkbook(readFile);
                XSSFSheet sheet = workbook.getSheetAt(0);

                Iterator<Row> rowIterator = sheet.iterator();
                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();
                    Iterator<Cell> cellIterator = row.cellIterator();

                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        result += cell.getStringCellValue();
                    }
                }
                assertEquals(result, "Вы отработали сегодня: 58 минут");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }


    }

}