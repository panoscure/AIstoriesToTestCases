package com.intralot.qa.automation.utilities;

import com.intralot.qa.automation.core.utilities.CustomProperties;
import com.intralot.qa.automation.core.utilities.Log;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadXLS {

    public ArrayList<String> tickets = new ArrayList<String>();

    Jira jira = new Jira();


    public static void updateXlsx(String story, String test_cases, int startRowIndex) {
        try {
            FileInputStream fis = new FileInputStream(new File(CustomProperties.getPropertyValue("path.xls")));
            XSSFWorkbook workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Sheet1");

            Log.info("Story: " + story + " - " + "Test Cases ID: " + test_cases);

            // Iterate through rows starting from the specified index
            //for (int i = startRowIndex; i <= sheet.getLastRowNum(); i++) {
                XSSFRow row = sheet.getRow(startRowIndex);
                if (row != null) {
                    // Create cells for story and test_cases
                    XSSFCell storyCell = row.createCell(2); // Column 3 (0-indexed)
                    storyCell.setCellValue(story);

                    XSSFCell testCasesCell = row.createCell(3); // Column 4 (0-indexed)
                    testCasesCell.setCellValue(test_cases);
                }
            //}

            // Close the FileInputStream
            fis.close();

            // Write the workbook to the file system
            try (FileOutputStream outputStream = new FileOutputStream(CustomProperties.getPropertyValue("path.xls"))) {
                workbook.write(outputStream);
                Log.info("Excel is updated successfully");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Close the workbook
            workbook.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readAndStoreIssuesFromXLS() throws IOException, UnirestException {
        FileInputStream file = new FileInputStream(CustomProperties.getPropertyValue("path.xls"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheet("Sheet1");
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            // Get the first cell in the row
            Cell firstCell = row.getCell(0);
            // Process the cell value only if it's not null
            if (firstCell != null) {
                // Process the cell value
                setJiraTicket(String.valueOf(firstCell));
            }
        }

        workbook.close();
        file.close();
        Log.info("Go to description");
        //getJiraTicketDescription();
  }


    public String getTicketDescription(int index){
        String description;
        description = jira.getJiraTicketDescription(index);
        return description;
    }

    public void setJiraTicket(String ticket)
    {
        tickets.add(ticket);
    }
    public ArrayList<String> getJiraTickets()
    {
        return tickets;
    }
    public String getJiraTicketFromXls(int index)
    {
        return tickets.get(index);
    }




}

