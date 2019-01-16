package cn.itsource.pss.service;

import cn.itsource.pss.domain.Department;

public interface IDepartmentService extends IBaseService<Department, Long> {
	
	/**
	 * 根据部门名称查询部门对象
	 * @param deptName 部门名字
	 * @return 部门对象
	 */
	public Department findDepartmentByName(String deptName);

}
