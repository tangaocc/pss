package cn.itsource.pss.service;

import java.util.List;

import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.domain.Permission;

public interface IPermissionService extends IBaseService<Permission, Long> {
	/**
	 * 判断名字是否重复
	 * @param id  主键id
	 * @param name	需要判断的姓名
	 * @return  false:表示重复， true：表示不重复
	 * 	
	 */
	public boolean checkName(Long id,String name);
	
	
	/**
	 * 获取权限列表
	 * @param e  根据员工对象
	 * @return	返回的是 员工对应的权限
	 */
	public List<String> findPermissionByEmployee(Employee e);
	/**
	 * 查询所有权限对应的 url地址
	 * @return
	 */
	public List<String> findAllMethod();
}
