package cn.itsource.pss.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.query.BaseQuery;

public interface IEmployeeService extends IBaseService<Employee, Long> {
	
	/**
	 * 判断用户名是否存在
	 * @param username
	 * @return  
	 */
	public boolean checkUsername(Long id,String username);
	
	
	/**
	 * 校验用户名和密码是否能正常的登录
	 * @param username  用户名
	 * @param password  密码
	 * @return Employee  如果登录成功，则返回用户名和密码对应的 对象
	 */
	public Employee checkUsernameAndPassword(String username,String password);

	
	/**
	 * 下载excel根据查询条件
	 * @param baseQuery  查询条件
	 * @return
	 */
	public InputStream downloadExcel(BaseQuery baseQuery) throws IOException;
	
	/**
	 * 把excel中的数据持久化到数据库中
	 * @param list
	 */
	public void saveExcelData(List<String[]> list);
}
