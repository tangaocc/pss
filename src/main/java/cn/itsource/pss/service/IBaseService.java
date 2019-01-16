package cn.itsource.pss.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import cn.itsource.pss.page.PageList;
import cn.itsource.pss.query.BaseQuery;


public interface IBaseService<T,ID extends Serializable> {
	/**
	 * 新增和修改都是此方法
	 * 	如果你传递t是没有id的，那就是新增
	 * 	如果你传递t是有id的， 那就证明是修改
	 * 
	 * @param t  对象
	 */
	public void save(T t);

	public void delete(ID id);

	public T queryOne(ID id);

	public List<T> queryAll();
	
	/**
	 * 分页查询
	 * @param baseQuery  公共的查询条件
	 * @return
	 */
	public PageList<T> findPageByQuery(BaseQuery baseQuery); 
	
	/**
	 * 
	 * @param data  查询出来的列表信息
	 * @param head	头信息
	 */
	public InputStream  data2Excel(List<String[]> data,String[] head) throws IOException;
	
	/**
	 * 导入excel附件
	 * @param file   临时附件
	 * @return   List<String[]>  返回的是解析excel的最终数据
	 */
	public List<String[]> importExcel(File file)  throws  IOException;
}
