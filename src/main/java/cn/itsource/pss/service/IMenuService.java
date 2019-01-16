package cn.itsource.pss.service;

import java.util.List;

import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.domain.Menu;

public interface IMenuService extends IBaseService<Menu, Long> {
	/**
	 * 判断名字是否重复
	 * @param id  主键id
	 * @param name	需要判断的姓名
	 * @return  false:表示重复， true：表示不重复
	 * 	
	 */
	public boolean checkName(Long id,String name);
	
	/**
	 * 查询菜单列表，根据员工对象
	 * @param employee  员工队员
	 * @return  根据指定的员工查询，该员工拥有哪些菜单列表
	 */
	public List<Menu> findMenusByEmployee(Employee employee);
}
