package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.${upperCase};
import cn.itsource.pss.repository.${upperCase}Repository;
import cn.itsource.pss.service.I${upperCase}Service;

@Service
public class ${upperCase}ServiceImpl extends BaseServiceImpl<${upperCase}, Long> implements I${upperCase}Service {
	
	@Autowired
	private ${upperCase}Repository ${lowerCase}Repository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from ${upperCase} o where o.name=?";
		if(id == null){//证明是新增
			List list = ${lowerCase}Repository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的${lowerCase}对象
			${upperCase} ${lowerCase} = super.queryOne(id);
			if(name.equals(${lowerCase}.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = ${lowerCase}Repository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}
}
