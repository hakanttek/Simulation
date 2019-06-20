/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simulation.data.importing;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author hakantek
 */
public class ExcelColumn {

    public static ArrayList<Double> Decimal(String fileLocation, String workbookName, String sheetName, int colIndexNumber, int initialRow, boolean header) {
        
        fileLocation = fileLocation + "\\" + workbookName + ".xlsx";
                
        /* excel column list */
        ArrayList<Double> column = new ArrayList<>();
        
        /* connection with excel workbook */
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
        } catch (IOException ex) {
            Logger.getLogger(ExcelColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /* connection with sheet of workbook */
        XSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        
        /* if there is a header increase row by 1 */
        initialRow += (header)?1:0;
        
        /* return data */
        while(true){
            try {
                column.add(sheet.getRow(++initialRow-1).getCell(colIndexNumber-1).getNumericCellValue());
            } 
            catch (Exception e) {
                break;
            }
        }

        return column;
    }
    public static ArrayList<Double> Decimal(String fileLocation, String workbookName, String sheetName, int colIndexNumber, int initialRow){
        return Decimal(fileLocation, workbookName, sheetName, colIndexNumber, initialRow, false);
    }
    public static ArrayList<Double> Decimal(String fileLocation, String workbookName, String sheetName, int colIndexNumber){
        return Decimal(fileLocation, workbookName, sheetName, colIndexNumber, 1);
    }
    public static ArrayList<Double> Decimal(String fileLocation, String workbookName, String sheetName, String header, int initialRow) {
        
        fileLocation = fileLocation + "\\" + workbookName + ".xlsx";

        int colIndexNumber = -1;
                
        /* excel column list */
        ArrayList<Double> column = new ArrayList<>();
        
        /* connection with excel workbook */
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
        } catch (IOException ex) {
            Logger.getLogger(ExcelColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /* connection with sheet of workbook */
        XSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        
        /* determinate colIndexNumber */
        for(int i=1; i<=100; i++){
            try {
                if(header.equals(sheet.getRow(initialRow-1).getCell(i-1).getStringCellValue())){
                    colIndexNumber = i;
                    break;
                }
            } catch (Exception e) {}
        }

        /* return data */
        while(true){
            try {
                column.add(sheet.getRow(++initialRow-1).getCell(colIndexNumber-1).getNumericCellValue());
            } 
            catch (Exception e) {
                break;
            }
        }

        return column;
    }
    public static ArrayList<Double> Decimal(String fileLocation, String workbookName, String sheetName, String header){
        return Decimal(fileLocation, workbookName, sheetName, header, 1);
    }
    ////////////////////////
    public static ArrayList<Integer> Integer(String fileLocation, String workbookName, String sheetName, int colIndexNumber, int initialRow, boolean header) {
        
        fileLocation = fileLocation + "\\" + workbookName + ".xlsx";
                
        /* excel column list */
        ArrayList<Integer> column = new ArrayList<>();
        
        /* connection with excel workbook */
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
        } catch (IOException ex) {
            Logger.getLogger(ExcelColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /* connection with sheet of workbook */
        XSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        
        /* if there is a header increase row by 1 */
        initialRow += (header)?1:0;
        
        /* return data */
        while(true){
            try {
                column.add((int)sheet.getRow(++initialRow-1).getCell(colIndexNumber-1).getNumericCellValue());
            } 
            catch (Exception e) {
                break;
            }
        }

        return column;
    }
    public static ArrayList<Integer> Integer(String fileLocation, String workbookName, String sheetName, int colIndexNumber, int initialRow){
        return Integer(fileLocation, workbookName, sheetName, colIndexNumber, initialRow, false);
    }
    public static ArrayList<Integer> Integer(String fileLocation, String workbookName, String sheetName, int colIndexNumber){
        return Integer(fileLocation, workbookName, sheetName, colIndexNumber, 1);
    }
    public static ArrayList<Integer> Integer(String fileLocation, String workbookName, String sheetName, String header, int initialRow) {
        
        fileLocation = fileLocation + "\\" + workbookName + ".xlsx";

        int colIndexNumber = -1;
                
        /* excel column list */
        ArrayList<Integer> column = new ArrayList<>();
        
        /* connection with excel workbook */
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
        } catch (IOException ex) {
            Logger.getLogger(ExcelColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /* connection with sheet of workbook */
        XSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        
        /* determinate colIndexNumber */
        for(int i=1; i<=100; i++){
            try {
                if(header.equals(sheet.getRow(initialRow-1).getCell(i-1).getStringCellValue())){
                    colIndexNumber = i;
                    break;
                }
            } catch (Exception e) {}
        }

        /* return data */
        while(true){
            try {
                column.add((int)sheet.getRow(++initialRow-1).getCell(colIndexNumber-1).getNumericCellValue());
            } 
            catch (Exception e) {
                break;
            }
        }

        return column;
    }
    public static ArrayList<Integer> Integer(String fileLocation, String workbookName, String sheetName, String header){
        return Integer(fileLocation, workbookName, sheetName, header, 1);
    }
}
