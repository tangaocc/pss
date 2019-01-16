package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.SystemDictionaryDetail;
import cn.itsource.pss.repository.SystemDictionaryDetailRepository;
import cn.itsource.pss.service.ISystemDictionaryDetailService;

@Service
public class SystemDictionaryDetailServiceImpl extends BaseServiceImpl<SystemDictionaryDetail, Long> implements ISystemDictionaryDetailService {
	
	@Autowired
	private SystemDictionaryDetailRepository systemDictionaryDetailRepository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from SystemDictionaryDetail o where o.name=?";
		if(id == null){//证明是新增
			List list = systemDictionaryDetailRepository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的systemDictionaryDetail对象
			SystemDictionaryDetail systemDictionaryDetail = super.queryOne(id);
			if(name.equals(systemDictionaryDetail.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = systemDictionaryDetailRepository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}

	@Override
	public List<SystemDictionaryDetail> findSystemDictionaryDetailByTypeSn(String sn) {
		String jpql = "select o from SystemDictionaryDetail o join o.systemDictionaryType t where t.sn=?";
		List<SystemDictionaryDetail> details = systemDictionaryDetailRepository.findByJpql(jpql, sn);
		return details;
	}
}
