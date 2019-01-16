package cn.itsource.pss.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itsource.pss.page.PageList;
import cn.itsource.pss.query.BaseQuery;
import cn.itsource.pss.repository.BaseRepository;
import cn.itsource.pss.service.IBaseService;

//加上事务只读
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
public abstract class BaseServiceImpl<T, ID extends Serializable> implements IBaseService<T, ID> {

	@Autowired
	private BaseRepository<T, ID> baseRepository;

	@Override
	public void save(T t) {
		baseRepository.save(t);
	}

	@Override
	public void delete(ID id) {
		baseRepository.delete(id);
	}

	@Override
	public T queryOne(ID id) {
		return baseRepository.findOne(id);
	}

	@Override
	public List<T> queryAll() {
		return baseRepository.findAll();
	}
	
	

	@Override
	public PageList<T> findPageByQuery(BaseQuery baseQuery) {
		return baseRepository.findPageByQuery(baseQuery);
	}
	
	//data中1个数组代表一个对象
	@Override
	public InputStream data2Excel(List<String[]> data, String[] head) throws IOException {
		//创建核心对象
		SXSSFWorkbook sw = new SXSSFWorkbook();
		//创建sheet表
		Sheet sheet = sw.createSheet();
		//创建行
		Row row = sheet.createRow(0);
		for (int i = 0; i < head.length; i++) {
			//在第一行中循环创建单元格
			Cell cell = row.createCell(i);
			//给单元格设置值
			cell.setCellValue(head[i]);
		}
		//创建第二行以上
		for (int i = 0; i < data.size(); i++) {
			//创建行
			row = sheet.createRow(i+1);
			//拿到具体的某一个对象
			String[] strs = data.get(i);
			for (int j = 0; j < strs.length; j++) {
				//循环创建单元格
				Cell cell = row.createCell(j);
				cell.setCellValue(strs[j]);
			}
		}
		ByteArrayOutputStream out = new ByteArrayOutputStream() ;
		sw.write(out);
		ByteArrayInputStream inputStream = new ByteArrayInputStream(out.toByteArray());
		return inputStream;
		
		
	}

	@Override
	public List<String[]> importExcel(File file) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(file);
		//创建核心对象
		XSSFWorkbook xw = new XSSFWorkbook(fileInputStream);
		//获取sheet表
		XSSFSheet sheet = xw.getSheetAt(0);
		//获取sheet表中对应总共有多少行
		int lastRowNum = sheet.getLastRowNum();
		List<String[]> listDatas = new ArrayList<String[]>();
		for (int i = 1; i <= lastRowNum; i++) {
			//获取指定的行
			XSSFRow row = sheet.getRow(i);
			//获取行对应总共有多少个单元格
			short lastCellNum = row.getLastCellNum();
			String[] datas = new String[lastCellNum];
			for (int j = 0; j < lastCellNum; j++) {
				//获取指定的单元格
				XSSFCell cell = row.getCell(j);
				//获取单元格对应指定的值
				datas[j] = cell.getStringCellValue();
			}
			listDatas.add(datas);
		}
		return listDatas;
		
		
		
	}

	
}
