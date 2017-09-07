package ru.savimar.stafftime.vaadin;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.junit.Test;
import ru.savimar.stafftime.entity.Status;

import java.io.File;
import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

public class ExcelParserTest {

    @Test
    public void writeIntoExcel() throws Exception {
        try {
            ExcelParser parser = new ExcelParser();
            File writeFile = new File("C:\\myFiles\\excel_test.xlsx");
            parser.writeIntoExcel(writeFile, 58);

            String result = "";
            FileInputStream readFile = new FileInputStream(new File("C:\\myFiles\\excel_test.xlsx"));
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
        catch (Exception e){
            e.printStackTrace();
            Assert.fail();
        }


    }

}