package com.learnautomation.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	static XSSFWorkbook wb;
	
	public static Object[][] getDataFromSheet(String sheetName)
	{
		System.out.println("LOG:INFO - Excel Data Getting Ready");
		
		Object [][]arr=null;
		
		try 
		{
			wb=new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"/TestData/Data.xlsx")));
			
			int row=getRows(sheetName);
			
			int column=getColumns(sheetName);
			
			arr=new Object[row-1][column];
			
			for(int i=1;i<row;i++)
			{
				
				for(int j=0;j<column;j++)
				{
					arr[i-1][j]=getData(sheetName, i, j);		  	
				}
				
			}
			

		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("Could not find the file "+e.getMessage());
		} 
		catch (IOException e) 
		{
			System.out.println("Could not load the file "+e.getMessage());
		}
		
		System.out.println("LOG:INFO - Excel Data Ready");
		
		return arr;
	}
	
	public static int getRows(String sheetName)
	{
		return wb.getSheet(sheetName).getPhysicalNumberOfRows();
	}
	
	public static int getColumns(String sheetName)
	{
		return wb.getSheet(sheetName).getRow(0).getPhysicalNumberOfCells();
	}
	
	public static String getData(String sheetName,int row,int column)
	{
		XSSFCell cell=wb.getSheet(sheetName).getRow(row).getCell(column);
		
		String data="";
		
		if(cell.getCellType()==CellType.NUMERIC)
		{
		 data=String.valueOf(cell.getNumericCellValue()); 
		}
		else if(cell.getCellType()==CellType.STRING)
		{
			data=cell.getStringCellValue();
		}
		else if(cell.getCellType()==CellType.BLANK)
		{
			data="";
		}
		
		return data;
	}
	
}
