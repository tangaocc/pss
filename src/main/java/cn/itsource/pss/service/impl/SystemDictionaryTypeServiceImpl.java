package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.SystemDictionaryType;
import cn.itsource.pss.repository.SystemDictionaryTypeRepository;
import cn.itsource.pss.service.ISystemDictionaryTypeService;

@Service
public class SystemDictionaryTypeServiceImpl extends BaseServiceImpl<SystemDictionaryType, Long> implements ISystemDictionaryTypeService {
	
	@Autowired
	private SystemDictionaryTypeRepository systemDictionaryTypeRepository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from SystemDictionaryType o where o.name=?";
		if(id == null){//证明是新增
			List list = systemDictionaryTypeRepository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的systemDictionaryType对象
			SystemDictionaryType systemDictionaryType = super.queryOne(id);
			if(name.equals(systemDictionaryType.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = systemDictionaryTypeRepository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}
}
