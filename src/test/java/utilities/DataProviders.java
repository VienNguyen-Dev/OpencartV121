package utilities;

import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;


public class DataProviders {
	@DataProvider(name="LoginData")
	Object[][] getData() throws IOException {
		// TODO Auto-generated method stub
		String path = System.getProperty("user.dir")+ "\\testData\\OpenCart_DataTest.xlsx";
		ExcelUtility elUtil = new ExcelUtility(path);
		int totalRows = elUtil.getCountRow("Sheet1");
		int totalCells = elUtil.getCountCell("Sheet1", 1);
		
		String loginData[][] = new String[totalRows][totalCells];
		for (int row = 1; row <= totalRows; row++) {
			for (int cell = 0; cell < totalCells; cell++) {
				loginData [row-1][cell]= elUtil.getCellData("Sheet1", row, cell);
			}
		}
		return loginData;

	}

}
