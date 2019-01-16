package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.Department;
import cn.itsource.pss.repository.DepartmentRepository;
import cn.itsource.pss.service.IDepartmentService;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department, Long> implements IDepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public Department findDepartmentByName(String deptName) {
		List<Department> list = departmentRepository.findByJpql("select o from Department o where o.name=?", deptName);
		return list.size() > 0 ? list.get(0) : null;
	}

}
