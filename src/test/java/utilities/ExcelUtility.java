package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	FileInputStream fi;
	FileOutputStream fo;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFRow rows;
	XSSFCell cells;
	XSSFCellStyle style;
	String path;

	public ExcelUtility(String path) {
		// TODO Auto-generated constructor stub
		this.path = path;
	}

	public int getCountRow(String sheetName) throws IOException {
		// TODO Auto-generated method stub
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		int countRow = sheet.getLastRowNum();
		wb.close();
		fi.close();
		return countRow;

	}

	public int getCountCell(String sheetName, int rownum) throws IOException {
		// TODO Auto-generated method stub
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		int countCell = sheet.getRow(rownum).getLastCellNum();
		wb.close();
		fi.close();
		return countCell;
	}

	public String getCellData(String sheetName, int rownnum, int colnum) throws IOException {
		// TODO Auto-generated method stub
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		String data;
		rows = sheet.getRow(rownnum);
		cells = rows.getCell(colnum);
		DataFormatter dataFormatter = new DataFormatter();
		try {
			data = dataFormatter.formatCellValue(cells);

		} catch (Exception e) {
			// TODO: handle exception
			data = "";
		}
		wb.close();
		fi.close();

		return data;
	}

	public void setCellData(String sheetName, int rownum, int colnum, String data ) throws IOException {
		// TODO Auto-generated method stub
		File exfile = new File(path);
		
		if (!exfile.exists()) { //if file non exist then create a new file
			wb = new XSSFWorkbook();
			fo = new FileOutputStream(path);
			wb.write(fo);
		}
		
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		
		if (wb.getSheetIndex(sheetName)== -1) {//if sheet don't exist then create a new sheet
			sheet = wb.createSheet(sheetName);
			sheet = wb.getSheet(sheetName);
		}
		
		if (sheet.getRow(rownum) == null) { //if row don't exist then create rownnum
			rows = sheet.createRow(rownum);
			
			rows = sheet.getRow(rownum);
			cells = rows.createCell(colnum);
			cells.setCellValue(data);
			fo = new FileOutputStream(path);
			wb.write(fo);
			wb.close();
			fi.close();fo.close();
			
		}

	}

	public void fillGreenColor(String sheetName, int rownum, int colnum) throws IOException {
		// TODO Auto-generated method stub
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		rows = sheet.getRow(rownum);
		cells = rows.getCell(colnum);
		style = wb.createCellStyle();
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cells.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();

	}

	public void fillRedColor(String sheetName, int rownum, int colnum) throws IOException {
		// TODO Auto-generated method stub
		fi = new FileInputStream(path);
		wb = new XSSFWorkbook(fi);
		sheet = wb.getSheet(sheetName);
		rows = sheet.getRow(rownum);
		cells = rows.getCell(colnum);
		style = wb.createCellStyle();
		
		style.setFillBackgroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cells.setCellStyle(style);
		fo = new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
		

	}
}
