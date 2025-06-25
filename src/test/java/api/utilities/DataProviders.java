package api.utilities;


import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class DataProviders {

	public static String[][] readExcel() throws IOException {
		
		XSSFWorkbook wbook=new XSSFWorkbook("./testdata/studentData.xlsx");
		XSSFSheet sheet=wbook.getSheetAt(0); //index of the sheet
		
		//XSSFSheet sheet=wbook.getSheet("Sheet1"); //index of the sheet
		
		//Get row count in excel it Excludes header.
		int rowvalue = sheet.getLastRowNum();
		//System.out.println("Row count:"+rowvalue);
		
		
		//Get column count in excel
		int columncount = sheet.getRow(1).getLastCellNum();
		//System.out.println("Column count :"+columncount);
		
	//step1 Declare  string array
		String[][] data = new String[rowvalue][columncount];
	
	
		
		for (int i=1;i<=rowvalue;i++)
		{
			for (int j=0;j<columncount;j++)
			{
				String value = sheet.getRow(i).getCell(j).getStringCellValue();
				System.out.println(value);
				
	//step2  Read from excel and pass it to Data provider
				data[i-1][j]=value;
			}
		}
		wbook.close();
		//return the variable name.
		return data;

}

}

	

