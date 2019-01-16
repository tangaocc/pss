package cn.itsource.pss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsource.pss.domain.ProductType;
import cn.itsource.pss.repository.ProductTypeRepository;
import cn.itsource.pss.service.IProductTypeService;

@Service
public class ProductTypeServiceImpl extends BaseServiceImpl<ProductType, Long> implements IProductTypeService {
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Override
	public boolean checkName(Long id,String name) {
		
		String jpql = "select count(o) from ProductType o where o.name=?";
		if(id == null){//证明是新增
			List list = productTypeRepository.findByJpql(jpql, name);
			Long count = (Long)list.get(0);
			return count == 0;
			
		}else{//证明是修改
			//通过id查询到对应的productType对象
			ProductType productType = super.queryOne(id);
			if(name.equals(productType.getName())){
				return true;// 应该直接放行    true:代表是没重复， false：代表重复了
			}else{
				List list = productTypeRepository.findByJpql(jpql, name);
				Long count = (Long)list.get(0);
				return count == 0;
			}
		}
	}

	@Override
	public List<ProductType> findOneLevelType() {
		String jpql = "select o from ProductType o where o.parent is null ";
		List<ProductType> types = productTypeRepository.findByJpql(jpql);
		return types;
	}

	@Override
	public List<ProductType> findTwoLevelByPid(Long pid) {
		String jpql = "select o from ProductType o where o.parent.id=?";
		return productTypeRepository.findByJpql(jpql, pid);
	}
}
