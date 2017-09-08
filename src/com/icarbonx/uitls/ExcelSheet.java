package com.icarbonx.uitls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Excel 数据表对象
 * 
 * @author penghong
 *
 */
public class ExcelSheet {
	private String filepath;// 文件路径
	private int numerSheet;// excel表格位置
	private Workbook workbook;
	private Sheet sheet;

	static String value = null;
	static ArrayList<String> list = new ArrayList<String>();

	static ArrayList<String> values = new ArrayList<String>();

	public ExcelSheet(String filepath, int numberSheet) {
		this.filepath = filepath;
		this.numerSheet = numberSheet;
		File file = new File(filepath);

		try {
			if (filepath.endsWith(".xls")) {
				workbook = new HSSFWorkbook(new FileInputStream(file));

			} else if (filepath.endsWith(".xlsx")) {
				workbook = new XSSFWorkbook(new FileInputStream(file));
			} else
				System.out.println("不是excel文件！");
			sheet = (Sheet) workbook.getSheetAt(numberSheet);

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	
	


	/*
	 * 获取单元格的值
	 * 
	 * @param rowindex 行
	 * 
	 * @param rankindex 列
	 * 
	 * @return value 单元格的值
	 * 
	 * @throws Exception
	 */
	public String getsigdata(int rowindex, int rankindex) {
		Row row = null;
		Cell cell = null;
		int lastrow = sheet.getLastRowNum();
		if (rowindex <= lastrow) {
			row = sheet.getRow(rowindex);
			cell = row.getCell(rankindex);

		}
		return getValue(cell);

	}

	/*
	 * 获取列的所有值
	 * 
	 * @param rankindex 列索引
	 * 
	 * @return rankvalues 值列表
	 */
	public List<String> getrankvalue(int rankindex) {
		ArrayList<String> rankvalues = new ArrayList<String>();
		Iterator<Row> rowiterator = sheet.rowIterator();
		while (rowiterator.hasNext()) {
			Row row = rowiterator.next();
			Cell cell = row.getCell(rankindex);

			String value = getValue(cell);
			System.out.println("单元格的值:" + value);

			rankvalues.add(value);
		}

		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rankvalues;
	}

	/*
	 * 获取所有列的值,静态方法
	 */
	public static List<String> getrankvalue(String filepath, int numberSheet, int rankindex) {
		Workbook workbook = null;
		Sheet sheet = null;
		ArrayList<String> rankvalues = new ArrayList<String>();
		File file = new File(filepath);
		
		try {
		

		    workbook=getWorkBook(file);
			
			sheet = workbook.getSheetAt(numberSheet);
			

			Iterator<Row> rowiterator = sheet.rowIterator();
			rowiterator.next();
			while (rowiterator.hasNext()) {
				Row row = rowiterator.next();
				Cell cell = row.getCell(rankindex);

				String value = getValue(cell);
				rankvalues.add(value);
			

		} 
		}catch (Exception e) {

			e.printStackTrace();
		}
		finally {
			try {
				
				workbook.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		

		return rankvalues;
	}

	/*
	 * 获取行的所有值
	 * 
	 * @param rowindex 行数
	 * 
	 * @return rankvalues 行值列表
	 */
	public List<String> getrowvalue(int rowindex) {
		ArrayList<String> rowvalues = new ArrayList<String>();
		HSSFRow row = (HSSFRow) sheet.getRow(rowindex);
		Iterator<Cell> iterator = row.cellIterator();
		HSSFCell cell = null;
		while (iterator.hasNext()) {

			rowvalues.add(getValue(iterator.next()));
		}
		try {
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowvalues;
	}

	/*
	 * 写入值至单元格
	 * 
	 * @param rowindex 行数
	 * 
	 * @param rankindex 列数
	 * 
	 * @param writevalue 要写入的字符串
	 */
	public static void writeList(List<String> list,int sheetindex,int rankindex,String finalXlsxPath )
	{
		OutputStream out=null;
		try{
		 // 读取Excel文档  
        File finalXlsxFile = new File(finalXlsxPath);  
        Workbook workBook = getWorkBook(finalXlsxFile);  
        Sheet sheet=workBook.getSheetAt(sheetindex);
        
    
        
        //写数据
        for(int i=0;i<list.size();i++)
        {
        	//Row row=sheet.createRow(i+1);
        	Row row=sheet.getRow(i+1);
        	String value=list.get(i);
        	Cell cell=row.createCell(rankindex);
        	cell.setCellValue(value);
        }
        //创建文件输出流，否则不生效
        out=new FileOutputStream(finalXlsxFile);
        workBook.write(out);
        
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			try {
				if(out!=null)
				{
					out.flush();
					out.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	public static void writeSigleDate(String data,String finalXlsxPath,int sheetindex,int rowindex,int rankindex)
	{
		OutputStream out=null;
		try{
		 // 读取Excel文档  
        File finalXlsxFile = new File(finalXlsxPath);  
        Workbook workBook = getWorkBook(finalXlsxFile);  
        Sheet sheet=workBook.getSheetAt(sheetindex);
        Row row=sheet.getRow(rowindex);
        Cell cell=row.createCell(rankindex);
        cell.setCellValue(data);
        //创建文件输出流，否则不生效
        out=new FileOutputStream(finalXlsxFile);
        workBook.write(out);
        
        
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(out!=null)
				{
					out.flush();
					out.close();
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void writeListMap(List<List> list,int sheetindex,int rankindex,String finalXlsxPath)
	{

		OutputStream out=null;
		try{
			//读取excel文档，要写入先读取
			File f=new File(finalXlsxPath);
			Workbook workbook=getWorkBook(f);
			//获取工作表
			Sheet sheet=workbook.getSheetAt(sheetindex);
			//往excel中写数据
			for(int i=0;i<list.size();i++)
			{
				int rowstart=rankindex;
				//创建行
				Row row=sheet.createRow(i+1);
				//得到要插入的每一条记录
				List<String> sonlist=list.get(i);
				//在一行内循环
				for(int j=0;i<sonlist.size();j++)
				{   
					String sonvalue=sonlist.get(j);
					Cell cell=row.createCell(rowstart);
					cell.setCellValue(sonvalue);
					rowstart++;
				}
			}
			
			//创建文件输出流
			out=new FileOutputStream(f);
			workbook.write(out);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			if(out!=null)
			{
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
				
		}
		
		
		
	}
	
	
	
	public static Workbook getWorkBook(File f)
	{   
		 Workbook wb = null;  
		 try{
	        FileInputStream in = new FileInputStream(f);  
	        if(f.getName().endsWith("xls")){  //Excel 2003  
	            wb = new HSSFWorkbook(in);  
	        }else if(f.getName().endsWith("xlsx")){  // Excel 2007/2010  
	            wb = new XSSFWorkbook(in);  
	        }  
		 }
	 catch (Exception e) {
			// TODO: handle exception
		 e.printStackTrace();
		}
		
		return wb;
	}
	

	/**
	 * 获取行数
	 * 
	 * @return rows 总行数
	 */
	public int getrows() {

		int rows = sheet.getLastRowNum();
		return rows;
	}

	/*
	 * 格式化单元格数值
	 * 
	 * @param cell 单元格
	 */
	private static String getValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 把数字当成String来读，避免出现1读成1.0的情况
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;

	}

	public String getFilePath() {
		return filepath;
	}

	public void setFilePath(String filepath) {
		this.filepath = filepath;
	}

	public static void main(String args[]) {
		writeSigleDate("测试棒棒地柘城基", "F://food.xls", 0,1,2);
		
 
	}

}
