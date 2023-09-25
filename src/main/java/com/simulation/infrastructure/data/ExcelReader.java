package com.simulation.infrastructure.data;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.simulation.contracts.infrastructure.data.IExcelContext;

/**
 *
 * @author hakantek
 */
public class ExcelReader implements IExcelContext {

	
	public final String path;
	private int initialRow = 1;

    /**
	 * @param path
	 */
	public ExcelReader(String path) {
		this.path = path;
	}
	
	@Override
	public IExcelContext fromRowIndexOf(int initialRow) {
		this.initialRow = initialRow;
		return this;
	}
	
	
	@Override
    public List<Double> readDecimal(String sheetName, String header) {
    	
        int colIndexNumber = -1;
                
        /* excel column list */
        ArrayList<Double> column = new ArrayList<>();
        
        /* connection with excel workbook */
        XSSFWorkbook workbook = null;
        try {
        	FileInputStream stream = new FileInputStream(path);
            workbook = new XSSFWorkbook(stream);
            stream.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelReader.class.getName()).log(Level.SEVERE, null, ex);
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
    
	@Override
    public List<Integer> readInteger(String sheetName, String header) {
    	return readDecimal(sheetName, header)
    			.stream().map(Double::intValue)
                .toList();
    }
}