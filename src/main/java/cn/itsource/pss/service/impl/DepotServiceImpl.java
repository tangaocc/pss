package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.Depot;
import cn.itsource.pss.repository.DepotRepository;
import cn.itsource.pss.service.IDepotService;

@Service
public class DepotServiceImpl extends BaseServiceImpl<Depot, Long> implements IDepotService {
	
	@Autowired
	private DepotRepository depotRepository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from Depot o where o.name=?";
		if(id == null){//证明是新增
			List list = depotRepository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的depot对象
			Depot depot = super.queryOne(id);
			if(name.equals(depot.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = depotRepository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}
}
