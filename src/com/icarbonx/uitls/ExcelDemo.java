package com.icarbonx.uitls;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelDemo {
	/*
	 * 导入excel文件，使用绝对路径
	 */
	public static List<ExcelInfo> importExcel(String file,int sheetIndex)
	{
		FileInputStream in=null;
		List<ExcelInfo> result=null;
		try {
			in=new FileInputStream(file);
			result=new ArrayList<ExcelInfo>();
			Workbook wb=new HSSFWorkbook(in);
			Sheet sheet=wb.getSheetAt(sheetIndex);
			for(Row row:sheet)
			{
				if(row.getRowNum()<1)
				{
					continue;
				}
				
				ExcelInfo eInfo=new ExcelInfo();
				eInfo.setIndex(row.getRowNum());
				eInfo.setFood(row.getCell(0).getStringCellValue());
				result.add(eInfo);
			}
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
		
	}
	
	/*
	 * 默认为第一张表格时
	 */
	public static List<ExcelInfo> importExcel(String file) throws IOException{
		return importExcel(file,0);
	}

}
