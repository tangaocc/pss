package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.Dept;
import cn.itsource.pss.repository.DeptRepository;
import cn.itsource.pss.service.IDeptService;

@Service
public class DeptServiceImpl extends BaseServiceImpl<Dept, Long> implements IDeptService {
	
	@Autowired
	private DeptRepository deptRepository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from Dept o where o.name=?";
		if(id == null){//证明是新增
			List list = deptRepository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的dept对象
			Dept dept = super.queryOne(id);
			if(name.equals(dept.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = deptRepository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}
}
