package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.Role;
import cn.itsource.pss.repository.RoleRepository;
import cn.itsource.pss.service.IRoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, Long> implements IRoleService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from Role o where o.name=?";
		if(id == null){//证明是新增
			List list = roleRepository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的role对象
			Role role = super.queryOne(id);
			if(name.equals(role.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = roleRepository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}
}
