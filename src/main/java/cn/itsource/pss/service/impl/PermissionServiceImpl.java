package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.Employee;
import cn.itsource.pss.domain.Permission;
import cn.itsource.pss.repository.PermissionRepository;
import cn.itsource.pss.service.IPermissionService;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission, Long> implements IPermissionService {
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from Permission o where o.name=?";
		if(id == null){//证明是新增
			List list = permissionRepository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的permission对象
			Permission permission = super.queryOne(id);
			if(name.equals(permission.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = permissionRepository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}

	@Override
	public List<String> findPermissionByEmployee(Employee e) {
		String jpql = "select distinct p.method from Employee e join e.roles r  join r.permissions p where e=?";
		return permissionRepository.findByJpql(jpql, e);
	}

	@Override
	public List<String> findAllMethod() {
		String jpql = "select method from Permission";
		return permissionRepository.findByJpql(jpql);
	}
}
