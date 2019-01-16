package cn.itsource.pss;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class TestExcel {
	
	@Test
	public void testHello() throws IOException{
		//创建一个核心对象
		SXSSFWorkbook sw = new SXSSFWorkbook();//它默认是支持100条数据放到缓存中，超过了100条之后，它会把前面的几条同步到磁盘文件中
		//创建一个sheet表
		Sheet sheet = sw.createSheet("excel的demo");
		//创建行，它的索引是从0开始的
		Row row = sheet.createRow(0); //创建第一行
		//创建单元格
		Cell cell = row.createCell(3);//创建索引为3的单元格
		//设置值
		cell.setCellValue("hello  世界");
		//数据到excel文件中
		FileOutputStream fileOutputStream = new FileOutputStream("demo.xlsx");
		sw.write(fileOutputStream);
		//关闭io流
		fileOutputStream.close();
		//清空临时文件
		sw.dispose();
		
	}
	
	@Test
	public void testHello2() throws IOException{
		//创建一个核心对象
		SXSSFWorkbook sw = new SXSSFWorkbook();
		//创建sheet表
		Sheet sheet = sw.createSheet("99乘法表");
		
		for (int i = 1; i <=9; i++) {
			Row row = sheet.createRow(i-1);
			for (int j = 1; j <= i; j++) {
				Cell cell = row.createCell(j-1);
				cell.setCellValue(j+" * "+i+" = "+i*j);
			}
		}
		
		FileOutputStream fileOutputStream = new FileOutputStream("demo.xlsx");
		sw.write(fileOutputStream);
		fileOutputStream.close();
		sw.dispose();
		
	}
	
	
	@Test
	public void test3() throws FileNotFoundException, IOException{
		//创建核心对象
		XSSFWorkbook xw = new XSSFWorkbook(new FileInputStream("employee3.xlsx"));
		//获取sheet表
		XSSFSheet sheet = xw.getSheetAt(0);
		//获取总共有多少行
		int lastRowNum = sheet.getLastRowNum();
		//循环的迭代行
		for (int i = 0; i <= lastRowNum; i++) {
			//获取具体的行对象
			XSSFRow row = sheet.getRow(i);
			//获取行对应总共的单元格
			short lastCellNum = row.getLastCellNum();
			for (int j = 0; j < lastCellNum; j++) {
				//获取指定的单元格
				XSSFCell cell = row.getCell(j);
				//获取具体的值
				String value = cell.getStringCellValue();
				System.out.print(value+"   ");
			}
			System.out.println();
			
		}
		
	}
}	
